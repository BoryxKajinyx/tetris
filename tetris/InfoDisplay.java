package tetris;

import tetris.Lik.Liki;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
public class InfoDisplay extends JPanel{
    int širinaKvadrata;
    int višinaKvadrata;

    public void setŠirinaKvadrata(int širinaKvadrata) {
        this.širinaKvadrata = širinaKvadrata;
    }

    public void setVišinaKvadrata(int višinaKvadrata) {
        this.višinaKvadrata = višinaKvadrata;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    public void doDrawing(Graphics g){
        
    }
}
