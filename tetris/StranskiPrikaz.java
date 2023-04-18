package tetris;

import tetris.Lik.Liki;

import javax.swing.*;
import java.awt.*;
public class StranskiPrikaz extends JPanel{

    private int širinaKvadrata;
    private int višinaKvadrata;
    private int nivo;
    private int štVrstic;

    private Lik shranjenLik;
    private Lik naslednjiLik;

    private String vrsticaStanja;
    private final JTextArea prikazRezultatovPrejšnjihIger;

    private Color[] barve;

    public StranskiPrikaz() {
        this.setLayout(new BorderLayout());
        JScrollPane sp = new JScrollPane();
        prikazRezultatovPrejšnjihIger =new JTextArea();
        prikazRezultatovPrejšnjihIger.setRows(12);
        prikazRezultatovPrejšnjihIger.setColumns(9);
        sp.setViewportView(prikazRezultatovPrejšnjihIger);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(sp,BorderLayout.SOUTH);
        barve = Barve.PRIVZETE_BARVE;
    }
    private void polniPrikazRezultatov(RezultatIgre[] scores){
        prikazRezultatovPrejšnjihIger.setText("");
        for (int i = 0; i <scores.length ; i++) {
            prikazRezultatovPrejšnjihIger.append(i+1+". "+scores[i].name+"    "+scores[i].point+"\n");
        }
        prikazRezultatovPrejšnjihIger.setCaretPosition(0);
    }

    public void nastaviBarve(Color[] barve) {
        this.barve = barve;
    }

    private void rišiDelLika(Graphics g, int x, int y, Liki liki) {

        var barva = barve[liki.ordinal()];
        g.setColor(barva);
        g.fillRect(x + 1, y + 1, širinaKvadrata -1, višinaKvadrata -1 );
        g.setColor(barva.darker());
        g.drawLine(x, y + višinaKvadrata - 1, x, y);
        g.drawLine(x, y, x + širinaKvadrata - 1, y);
        g.drawLine(x + 1, y + višinaKvadrata - 1,
                 x + širinaKvadrata - 1, y + višinaKvadrata - 1);
        g.drawLine(x + širinaKvadrata - 1, y + višinaKvadrata - 1,
                 x + širinaKvadrata - 1, y + 1);
    }
    public void osveži(Lik shranjen, Lik naslednji, String stanje, int nivo, int štVrstic){
        širinaKvadrata=getWidth()/5;
        višinaKvadrata=getWidth()/5;
        shranjenLik=shranjen;
        naslednjiLik=naslednji;
        vrsticaStanja =stanje;
        this.nivo =nivo;
        this.štVrstic=štVrstic;
        if(shranjenLik==null) {
            shranjenLik=new Lik();
            shranjenLik.setLik(Liki.NoShape);
        }
        if(naslednjiLik==null) {
            naslednjiLik=new Lik();
            naslednjiLik.setLik(Liki.NoShape);
        }
        if(vrsticaStanja ==null) {
            vrsticaStanja ="";
        }
    }
    public void osvežiRezultate(RezultatIgre[] scores){
        polniPrikazRezultatov(scores);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        riši(g);
    }
    public void riši(Graphics g){


        g.drawLine(0, 0, 0, getHeight());

        int novY=getHeight()/8;
        try{
            for (int i = 0; i < 4; i++) {
                int x = 50 + naslednjiLik.x(i)*širinaKvadrata;
                int y = novY+širinaKvadrata+širinaKvadrata/2 - naslednjiLik.y(i)*višinaKvadrata;
                if(naslednjiLik.getLik().equals(Liki.LineShape)){
                    y+=širinaKvadrata;
                }
                rišiDelLika(g, x, y, naslednjiLik.getLik());
            }
            
            
            int starY=novY+višinaKvadrata*6;
            if(!shranjenLik.getLik().equals(Liki.NoShape))
                for (int i = 0; i < 4; i++) {
                    int x = 50 + shranjenLik.x(i)*širinaKvadrata;
                    int y = starY+širinaKvadrata+širinaKvadrata/2 - shranjenLik.y(i)*višinaKvadrata;
                    if(shranjenLik.getLik().equals(Liki.LineShape))
                        y+=širinaKvadrata;
                    rišiDelLika(g, x, y, shranjenLik.getLik());
                }

            g.setColor(Color.BLACK);
            g.fillRect(0, novY-30, getWidth(), 2);
            g.fillRect(0, starY-30, getWidth(), 2);
            g.fillRect(0, starY+višinaKvadrata*6-30, getWidth(), 2);
            g.setFont(new Font("default", Font.PLAIN, 20));
            g.drawString("Naslednji lik:",20,novY);
            g.drawString("Shranjen lik:",20,starY);
            g.drawString("Level: "+ nivo, 10, starY+višinaKvadrata*6);
            g.setFont(new Font("default", Font.PLAIN, 12));
            g.drawString("odstranjene vrstice: "+štVrstic, 10, starY+višinaKvadrata*7);
            g.drawString("Št vrstic do levla: "+(NivoIgre.getLinesToLevel(nivo)-štVrstic), 10, starY+višinaKvadrata*8);
            g.drawString(vrsticaStanja, 5, getHeight()/16);
        }catch(NullPointerException ignored){}
    }
}