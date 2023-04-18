package tetris;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {

    public Tetris(Color[] colors) {
        initUI(colors);
    }

    private void initUI(Color[] colors) {
        var display =new InfoDisplay();
        var deska = new Deska(display);

        add(display,BorderLayout.EAST);
        display.setPreferredSize(new Dimension(150,getHeight()));
        add(deska,BorderLayout.CENTER);
        deska.setBarve(colors);
        display.setColors(colors);

        setTitle("Tetris");
        setSize(550, 800);
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        deska.start();
    }

    public static void game(Color[] barve) {
        EventQueue.invokeLater(() -> {
            var game = new Tetris(barve);
            game.setVisible(true);
        });
    }
}
