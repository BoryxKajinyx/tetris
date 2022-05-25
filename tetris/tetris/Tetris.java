package tetris;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;

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
