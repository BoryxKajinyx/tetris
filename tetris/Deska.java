package tetris;

import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.Lik.Liki;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Deska extends JPanel {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;
    private final int PERIOD_INTERVAL = 300;

    private Timer timer;
    private Timer infoTimer;

    private boolean jeKonecPada = false;
    private boolean jaPavza = false;
    private boolean jeKonecIgre = false;

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

    private InfoDisplay display;

    public Deska(Tetris parent,InfoDisplay display) {
        initBoard(parent,display);
    }

    private void initBoard(Tetris parent,InfoDisplay display) {
        setFocusable(true);
        addKeyListener(new TAdapter());
        this.display=display;
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
        timer = new Timer(PERIOD_INTERVAL, new GameCycle());
        infoTimer = new Timer(10, new GraphicsCycle());
        infoTimer.start();
        timer.start();
    }

    private void pause() {
        jaPavza = !jaPavza;
        if (jaPavza) {
            statusbar="Pavza.";
        } else {
            statusbar="Točke: "+String.valueOf(točke);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
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
            g.setFont(new Font("default", Font.PLAIN, 40));
            g.setColor(Color.BLACK);
            g.drawString("Game over.", getWidth()/4, getHeight()/3);
            g.drawString("Score: "+točke, getWidth()/4+20, getHeight()/3+40);
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
            switch(numFullLines){
                case 1:
                točke+=100;
                break;
                case 2:
                točke+=300;
                break;
                case 3:
                točke+=500;
                break;
                case 4:
                točke+=800;
                break;
            }
            statusbar="Točke: "+String.valueOf(točke);
            jeKonecPada = true;
            trenutniLik.setLik(Liki.NoShape);
            level=Level.checkLevel(štOdstranjenihVrstic);
        }
    }

    private void drawSquare(Graphics g, int x, int y, Liki shape) {
        Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };
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

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class GraphicsCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            display.update(shranjenLik,naslednjiLik,statusbar,level);
            display.repaint();
            repaint();
        }
    }

    private void doGameCycle() {
        update();
    }

    private void update() {
        statusbar="Točke: "+String.valueOf(točke);
        updateDelay();
        if (jaPavza) {
            statusbar="Pavza";
            return;
        }
        if(jeKonecIgre){
            statusbar="Game over";
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
        timer.setDelay(Level.getDelay(level));
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
            clearBoard();
            newPiece();
            timer.start();
            točke=0;
        }
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_P:
                    if(!jeKonecIgre)
                    pause();
                    break;
                case KeyEvent.VK_LEFT:
                    if(!jeKonecIgre)
                    tryMove(trenutniLik, trenutniX - 1, trenutniY);
                    break;
                case KeyEvent.VK_RIGHT:
                    if(!jeKonecIgre)
                    tryMove(trenutniLik, trenutniX + 1, trenutniY);
                    break;
                case KeyEvent.VK_DOWN:
                    if(!jeKonecIgre)
                    tryMove(trenutniLik.obrniDesno(), trenutniX, trenutniY);
                    break;
                case KeyEvent.VK_UP:
                    if(!jeKonecIgre)
                    tryMove(trenutniLik.obrniLevo(), trenutniX, trenutniY);
                    break;
                case KeyEvent.VK_SPACE:
                    if(!jeKonecIgre)
                    dropDown();
                    break;
                case KeyEvent.VK_D:
                    if(!jeKonecIgre)
                    oneLineDown();
                    break;
                case KeyEvent.VK_Y:
                case KeyEvent.VK_ENTER:
                    if(!jeKonecIgre)
                    shraniLik();
                    break;
                case KeyEvent.VK_0:
                    konecIgre();
                    break;
            }
        }
    }
}