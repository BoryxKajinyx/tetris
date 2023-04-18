package tetris;

import javax.swing.*;
import java.awt.*;

public class Igra extends JFrame {

    public Igra(Color[] colors) {
        initUI(colors);
    }

    private void initUI(Color[] colors) {
        var display =new StranskiPrikaz();
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
            var game = new Igra(barve);
            game.setVisible(true);
        });
    }
}
