package tetris;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

public class Tetris extends JFrame {

    public Tetris() {
        initUI();
    }

    private void initUI() {
        var display =new InfoDisplay();
        var deska = new Deska(display);

        add(display,BorderLayout.EAST);
        display.setPreferredSize(new Dimension(150,getHeight()));
        add(deska,BorderLayout.CENTER);

        deska.start();

        setTitle("Tetris");
        setSize(520, 800);
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var game = new Tetris();
            game.setVisible(true);
        });
    }
}
