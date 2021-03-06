package tetris;

import tetris.Lik.Liki;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JPanel;
public class InfoDisplay extends JPanel{

    private int širinaKvadrata;
    private int višinaKvadrata;
    private int level;
    private int štVrstic;

    private Lik shranjenLik;
    private Lik naslednjiLik;

    private JLabel label;
    private String statusbar;

    public InfoDisplay() {
        label=new JLabel("▒▒▒▒▒▒▒▒▒▒▒");
        add(label,BorderLayout.SOUTH);
    }
    

    private void drawSquare(Graphics g, int x, int y, Liki shape) {
        Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };
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
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    public void doDrawing(Graphics g){
        
        g.drawLine(0, 0, 0, getHeight());

        int nY=getHeight()/8;
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
        g.drawString("Št vrstic do levla: "+(Level.getLinesToLevel(level)-štVrstic), 10, sY+višinaKvadrata*8);
        g.drawString(statusbar, 5, getHeight()/16);
    }
}