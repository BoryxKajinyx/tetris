package tetris;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

public class Tetris extends JFrame {

    public Tetris() {
        initUI();
    }

    private void initUI() {
        var display =new InfoDisplay();
        var deska = new Deska(this,display);

        add(deska,BorderLayout.CENTER);
        add(display,BorderLayout.EAST);

        deska.start();

        setTitle("Tetris");
        setSize(500, 800);
        setResizable(false);
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
