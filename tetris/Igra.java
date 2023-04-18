package tetris;

import javax.swing.*;
import java.awt.*;

public class Igra extends JFrame {

    public Igra(Color[] barve) {
        var stranskiPrikaz =new StranskiPrikaz();
        var deska = new Deska(stranskiPrikaz);

        add(stranskiPrikaz,BorderLayout.EAST);
        stranskiPrikaz.setPreferredSize(new Dimension(150,getHeight()));
        add(deska,BorderLayout.CENTER);
        deska.nastaviBarve(barve);
        stranskiPrikaz.nastaviBarve(barve);

        setTitle("Tetris");
        setSize(550, 800);
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        deska.začniIgro();
    }
    public static void igra(Color[] barve) {
        EventQueue.invokeLater(() -> {
            var igra = new Igra(barve);
            igra.setVisible(true);
        });
    }
}
