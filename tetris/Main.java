package tetris;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private StartScreen panel1;
    public Main(){
        initComponents();
    }

    public static Main start;

    private void initComponents() {

        panel1 = new tetris.StartScreen();
        this.setTitle("Izbira barve");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panel1.addPropertyChangeListener(this::panel1PropertyChange);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }
    private void panel1PropertyChange(java.beans.PropertyChangeEvent evt) {
        Color[] c=panel1.getSelectedColor();
        if(c!=null){
            Tetris.game(c);
            this.setVisible(false);
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            start= new Main();
            start.setVisible(true);
        });
    }
}
