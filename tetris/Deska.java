package tetris;

import tetris.Lik.Liki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Deska extends JPanel {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;
    private final int BASE_DELAY = 400;
    private Timer timer;
    private boolean jeKonecPada = false;
    private boolean jaPavza = false;
    private boolean jeKonecIgre = false;
    private boolean confirm=false;
    private int štOdstranjenihVrstic=0;
    private int trenutniX = 0;
    private int trenutniY = 0;
    private int točke=0;
    private int level=0;
    private String statusbar;
    private Lik trenutniLik;
    private Lik naslednjiLik;
    private Lik shranjenLik;
    private Liki[] deska;
    private Score[] scores;
    private InfoDisplay display;
    private Color[] colors;

    public Deska(InfoDisplay display) {
        initBoard(display);
    }

    private void initBoard(InfoDisplay display){
        setFocusable(true);
        addKeyListener(new TAdapter());
        this.display=display;
        try{
            scores=io.beriStart();
            io.piši(scores);
        }
        catch(IOException e){
            scores=new Score[0];
        }
        display.updateScores(scores);
        colors=Colors.defaultC;
    }

    private int širinaKvadrata() {
        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    private int višinaKvadrata() {
        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    private Liki shapeAt(int x, int y) {
        return deska[(y * BOARD_WIDTH) + x];
    }

    void start() {
        trenutniLik = new Lik();
        shranjenLik=new Lik();
        naslednjiLik=new Lik();
        naslednjiLik.setRandomLik();
        deska = new Liki[BOARD_WIDTH * BOARD_HEIGHT];
        clearBoard();
        newPiece();
        timer = new Timer(Level.getDelay(level,BASE_DELAY), new GameCycle());
        Timer infoTimer = new Timer(1, new GraphicsCycle());
        infoTimer.start();
        timer.start();
        confirm=false;
    }

    private void pause() {
        jaPavza=!jaPavza;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    public void setColors(Color[] colors){
        this.colors=colors;
    }
    private void doDrawing(Graphics g) {
        g.setColor(new Color(225,225,225));
        for(int i=0;i<getWidth();i+=getWidth()/BOARD_WIDTH){
            g.drawLine(i, 0, i, getHeight());
            g.drawLine(i-1, 0, i-1, getHeight());
        }
        g.drawLine(BOARD_WIDTH*širinaKvadrata()+1, 0, BOARD_WIDTH*širinaKvadrata()+1, getHeight());
        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * višinaKvadrata();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                Liki shape = shapeAt(j, BOARD_HEIGHT - i - 1);
                if (shape != Liki.NoShape) {
                    drawSquare(g, j * širinaKvadrata(),
                    boardTop + i * višinaKvadrata(), shape);
                }
            }
        }

        if (trenutniLik.getLik() != Liki.NoShape) {
            for (int i = 0; i < 4; i++) {
                int x = trenutniX + trenutniLik.x(i);
                int y = trenutniY - trenutniLik.y(i);
                drawSquare(g, x * širinaKvadrata(),
                        boardTop + (BOARD_HEIGHT - y - 1) * višinaKvadrata(),
                        trenutniLik.getLik());
            }
        }
        
        if(jeKonecIgre){
            g.setColor(new Color(125,125,125,125));
            g.fillRect(0, getHeight()/3-50, getWidth(), 200);
            g.setFont(new Font("default", Font.PLAIN, 40));
            g.setColor(Color.BLACK);
            g.drawString("Game over.", getWidth()/4, getHeight()/3);
            g.drawString("Score: "+točke, getWidth()/4+20, getHeight()/3+40);
            g.setFont(new Font("default", Font.PLAIN, 30));
            g.drawString("Pritisni presledek", getWidth()/5, getHeight()/3+100);
            g.drawString("za novo igro", getWidth()/4, getHeight()/3+130);
            if(!confirm){
                confirm=true;
                if(JOptionPane.showConfirmDialog(display, "Ali želiš shraniti rezultat igre?", "", JOptionPane.YES_NO_OPTION)==0){
                    scores=io.dodajZapis(scores,new Score(točke,JOptionPane.showInputDialog(display,"Vnesi ime:")));
                    try{io.piši(scores);}catch(IOException ignored){}
                    display.updateScores(scores);
                }
            }
        }
        if(jaPavza){
            g.setColor(new Color(125,125,125,125));
            g.fillRect(0, getHeight()/3-50, getWidth(),getHeight()/2);
            g.setFont(new Font("default", Font.PLAIN, 40));
            g.setColor(Color.BLACK);
            g.drawString("Pavza", getWidth()/2-50, getHeight()/3);
  
        }
    }

    private void dropDown() {
        int newY = trenutniY;
        int števec=0;
        while (newY > 0) {
            if (!tryMove(trenutniLik, trenutniX, newY - 1)) {
                break;
            }
            števec++;
            newY--;
        }
        pieceDropped();
        točke+=2*števec;
        doGraphicsCycle();
    }

    private void oneLineDown() {
        if (!tryMove(trenutniLik, trenutniX, trenutniY - 1)) {
            pieceDropped();
        }
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
            deska[i] = Liki.NoShape;
        }
    }

    private void pieceDropped() {
        for (int i = 0; i < 4; i++) {
            int x = trenutniX + trenutniLik.x(i);
            int y = trenutniY - trenutniLik.y(i);
            deska[(y * BOARD_WIDTH) + x] = trenutniLik.getLik();
        }

        removeFullLines();
        if (!jeKonecPada) {
            newPiece();
        }
    }

    private void newPiece() {
        trenutniLik.setLik(naslednjiLik.getLik());
        naslednjiLik.setRandomLik();
        trenutniX = BOARD_WIDTH / 2 + 1;
        trenutniY = BOARD_HEIGHT - 1 + trenutniLik.minY();
        if (!tryMove(trenutniLik, trenutniX, trenutniY)) {
            konecIgre();
        }
    }

    private boolean tryMove(Lik newPiece, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }
            if (shapeAt(x, y) != Liki.NoShape) {
                return false;
            }
        }

        trenutniLik = newPiece;
        trenutniX = newX;
        trenutniY = newY;
        repaint();
        return true;
    }

    private void removeFullLines() {
        int numFullLines = 0;
        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean lineIsFull = true;
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (shapeAt(j, i) == Liki.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                numFullLines++;
                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        deska[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }
        if (numFullLines > 0) {
            štOdstranjenihVrstic+=numFullLines;
            switch (numFullLines) {
                case 1 -> {
                    točke += 100;
                    if (level > 0)
                        točke += 100 * (level - 1);
                }
                case 2 -> {
                    točke += 300;
                    if (level > 0)
                        točke += 300 * (level - 1);
                }
                case 3 -> {
                    točke += 500;
                    if (level > 0)
                        točke += 500 * (level - 1);
                }
                case 4 -> {
                    točke += 800;
                    if (level > 0)
                        točke += 800 * (level - 1);
                }
            }
            statusbar="Točke: "+ točke;
            jeKonecPada = true;
            trenutniLik.setLik(Liki.NoShape);
            level=Level.checkLevel(štOdstranjenihVrstic);
        }
    }

    private void drawSquare(Graphics g, int x, int y, Liki shape) {
        var color = colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, širinaKvadrata() -1, višinaKvadrata() -1 );
        g.setColor(color.darker());
        g.drawLine(x, y + višinaKvadrata() - 1, x, y);
        g.drawLine(x, y, x + širinaKvadrata() - 1, y);
        // g.setColor(color.darker());
        g.drawLine(x + 1, y + višinaKvadrata() - 1,
                 x + širinaKvadrata() - 1, y + višinaKvadrata() - 1);
        g.drawLine(x + širinaKvadrata() - 1, y + višinaKvadrata() - 1,
                 x + širinaKvadrata() - 1, y + 1);
    }

    private void update() {
        statusbar="Točke: "+ točke;
        updateDelay();
        if (jaPavza) {
            return;
        }
        if(jeKonecIgre){
            return;
        }
        if (jeKonecPada) {
            jeKonecPada = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

    private void updateDelay(){
        timer.stop();
        timer.setDelay(Level.getDelay(level,BASE_DELAY));
        timer.start();
    }

    private void shraniLik(){
        Lik temp=shranjenLik;
        shranjenLik=naslednjiLik;
        if(temp.getLik().equals(Liki.NoShape)){
            temp.setRandomLik();
        }
        naslednjiLik=temp;
    }

    private void konecIgre(){
        if(!jeKonecIgre){
            trenutniLik.setLik(Liki.NoShape);
            jeKonecIgre=true;
            timer.stop();
        }else{
            jeKonecIgre=false;
            confirm=false;
            naslednjiLik.setRandomLik();
            clearBoard();
            newPiece();
            shranjenLik.setLik(Liki.NoShape);
            timer.start();
            točke=0;
        }
    }

    private void doGameCycle(){
        update();
    }

    private void doGraphicsCycle(){
        display.update(shranjenLik,naslednjiLik,statusbar,level,štOdstranjenihVrstic);
        display.repaint();
        repaint();
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class GraphicsCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGraphicsCycle();
        }
    }
    
    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_P, KeyEvent.VK_ESCAPE -> {
                    if (!jeKonecIgre)
                        pause();
                }
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                    if (!jeKonecIgre && !jaPavza)
                        tryMove(trenutniLik, trenutniX - 1, trenutniY);
                }
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                    if (!jeKonecIgre && !jaPavza)
                        tryMove(trenutniLik, trenutniX + 1, trenutniY);
                }
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                    if (!jeKonecIgre && !jaPavza)
                        tryMove(trenutniLik.obrniDesno(), trenutniX, trenutniY);
                }
                case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                    if (!jeKonecIgre && !jaPavza)
                        tryMove(trenutniLik.obrniLevo(), trenutniX, trenutniY);
                }
                case KeyEvent.VK_SPACE -> {
                    if (!jeKonecIgre && !jaPavza)
                        dropDown();
                    else
                        konecIgre();
                }
                case KeyEvent.VK_Y, KeyEvent.VK_ENTER -> {
                    if (!jeKonecIgre && !jaPavza)
                        shraniLik();
                }
                case KeyEvent.VK_BACK_SPACE -> konecIgre();
            }
        }
    }
}