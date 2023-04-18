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

    private static final int ŠIRINA_DESKE = 10;
    private static final int VIŠINA_DESKE = 22;
    private static final int PRIVZETI_ZAMIK_ČASOVNIKA = 400;
    private Timer časovnik;
    private boolean jeKonecPada = false;
    private boolean jaPavza = false;
    private boolean jeKonecIgre = false;
    private boolean potrdiShranjevanjeRezultata=false;
    private int štOdstranjenihVrstic=0;
    private int trenutniX = 0;
    private int trenutniY = 0;
    private int točke=0;
    private int nivoIgre=0;
    private Lik trenutniLik;
    private Lik naslednjiLik;
    private Lik shranjenLik;
    private Liki[] deska;
    private Score[] rezultati;
    private InfoDisplay stranskiPrikaz;
    private Color[] barve;

    public Deska(InfoDisplay display) {
        setFocusable(true);
        addKeyListener(new TAdapter());
        this.stranskiPrikaz=display;
        try{
            rezultati=io.beriStart();
            io.piši(rezultati);
        }
        catch(IOException e){
            rezultati=new Score[0];
        }
        display.updateScores(rezultati);
        barve=Colors.defaultC;
    }

    private int širinaKvadrata() {
        return (int) getSize().getWidth() / ŠIRINA_DESKE;
    }

    private int višinaKvadrata() {
        return (int) getSize().getHeight() / VIŠINA_DESKE;
    }

    private Liki shapeAt(int x, int y) {
        return deska[(y * ŠIRINA_DESKE) + x];
    }

    void start() {
        trenutniLik = new Lik();
        shranjenLik=new Lik();
        naslednjiLik=new Lik();
        naslednjiLik.setRandomLik();
        deska = new Liki[ŠIRINA_DESKE * VIŠINA_DESKE];
        clearBoard();
        newPiece();
        časovnik = new Timer(Level.getDelay(nivoIgre,PRIVZETI_ZAMIK_ČASOVNIKA), new GameCycle());
        Timer infoTimer = new Timer(1, new GraphicsCycle());
        infoTimer.start();
        časovnik.start();
        potrdiShranjevanjeRezultata=false;
    }

    private void pause() {
        jaPavza=!jaPavza;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    public void setBarve(Color[] colors){
        this.barve=colors;
    }
    private void doDrawing(Graphics g) {
        g.setColor(new Color(225,225,225));
        for(int i=0;i<getWidth();i+=getWidth()/ŠIRINA_DESKE){
            g.drawLine(i, 0, i, getHeight());
            g.drawLine(i-1, 0, i-1, getHeight());
        }
        g.drawLine(ŠIRINA_DESKE*širinaKvadrata()+1, 0, ŠIRINA_DESKE*širinaKvadrata()+1, getHeight());
        var size = getSize();
        int boardTop = (int) size.getHeight() - VIŠINA_DESKE * višinaKvadrata();
        for (int i = 0; i < VIŠINA_DESKE; i++) {
            for (int j = 0; j < ŠIRINA_DESKE; j++) {
                Liki shape = shapeAt(j, VIŠINA_DESKE - i - 1);
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
                        boardTop + (VIŠINA_DESKE - y - 1) * višinaKvadrata(),
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
            if(!potrdiShranjevanjeRezultata){
                potrdiShranjevanjeRezultata=true;
                if(JOptionPane.showConfirmDialog(stranskiPrikaz, "Ali želiš shraniti rezultat igre?", "", JOptionPane.YES_NO_OPTION)==0){
                    rezultati=io.dodajZapis(rezultati,new Score(točke,JOptionPane.showInputDialog(stranskiPrikaz,"Vnesi ime:")));
                    try{io.piši(rezultati);}catch(IOException ignored){}
                    stranskiPrikaz.updateScores(rezultati);
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
        for (int i = 0; i < VIŠINA_DESKE * ŠIRINA_DESKE; i++) {
            deska[i] = Liki.NoShape;
        }
    }

    private void pieceDropped() {
        for (int i = 0; i < 4; i++) {
            int x = trenutniX + trenutniLik.x(i);
            int y = trenutniY - trenutniLik.y(i);
            deska[(y * ŠIRINA_DESKE) + x] = trenutniLik.getLik();
        }

        removeFullLines();
        if (!jeKonecPada) {
            newPiece();
        }
    }

    private void newPiece() {
        trenutniLik.setLik(naslednjiLik.getLik());
        naslednjiLik.setRandomLik();
        trenutniX = ŠIRINA_DESKE / 2 + 1;
        trenutniY = VIŠINA_DESKE - 1 + trenutniLik.minY();
        if (!tryMove(trenutniLik, trenutniX, trenutniY)) {
            konecIgre();
        }
    }

    private boolean tryMove(Lik newPiece, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= ŠIRINA_DESKE || y < 0 || y >= VIŠINA_DESKE) {
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
        for (int i = VIŠINA_DESKE - 1; i >= 0; i--) {
            boolean lineIsFull = true;
            for (int j = 0; j < ŠIRINA_DESKE; j++) {
                if (shapeAt(j, i) == Liki.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                numFullLines++;
                for (int k = i; k < VIŠINA_DESKE - 1; k++) {
                    for (int j = 0; j < ŠIRINA_DESKE; j++) {
                        deska[(k * ŠIRINA_DESKE) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }
        if (numFullLines > 0) {
            štOdstranjenihVrstic+=numFullLines;
            switch (numFullLines) {
                case 1:
                    točke += 100;
                    if (nivoIgre > 0)
                        točke += 100 * (nivoIgre - 1);
                    break;
                case 2:
                    točke += 300;
                    if (nivoIgre > 0)
                        točke += 300 * (nivoIgre - 1);
                    break;
                case 3:
                    točke += 500;
                    if (nivoIgre > 0)
                        točke += 500 * (nivoIgre - 1);
                    break;
                case 4:
                    točke += 800;
                    if (nivoIgre > 0)
                        točke += 800 * (nivoIgre - 1);
                    break;
            }
            jeKonecPada = true;
            trenutniLik.setLik(Liki.NoShape);
            nivoIgre=Level.checkLevel(štOdstranjenihVrstic);
        }
    }

    private void drawSquare(Graphics g, int x, int y, Liki shape) {
        var color = barve[shape.ordinal()];
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
        časovnik.stop();
        časovnik.setDelay(Level.getDelay(nivoIgre,PRIVZETI_ZAMIK_ČASOVNIKA));
        časovnik.start();
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
            časovnik.stop();
        }else{
            jeKonecIgre=false;
            potrdiShranjevanjeRezultata=false;
            naslednjiLik.setRandomLik();
            clearBoard();
            newPiece();
            shranjenLik.setLik(Liki.NoShape);
            časovnik.start();
            točke=0;
        }
    }

    private void doGameCycle(){
        update();
    }

    private void doGraphicsCycle(){
        stranskiPrikaz.update(shranjenLik,naslednjiLik,"Točke: "+ točke,nivoIgre,štOdstranjenihVrstic);
        stranskiPrikaz.repaint();
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
                case KeyEvent.VK_P:
                case KeyEvent.VK_ESCAPE:
                    if (!jeKonecIgre)
                        pause();
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    if (!jeKonecIgre && !jaPavza)
                        tryMove(trenutniLik, trenutniX - 1, trenutniY);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    if (!jeKonecIgre && !jaPavza)
                        tryMove(trenutniLik, trenutniX + 1, trenutniY);
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    if (!jeKonecIgre && !jaPavza)
                        tryMove(trenutniLik.obrniDesno(), trenutniX, trenutniY);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    if (!jeKonecIgre && !jaPavza)
                        tryMove(trenutniLik.obrniLevo(), trenutniX, trenutniY);
                    break;
                case KeyEvent.VK_SPACE:
                    if (!jeKonecIgre && !jaPavza)
                        dropDown();
                    else
                        konecIgre();
                    break;
                case KeyEvent.VK_Y:
                case KeyEvent.VK_ENTER:
                    if (!jeKonecIgre && !jaPavza)
                        shraniLik();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    konecIgre();
                    break;
            }
        }
    }
}