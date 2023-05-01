package tetris;

import javax.swing.*;
import java.awt.*;

public class Glavni extends JFrame {

    private final ZačetniMeni začetniMeni;
    public Glavni(){
        začetniMeni = new ZačetniMeni();
        this.setTitle("Izbira barve");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        začetniMeni.addPropertyChangeListener(this::začetniMeniSpremembaLastnosti);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(začetniMeni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(začetniMeni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    public static Glavni okvir;
    private void začetniMeniSpremembaLastnosti(java.beans.PropertyChangeEvent evt) {
        Color[] c= začetniMeni.dobiIzbranoBarvo();
        if(c!=null&& začetniMeni.getName().equalsIgnoreCase("KonecIzbire")){
            this.setVisible(false);
            začetniMeni.setName("Igra");
            začetniMeni.setEnabled(false);
            Igra.igra(c);
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            okvir = new Glavni();
            okvir.setVisible(true);
        });
    }
}
