package tetris;

import tetris.Lik.Liki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class IgralnaPlošča extends JPanel {

    private static final int ŠIRINA_DESKE = 10;
    private static final int VIŠINA_DESKE = 22;
    private static final int PRIVZETI_ZAMIK_ČASOVNIKA = 400;
    private Timer časovnik;
    private boolean jeKonecPada = false;
    private boolean jePavza = false;
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
    private RezultatIgre[] rezultati;
    private final StranskiPrikaz stranskiPrikaz;
    private Color[] barve;

    public IgralnaPlošča(StranskiPrikaz display) {
        setFocusable(true);
        addKeyListener(new Tipke());
        this.stranskiPrikaz=display;
        try{
            rezultati=io.beriRezultate();
            io.piši(rezultati);
        }
        catch(IOException e){
            rezultati=new RezultatIgre[0];
        }
        display.osvežiRezultate(rezultati);
        barve= Barve.PRIVZETE_BARVE;
    }

    private int širinaKvadrata() {
        return (int) getSize().getWidth() / ŠIRINA_DESKE;
    }

    private int višinaKvadrata() {
        return (int) getSize().getHeight() / VIŠINA_DESKE;
    }

    private Liki likNaKoordinatah(int x, int y) {
        return deska[(y * ŠIRINA_DESKE) + x];
    }

    public void začniIgro() {
        trenutniLik = new Lik();
        shranjenLik=new Lik();
        naslednjiLik=new Lik();
        naslednjiLik.nastaviNaključenLik();
        deska = new Liki[ŠIRINA_DESKE * VIŠINA_DESKE];
        počistiDesko();
        novLik();
        časovnik = new Timer(NivoIgre.pridobiZamik(nivoIgre,PRIVZETI_ZAMIK_ČASOVNIKA), new ZankaIgre());
        Timer časovnikZaStranskiPrikaz = new Timer(1, new ZankaPrikaza());
        časovnikZaStranskiPrikaz.start();
        časovnik.start();
        potrdiShranjevanjeRezultata=false;
    }

    private void pavza() {
        jePavza =!jePavza;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        riši(g);
    }
    public void nastaviBarve(Color[] barve){
        this.barve=barve;
    }
    private void riši(Graphics g) {
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
                Liki shape = likNaKoordinatah(j, VIŠINA_DESKE - i - 1);
                if (shape != Liki.PrazenLik) {
                    rišiDelLika(g, j * širinaKvadrata(),
                    boardTop + i * višinaKvadrata(), shape);
                }
            }
        }

        if (trenutniLik.dobiLik() != Liki.PrazenLik) {
            for (int i = 0; i < 4; i++) {
                int x = trenutniX + trenutniLik.x(i);
                int y = trenutniY - trenutniLik.y(i);
                rišiDelLika(g, x * širinaKvadrata(),
                        boardTop + (VIŠINA_DESKE - y - 1) * višinaKvadrata(),
                        trenutniLik.dobiLik());
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
                    rezultati=io.dodajZapis(rezultati,new RezultatIgre(točke,JOptionPane.showInputDialog(stranskiPrikaz,"Vnesi ime:")));
                    try{io.piši(rezultati);}catch(IOException ignored){}
                    stranskiPrikaz.osvežiRezultate(rezultati);
                }
            }
        }
        if(jePavza){
            g.setColor(new Color(125,125,125,125));
            g.fillRect(0, getHeight()/3-50, getWidth(),getHeight()/2);
            g.setFont(new Font("default", Font.PLAIN, 40));
            g.setColor(Color.BLACK);
            g.drawString("Pavza", getWidth()/2-50, getHeight()/3);

        }
    }

    private void spustiLikNaDnoDeske() {
        int novY = trenutniY;
        int števec=0;
        while (novY > 0) {
            if (!poskusiPremik(trenutniLik, trenutniX, novY - 1)) {
                break;
            }
            števec++;
            novY--;
        }
        likNaDnu();
        točke+=2*števec;
        osvežiPrikaz();
    }

    private void enoVrsticoDol() {
        if (!poskusiPremik(trenutniLik, trenutniX, trenutniY - 1)) {
            likNaDnu();
        }
    }

    private void počistiDesko() {
        for (int i = 0; i < VIŠINA_DESKE * ŠIRINA_DESKE; i++) {
            deska[i] = Liki.PrazenLik;
        }
    }

    private void likNaDnu() {
        for (int i = 0; i < 4; i++) {
            int x = trenutniX + trenutniLik.x(i);
            int y = trenutniY - trenutniLik.y(i);
            deska[(y * ŠIRINA_DESKE) + x] = trenutniLik.dobiLik();
        }

        odstraniPolneVrstice();
        if (!jeKonecPada) {
            novLik();
        }
    }

    private void novLik() {
        trenutniLik.setLik(naslednjiLik.dobiLik());
        naslednjiLik.nastaviNaključenLik();
        trenutniX = ŠIRINA_DESKE / 2 + 1;
        trenutniY = VIŠINA_DESKE - 1 + trenutniLik.najnižjiY();
        if (!poskusiPremik(trenutniLik, trenutniX, trenutniY)) {
            konecIgre();
        }
    }

    private boolean poskusiPremik(Lik novLik, int novX, int novY) {
        for (int i = 0; i < 4; i++) {
            int x = novX + novLik.x(i);
            int y = novY - novLik.y(i);
            if (x < 0 || x >= ŠIRINA_DESKE || y < 0 || y >= VIŠINA_DESKE) {
                return false;
            }
            if (likNaKoordinatah(x, y) != Liki.PrazenLik) {
                return false;
            }
        }

        trenutniLik = novLik;
        trenutniX = novX;
        trenutniY = novY;
        repaint();
        return true;
    }

    private void odstraniPolneVrstice() {
        int številoPolnihVrstic = 0;
        for (int i = VIŠINA_DESKE - 1; i >= 0; i--) {
            boolean polnaVrstica = true;
            for (int j = 0; j < ŠIRINA_DESKE; j++) {
                if (likNaKoordinatah(j, i) == Liki.PrazenLik) {
                    polnaVrstica = false;
                    break;
                }
            }
            if (polnaVrstica) {
                številoPolnihVrstic++;
                for (int k = i; k < VIŠINA_DESKE - 1; k++) {
                    for (int j = 0; j < ŠIRINA_DESKE; j++) {
                        deska[(k * ŠIRINA_DESKE) + j] = likNaKoordinatah(j, k + 1);
                    }
                }
            }
        }
        if (številoPolnihVrstic > 0) {
            štOdstranjenihVrstic+=številoPolnihVrstic;
            switch (številoPolnihVrstic) {
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
            trenutniLik.setLik(Liki.PrazenLik);
            nivoIgre= NivoIgre.preveriNivo(štOdstranjenihVrstic);
        }
    }

    private void rišiDelLika(Graphics g, int x, int y, Liki lik) {
        var barva = barve[lik.ordinal()];
        g.setColor(barva);
        g.fillRect(x + 1, y + 1, širinaKvadrata() -1, višinaKvadrata() -1 );
        g.setColor(barva.darker());
        g.drawLine(x, y + višinaKvadrata() - 1, x, y);
        g.drawLine(x, y, x + širinaKvadrata() - 1, y);
        g.drawLine(x + 1, y + višinaKvadrata() - 1,
                 x + širinaKvadrata() - 1, y + višinaKvadrata() - 1);
        g.drawLine(x + širinaKvadrata() - 1, y + višinaKvadrata() - 1,
                 x + širinaKvadrata() - 1, y + 1);
    }

    private void osveži() {
        osvežiZamik();
        if (jePavza) {
            return;
        }
        if(jeKonecIgre){
            return;
        }
        if (jeKonecPada) {
            jeKonecPada = false;
            novLik();
        } else {
            enoVrsticoDol();
        }
    }

    private void osvežiZamik(){
        časovnik.stop();
        časovnik.setDelay(NivoIgre.pridobiZamik(nivoIgre,PRIVZETI_ZAMIK_ČASOVNIKA));
        časovnik.start();
    }

    private void shraniLik(){
        Lik začasenLik=shranjenLik;
        shranjenLik=naslednjiLik;
        if(začasenLik.dobiLik().equals(Liki.PrazenLik)){
            začasenLik.nastaviNaključenLik();
        }
        naslednjiLik=začasenLik;
    }

    private void konecIgre(){
        if(!jeKonecIgre){
            trenutniLik.setLik(Liki.PrazenLik);
            jeKonecIgre=true;
            časovnik.stop();
        }else{
            jeKonecIgre=false;
            potrdiShranjevanjeRezultata=false;
            naslednjiLik.nastaviNaključenLik();
            počistiDesko();
            novLik();
            shranjenLik.setLik(Liki.PrazenLik);
            časovnik.start();
            točke=0;
        }
    }



    private void osvežiPrikaz(){
        stranskiPrikaz.osveži(shranjenLik,naslednjiLik,"Točke: "+ točke,nivoIgre,štOdstranjenihVrstic);
        stranskiPrikaz.repaint();
        repaint();
    }

    private class ZankaIgre implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            osveži();
        }
    }

    private class ZankaPrikaza implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            osvežiPrikaz();
        }
    }
    
    class Tipke extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_P:
                case KeyEvent.VK_ESCAPE:
                    if (!jeKonecIgre)
                        pavza();
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    if (!jeKonecIgre && !jePavza)
                        poskusiPremik(trenutniLik, trenutniX - 1, trenutniY);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    if (!jeKonecIgre && !jePavza)
                        poskusiPremik(trenutniLik, trenutniX + 1, trenutniY);
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    if (!jeKonecIgre && !jePavza)
                        poskusiPremik(trenutniLik.obrniDesno(), trenutniX, trenutniY);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    if (!jeKonecIgre && !jePavza)
                        poskusiPremik(trenutniLik.obrniLevo(), trenutniX, trenutniY);
                    break;
                case KeyEvent.VK_SPACE:
                    if (!jeKonecIgre && !jePavza)
                        spustiLikNaDnoDeske();
                    else
                        konecIgre();
                    break;
                case KeyEvent.VK_Y:
                case KeyEvent.VK_ENTER:
                    if (!jeKonecIgre && !jePavza)
                        shraniLik();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    konecIgre();
                    break;
            }
        }
    }
}