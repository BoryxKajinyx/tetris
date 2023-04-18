package tetris;

import tetris.Lik.Liki;

import java.awt.*;

public class StartScreen extends javax.swing.JPanel {

    private Color[] colors;
    private final Color[] customC;
    private Color[] selectedC;
    private boolean focusOnColorSliders;
    public StartScreen() {
        initComponents();
        colors= Barve.PRIVZETE_BARVE;
        customC=new Color[8];
        for(int i=0;i<customC.length;i++){
            customC[i]=new Color(0,0,0);
        }
        focusOnColorSliders=false;
        selectedC=null;
    }

    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        // Variables declaration - do not modify
        javax.swing.JButton jButton1 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        jSlider3 = new javax.swing.JSlider();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        jSlider4 = new javax.swing.JSlider();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator2 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(400, 500));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Privzete barve", "Originalne barve", "Poljubne barve" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jButton1.setText("Končaj izbiro");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jSlider1.setMaximum(255);
        jSlider1.setSnapToTicks(true);
        jSlider1.setEnabled(false);
        jSlider1.addChangeListener(this::jSliderStateChanged);
        jSlider1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSliderFocusGained();
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSliderFocusLost();
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modra");

        jSlider2.setMaximum(255);
        jSlider2.setSnapToTicks(true);
        jSlider2.setEnabled(false);
        jSlider2.addChangeListener(this::jSliderStateChanged);
        jSlider2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSliderFocusGained();
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSliderFocusLost();
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Zelena");

        jSlider3.setMaximum(255);
        jSlider3.setSnapToTicks(true);
        jSlider3.setEnabled(false);
        jSlider3.addChangeListener(this::jSliderStateChanged);
        jSlider3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSliderFocusGained();
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSliderFocusLost();
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Rdeča");

        jSlider4.setMaximum(6);
        jSlider4.setMinorTickSpacing(1);
        jSlider4.setPaintTicks(true);
        jSlider4.setSnapToTicks(true);
        jSlider4.setValue(0);
        jSlider4.addChangeListener(this::jSlider4StateChanged);

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
                                                        .addComponent(jSlider3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 97, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jSlider4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                                .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                selectedC = Barve.PRIVZETE_BARVE;
                break;
            case 1:
                selectedC = Barve.TETRIS_ORIGINALNE_BARVE;
                break;
            default:
                selectedC = customC;
                break;
        }
        setName("KonecIzbire");
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                colors = Barve.PRIVZETE_BARVE;
                jSlider1.setEnabled(false);
                jSlider2.setEnabled(false);
                jSlider3.setEnabled(false);
                break;
            case 1:
                colors = Barve.TETRIS_ORIGINALNE_BARVE;
                jSlider1.setEnabled(false);
                jSlider2.setEnabled(false);
                jSlider3.setEnabled(false);
                break;
            default:
                colors = customC;
                jSlider1.setEnabled(true);
                jSlider2.setEnabled(true);
                jSlider3.setEnabled(true);
                break;
        }
        jSlider1.setValue(colors[jSlider4.getValue()+1].getBlue());
        jSlider2.setValue(colors[jSlider4.getValue()+1].getGreen());
        jSlider3.setValue(colors[jSlider4.getValue()+1].getRed());
        repaint();
    }

    private void jSliderStateChanged(javax.swing.event.ChangeEvent evt) {
        if(focusOnColorSliders){
            colors[jSlider4.getValue()+1]=new Color(jSlider3.getValue(),jSlider2.getValue(),jSlider1.getValue());
        }
        repaint();
    }

    private void jSlider4StateChanged(javax.swing.event.ChangeEvent evt) {
        jSlider1.setValue(colors[jSlider4.getValue()+1].getBlue());
        jSlider2.setValue(colors[jSlider4.getValue()+1].getGreen());
        jSlider3.setValue(colors[jSlider4.getValue()+1].getRed());
        repaint();
    }

    private void jSliderFocusGained() {
        focusOnColorSliders=true;
    }

    private void jSliderFocusLost() {
        focusOnColorSliders=false;
    }

    private final int size=40;
    private void drawSquare(Graphics g, int x, int y) {
        var color = colors[jSlider4.getValue()+1];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, size -1, size -1 );
        g.setColor(color.darker());
        g.drawLine(x, y + size - 1, x, y);
        g.drawLine(x, y, x + size - 1, y);
        g.drawLine(x + 1, y + size - 1,
                x + size - 1, y + size - 1);
        g.drawLine(x + size - 1, y + size - 1,
                x + size - 1, y + 1);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    private void doDrawing(Graphics g){
        Lik[] liki=new Lik[7];
        for(int i=0;i<liki.length;i++){
            liki[i]=new Lik();
        }
        liki[0].setLik(Liki.ZShape);
        liki[1].setLik(Liki.SShape);
        liki[2].setLik(Liki.LineShape);
        liki[3].setLik(Liki.TShape);
        liki[4].setLik(Liki.SquareShape);
        liki[5].setLik(Liki.LShape);
        liki[6].setLik(Liki.MirroredLShape);
        for (int i = 0; i < 4; i++) {
            int x = 400/2-size/2 + liki[jSlider4.getValue()].x(i)*size;
            int y = 20+size+size/2 - liki[jSlider4.getValue()].y(i)*size;
            if(liki[jSlider4.getValue()].getLik().equals(Liki.LineShape)){
                y+=size;
            }
            drawSquare(g, x, y);
        }

    }

    public Color[] getSelectedColor(){
        return selectedC;
    }

    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    // End of variables declaration
}

