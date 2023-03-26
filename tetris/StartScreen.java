package tetris;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import tetris.Lik.Liki;

public class StartScreen extends JPanel{

    Color[] defaultColors={
        new Color(0, 0, 0), new Color(204, 102, 102),
        new Color(102, 204, 102), new Color(0, 255, 255),
        new Color(204, 204, 102), new Color(204, 102, 204),
        new Color(102, 204, 204), new Color(218, 170, 0)
    };
    Color[] ogTetris={
        new Color(0, 0, 0), new Color(255, 0, 0),
        new Color(0, 255, 0), new Color(102, 102, 204),
        new Color(153, 0, 255), new Color(255, 255, 0),
        new Color(255, 170, 0), new Color(0,0,255)
    };
    private void drawSquare(Graphics g, int x, int y, Liki shape) {
        Color colors[] =defaultColors;
        var color = colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, 10 -1, 10 -1 );
        g.setColor(color.darker());
        g.drawLine(x, y + 10 - 1, x, y);
        g.drawLine(x, y, x + 10 - 1, y);
        g.drawLine(x + 1, y + 10 - 1,
                 x + 10 - 1, y + 10 - 1);
        g.drawLine(x + 10 - 1, y + 10 - 1,
                 x + 10 - 1, y + 1);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    public void doDrawing(Graphics g){
        Lik[] liki=new Lik[7];
        liki[0].setLik(Liki.ZShape);
        liki[1].setLik(Liki.SShape);
        liki[2].setLik(Liki.LineShape);
        liki[3].setLik(Liki.TShape);
        liki[4].setLik(Liki.SquareShape);
        liki[5].setLik(Liki.LShape);
        liki[6].setLik(Liki.MirroredLShape);

    }
}
