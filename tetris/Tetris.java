package tetris;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Tetris extends JFrame {

    private JLabel statusbar;

    public Tetris() {

        initUI();
    }

    private void initUI() {

        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        var display =new InfoDisplay();
        var deska = new Deska(this,display);
        add(display,BorderLayout.WEST);
        add(deska,BorderLayout.CENTER);
        deska.start();

        setTitle("Tetris");
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    JLabel getStatusBar() {

        return statusbar;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var game = new Tetris();
            game.setVisible(true);
        });
    }
}
