package tetris;

import tetris.Lik.Liki;

import java.awt.*;

public class ZačetniMeni extends javax.swing.JPanel {

    private Color[] barve;
    private final Color[] poljubneBarve;
    private Color[] izbraneBarve;
    private boolean fokusNaDrsnikih;
    public ZačetniMeni() {
        inicializirajKomponente();
        barve = Barve.PRIVZETE_BARVE;
        poljubneBarve =new Color[8];
        for(int i = 0; i< poljubneBarve.length; i++){
            poljubneBarve[i]=new Color(0,0,0);
        }
        fokusNaDrsnikih =false;
        izbraneBarve =null;
    }

    private void inicializirajKomponente() {

        jComboBox1 = new javax.swing.JComboBox<>();
        // Variables declaration - do not modify
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        drsnikModra = new javax.swing.JSlider();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        drsnikZelena = new javax.swing.JSlider();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        drsnikRdeča = new javax.swing.JSlider();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        drsnikLiki = new javax.swing.JSlider();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator2 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(400, 500));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Privzete barve", "Standardne barve", "Poljubne barve" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jButton1.setText("Končaj izbiro");
        jButton1.addActionListener(this::gumb1Pritisnjen);

        drsnikModra.setMaximum(255);
        drsnikModra.setSnapToTicks(true);
        drsnikModra.setEnabled(false);
        drsnikModra.addChangeListener(this::barvniDrsnikiPremaknjeni);
        drsnikModra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fokusNaDrsnikih();
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fokusNaDrsnikihIzgubljen();
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modra");

        drsnikZelena.setMaximum(255);
        drsnikZelena.setSnapToTicks(true);
        drsnikZelena.setEnabled(false);
        drsnikZelena.addChangeListener(this::barvniDrsnikiPremaknjeni);
        drsnikZelena.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fokusNaDrsnikih();
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fokusNaDrsnikihIzgubljen();
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Zelena");

        drsnikRdeča.setMaximum(255);
        drsnikRdeča.setSnapToTicks(true);
        drsnikRdeča.setEnabled(false);
        drsnikRdeča.addChangeListener(this::barvniDrsnikiPremaknjeni);
        drsnikRdeča.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fokusNaDrsnikih();
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fokusNaDrsnikihIzgubljen();
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Rdeča");

        drsnikLiki.setMaximum(6);
        drsnikLiki.setMinorTickSpacing(1);
        drsnikLiki.setPaintTicks(true);
        drsnikLiki.setSnapToTicks(true);
        drsnikLiki.setValue(0);
        drsnikLiki.addChangeListener(this::LikovniDrsnikPremaknjen);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Lik");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(97, 97, 97)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(drsnikRdeča, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(drsnikZelena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(drsnikModra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 97, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(drsnikLiki, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(drsnikLiki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(drsnikRdeča, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(drsnikZelena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(drsnikModra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }

    private void gumb1Pritisnjen(java.awt.event.ActionEvent evt) {
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                izbraneBarve = Barve.PRIVZETE_BARVE;
                break;
            case 1:
                izbraneBarve = Barve.TETRIS_STANDARDNE_BARVE;
                break;
            default:
                izbraneBarve = poljubneBarve;
                break;
        }
        setName("KonecIzbire");
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                barve = Barve.PRIVZETE_BARVE;
                drsnikModra.setEnabled(false);
                drsnikZelena.setEnabled(false);
                drsnikRdeča.setEnabled(false);
                break;
            case 1:
                barve = Barve.TETRIS_STANDARDNE_BARVE;
                drsnikModra.setEnabled(false);
                drsnikZelena.setEnabled(false);
                drsnikRdeča.setEnabled(false);
                break;
            default:
                barve = poljubneBarve;
                drsnikModra.setEnabled(true);
                drsnikZelena.setEnabled(true);
                drsnikRdeča.setEnabled(true);
                break;
        }
        drsnikModra.setValue(barve[drsnikLiki.getValue()+1].getBlue());
        drsnikZelena.setValue(barve[drsnikLiki.getValue()+1].getGreen());
        drsnikRdeča.setValue(barve[drsnikLiki.getValue()+1].getRed());
        repaint();
    }

    private void barvniDrsnikiPremaknjeni(javax.swing.event.ChangeEvent evt) {
        if(fokusNaDrsnikih){
            barve[drsnikLiki.getValue()+1]=new Color(drsnikRdeča.getValue(), drsnikZelena.getValue(), drsnikModra.getValue());
        }
        repaint();
    }

    private void LikovniDrsnikPremaknjen(javax.swing.event.ChangeEvent evt) {
        drsnikModra.setValue(barve[drsnikLiki.getValue()+1].getBlue());
        drsnikZelena.setValue(barve[drsnikLiki.getValue()+1].getGreen());
        drsnikRdeča.setValue(barve[drsnikLiki.getValue()+1].getRed());
        repaint();
    }

    private void fokusNaDrsnikih() {
        fokusNaDrsnikih =true;
    }

    private void fokusNaDrsnikihIzgubljen() {
        fokusNaDrsnikih =false;
    }

    private final int velikost =40;
    private void rišiDelLika(Graphics g, int x, int y) {
        var color = barve[drsnikLiki.getValue()+1];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, velikost -1, velikost -1 );
        g.setColor(color.darker());
        g.drawLine(x, y + velikost - 1, x, y);
        g.drawLine(x, y, x + velikost - 1, y);
        g.drawLine(x + 1, y + velikost - 1,
                x + velikost - 1, y + velikost - 1);
        g.drawLine(x + velikost - 1, y + velikost - 1,
                x + velikost - 1, y + 1);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        riši(g);
    }
    private void riši(Graphics g){
        Lik[] liki=new Lik[7];
        for(int i=0;i<liki.length;i++){
            liki[i]=new Lik();
        }
        liki[0].setLik(Liki.Z_Oblika);
        liki[1].setLik(Liki.S_Oblika);
        liki[2].setLik(Liki.Črta);
        liki[3].setLik(Liki.T_oblika);
        liki[4].setLik(Liki.Kvadrat);
        liki[5].setLik(Liki.L_Oblika);
        liki[6].setLik(Liki.Obrnjen_L);
        for (int i = 0; i < 4; i++) {
            int x = 400/2- velikost /2 + liki[drsnikLiki.getValue()].x(i)* velikost;
            int y = 20+ velikost + velikost /2 - liki[drsnikLiki.getValue()].y(i)* velikost;
            if(liki[drsnikLiki.getValue()].dobiLik().equals(Liki.Črta)){
                y+= velikost;
            }
            rišiDelLika(g, x, y);
        }

    }

    public Color[] dobiIzbranoBarvo(){
        return izbraneBarve;
    }

    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JSlider drsnikModra;
    private javax.swing.JSlider drsnikZelena;
    private javax.swing.JSlider drsnikRdeča;
    private javax.swing.JSlider drsnikLiki;
    // End of variables declaration
}

