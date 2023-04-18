package tetris;

import tetris.Lik.Liki;

import javax.swing.*;
import java.awt.*;
public class StranskiPrikaz extends JPanel{

    private int širinaKvadrata;
    private int višinaKvadrata;
    private int level;
    private int štVrstic;

    private Lik shranjenLik;
    private Lik naslednjiLik;

    private String statusbar;
    private final JTextArea jta;

    private Color[] colors;

    public StranskiPrikaz() {
        this.setLayout(new BorderLayout());
        JScrollPane sp = new JScrollPane();
        jta=new JTextArea();
        jta.setRows(12);
        jta.setColumns(9);
        sp.setViewportView(jta);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(sp,BorderLayout.SOUTH);
        colors= Barve.defaultC;
    }
    private void polni(RezultatIgre[] scores){
        jta.setText("");
        for (int i = 0; i <scores.length ; i++) {
            jta.append(i+1+". "+scores[i].name+"    "+scores[i].point+"\n");
        }
        jta.setCaretPosition(0);
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }

    private void drawSquare(Graphics g, int x, int y, Liki shape) {

        var color = colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, širinaKvadrata -1, višinaKvadrata -1 );
        g.setColor(color.darker());
        g.drawLine(x, y + višinaKvadrata - 1, x, y);
        g.drawLine(x, y, x + širinaKvadrata - 1, y);
        // g.setColor(color.darker());
        g.drawLine(x + 1, y + višinaKvadrata - 1,
                 x + širinaKvadrata - 1, y + višinaKvadrata - 1);
        g.drawLine(x + širinaKvadrata - 1, y + višinaKvadrata - 1,
                 x + širinaKvadrata - 1, y + 1);
    }
    public void update(Lik shranjen,Lik naslednji,String status,int level,int štVrstic){
        širinaKvadrata=getWidth()/5;
        višinaKvadrata=getWidth()/5;
        shranjenLik=shranjen;
        naslednjiLik=naslednji;
        statusbar=status;
        this.level=level;
        this.štVrstic=štVrstic;
        if(shranjenLik==null) {
            shranjenLik=new Lik();
            shranjenLik.setLik(Liki.NoShape);
        }
        if(naslednjiLik==null) {
            naslednjiLik=new Lik();
            naslednjiLik.setLik(Liki.NoShape);
        }
        if(statusbar==null) {
            statusbar="";
        }
    }
    public void updateScores(RezultatIgre[] scores){
        polni(scores);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    public void doDrawing(Graphics g){


        g.drawLine(0, 0, 0, getHeight());

        int nY=getHeight()/8;
        try{
            for (int i = 0; i < 4; i++) {
                int x = 50 + naslednjiLik.x(i)*širinaKvadrata;
                int y = nY+širinaKvadrata+širinaKvadrata/2 - naslednjiLik.y(i)*višinaKvadrata;
                if(naslednjiLik.getLik().equals(Liki.LineShape)){
                    y+=širinaKvadrata;
                }
                drawSquare(g, x, y, naslednjiLik.getLik());
            }
            
            
            int sY=nY+višinaKvadrata*6;
            if(!shranjenLik.getLik().equals(Liki.NoShape))
                for (int i = 0; i < 4; i++) {
                    int x = 50 + shranjenLik.x(i)*širinaKvadrata;
                    int y = sY+širinaKvadrata+širinaKvadrata/2 - shranjenLik.y(i)*višinaKvadrata;
                    if(shranjenLik.getLik().equals(Liki.LineShape))
                        y+=širinaKvadrata;
                    drawSquare(g, x, y, shranjenLik.getLik());
                }

            g.setColor(Color.BLACK);
            g.fillRect(0, nY-30, getWidth(), 2);
            g.fillRect(0, sY-30, getWidth(), 2);
            g.fillRect(0, sY+višinaKvadrata*6-30, getWidth(), 2);
            g.setFont(new Font("default", Font.PLAIN, 20));
            g.drawString("Naslednji lik:",20,nY);
            g.drawString("Shranjen lik:",20,sY);
            g.drawString("Level: "+level, 10, sY+višinaKvadrata*6);
            g.setFont(new Font("default", Font.PLAIN, 12));
            g.drawString("odstranjene vrstice: "+štVrstic, 10, sY+višinaKvadrata*7);
            g.drawString("Št vrstic do levla: "+(NivoIgre.getLinesToLevel(level)-štVrstic), 10, sY+višinaKvadrata*8);
            g.drawString(statusbar, 5, getHeight()/16);
        }catch(NullPointerException ignored){}
    }
}