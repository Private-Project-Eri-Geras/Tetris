package com.leviatanes.menus;

import java.awt.Color;

import javax.swing.JLabel;

import com.leviatanes.tetris.Main;

/**
 *
 * @author maria
 */
public class Minicio extends javax.swing.JPanel {// El panel de inicio
    private escalaImag escal = new escalaImag();
    private Main main;

    // Offset para el fondo1
    private final int fondoHolderYoffset = 0;
    private final int fondo1HolderXoffset = 0;
    private final int fondoHolderWidth = 20;
    private final int fondoHolderHeight = 80;
    // offset para el fondo2
    private final int fondo2HolderXoffset = 70;

    // offset para el titulo
    private final int tituloHolderYoffset = 10;
    private final int tituloHolderXoffset = 25;
    private final int tituloHolderWidth = 40;
    private final int tituloHolderHeight = 12;
    // offset para los botones
    private final int btnPHolderXoffset = 33;// Es el mismo para todos los botones
    private final int btnPHolderWidth = 24;
    private final int btnPHolderHeight = 8;

    private final int playPHolderYoffset = 28; // La Y es la que va a cambiar en cada botón
    private final int scorePHolderYoffset = 41; // La Y es la que va a cambiar en cada botón
    private final int configPHolderYoffset = 54; // La Y es la que va a cambiar en cada botón
    private final int exitPHolderYoffset = 67; // La Y es la que va a cambiar en cada botón

    public Minicio(int width, int height, int multiplier, Main main) {
        this.main = main;
        initComponents();
        initPanel(width, height, multiplier);
    }

    private void initPanel(int width, int height, int multiplier) {
        this.setLayout(null);
        System.out.println("Panel Inicio: ancho: " + width + ", alto: " + height);
        this.setBounds(0, 0, width, height);
        setBackground(new java.awt.Color(142, 76, 236));

        int x, y, w, h;
        // =====================[TITULO]=======================
        titulo = new JLabel();
        x = tituloHolderXoffset * multiplier;
        y = tituloHolderYoffset * multiplier;
        w = tituloHolderWidth * multiplier;
        h = tituloHolderHeight * multiplier;
        titulo.setBounds(x, y, w, h);
        System.out.println("\nTITULO: w:" + titulo.getWidth() + ", h" + titulo.getHeight() + ", x:" + titulo.getX()
                + ", y:" + titulo.getY());

        escal.escalarLabel(titulo, "/com/leviatanes/images/Tetris.png", multiplier);
        this.add(titulo);
        // =====================[FONDOS]=======================
        fondo1 = new JLabel();
        x = fondo1HolderXoffset * multiplier;
        y = fondoHolderYoffset * multiplier;
        w = fondoHolderWidth * multiplier;
        h = fondoHolderHeight * multiplier;
        fondo1.setBounds(x, y, w, h);
        System.out.println("\nFONDO1: w:" + fondo1.getWidth() + ", h" + fondo1.getHeight() + ", x:" + fondo1.getX()
                + ", y:" + fondo1.getY());
        escal.escalarLabel(fondo1, "/com/leviatanes/images/FondoT.png", multiplier);
        this.add(fondo1);

        fondo2 = new JLabel();
        x = fondo2HolderXoffset * multiplier;
        fondo2.setBounds(x, y, w, h);
        System.out.println("\nFondo2: w: " + fondo2.getWidth() + ", h: " + fondo2.getHeight() + ", x:" + fondo2.getX()
                + ", y:" + fondo2.getY());
        escal.escalarLabel(fondo2, "/com/leviatanes/images/FondoT.png", multiplier);
        this.add(fondo2);
        // =====================[BOTONES]=======================
        // =====================[PLAY]=======================
        playBtn = new JLabel();
        playBtn.setBackground(new java.awt.Color(38, 185, 193));
        playBtn.setOpaque(true);
        playBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main.initGame();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEntry(playBtn);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(playBtn);
            }
        });
        x = btnPHolderXoffset * multiplier;
        y = playPHolderYoffset * multiplier;
        w = btnPHolderWidth * multiplier;
        h = btnPHolderHeight * multiplier;
        playBtn.setBounds(x, y, w, h);
        setText(playBtn, "PLAY");
        playBtn.setForeground(new Color(20, 20, 20));
        this.adjustFontSize(playBtn, playBtn.getFont().getFontName(), w, h);
        this.add(playBtn);
        // =====================[SCORE]=======================
        scoreBtn = new JLabel();
        scoreBtn.setBackground(new java.awt.Color(38, 185, 193));
        scoreBtn.setOpaque(true);
        scoreBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main.menuScore();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEntry(scoreBtn);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(scoreBtn);
            }
        });
        y = scorePHolderYoffset * multiplier;
        scoreBtn.setBounds(x, y, w, h);
        setText(scoreBtn, "SCORE");
        scoreBtn.setForeground(new Color(20, 20, 20));
        this.adjustFontSize(scoreBtn, scoreBtn.getFont().getFontName(), w, h);
        this.add(scoreBtn);
        scoreBtn.setVisible(true);
        System.out.println("\nSCORE: w:" + w + ", h" + h + ", x:" + x + ", y:" + y);
        // =====================[CONFIG]=======================
        configBtn = new JLabel();
        configBtn.setBackground(new java.awt.Color(38, 185, 193));
        configBtn.setOpaque(true);
        configBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main.settingsMenu();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEntry(configBtn);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(configBtn);
            }
        });
        y = configPHolderYoffset * multiplier;
        configBtn.setBounds(x, y, w, h);
        setText(configBtn, "CONFIG");
        configBtn.setForeground(new Color(20, 20, 20));
        this.adjustFontSize(configBtn, configBtn.getFont().getFontName(), w, h);
        this.add(configBtn);
        configBtn.setVisible(true);
        System.out.println("\nCONFIG: w:" + configP.getWidth() + ", h" + configP.getHeight() + ", x:" + configP.getX()
                + ", y:" + configP.getY());
        // =====================[EXIT]=======================
        exitBtn = new JLabel();
        exitBtn.setBackground(new java.awt.Color(38, 185, 193));
        exitBtn.setOpaque(true);
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEntry(exitBtn);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(exitBtn);
            }
        });

        y = exitPHolderYoffset * multiplier;
        exitBtn.setBounds(x, y, w, h);
        setText(exitBtn, "EXIT");
        exitBtn.setForeground(new Color(20, 20, 20));
        this.adjustFontSize(exitBtn, exitBtn.getFont().getFontName(), w, h);
        this.add(exitBtn);
        exitP.setVisible(true);
        System.out.println("\nEXIT: w:" + exitP.getWidth() + ", h" + exitP.getHeight() + ", x:" + exitP.getX() + ", y:"
                + exitP.getY());

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    private void adjustFontSize(JLabel label, String font, int w, int h) {
        for (int i = 1;; i++) {
            label.setFont(new java.awt.Font(font, 1, i));
            int fontW = label.getFontMetrics(label.getFont()).stringWidth(label.getText());
            int fontH = label.getFontMetrics(label.getFont()).getHeight();
            if (fontW > w || fontH > h) {
                label.setFont(new java.awt.Font(font, 1, i - 1));
                break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo1 = new javax.swing.JLabel();
        fondo2 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        playP = new javax.swing.JPanel();
        playBtn = new javax.swing.JLabel();
        scoreP = new javax.swing.JPanel();
        scoreBtn = new javax.swing.JLabel();
        configP = new javax.swing.JPanel();
        configBtn = new javax.swing.JLabel();
        exitP = new javax.swing.JPanel();
        exitBtn = new javax.swing.JLabel();

        setBackground(new java.awt.Color(142, 76, 236));

        playP.setBackground(new java.awt.Color(38, 185, 193));

        playBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        playBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playBtn.setText("PLAY");
        playBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main.initGame();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEntry(playBtn);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseExit(playBtn);
            }
        });

        javax.swing.GroupLayout playPLayout = new javax.swing.GroupLayout(playP);
        playP.setLayout(playPLayout);
        playPLayout.setHorizontalGroup(
                playPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE));
        playPLayout.setVerticalGroup(
                playPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE));

        scoreP.setBackground(new java.awt.Color(38, 185, 193));

        scoreBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        scoreBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreBtn.setText("SCORE");

        javax.swing.GroupLayout scorePLayout = new javax.swing.GroupLayout(scoreP);
        scoreP.setLayout(scorePLayout);
        scorePLayout.setHorizontalGroup(
                scorePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scoreBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        scorePLayout.setVerticalGroup(
                scorePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scoreBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE));

        configP.setBackground(new java.awt.Color(38, 185, 193));

        configBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        configBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        configBtn.setText("CONFIG");

        javax.swing.GroupLayout configPLayout = new javax.swing.GroupLayout(configP);
        configP.setLayout(configPLayout);
        configPLayout.setHorizontalGroup(
                configPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(configBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        configPLayout.setVerticalGroup(
                configPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(configBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE));

        exitP.setBackground(new java.awt.Color(38, 185, 193));

        exitBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        exitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitBtn.setText("EXIT");

        javax.swing.GroupLayout exitPLayout = new javax.swing.GroupLayout(exitP);
        exitP.setLayout(exitPLayout);
        exitPLayout.setHorizontalGroup(
                exitPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE));
        exitPLayout.setVerticalGroup(
                exitPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(fondo1, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(playP, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                .addComponent(configP,
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(scoreP,
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(exitP,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30,
                                        Short.MAX_VALUE)
                                .addComponent(fondo2, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fondo1, javax.swing.GroupLayout.PREFERRED_SIZE, 580,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fondo2, javax.swing.GroupLayout.PREFERRED_SIZE, 580,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(playP, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(scoreP, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(configP, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(exitP, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)));
    }// </editor-fold>//GEN-END:initComponents

    private void setText(JLabel label, String text) {
        int width = label.getWidth();
        int height = label.getHeight();
        label.setText(text);
        for (int i = 1;; i++) {
            label.setFont(new java.awt.Font("Impact", 1, i));
            if (label.getFontMetrics(label.getFont()).stringWidth(text) > width
                    || label.getFontMetrics(label.getFont()).getHeight() > height) {
                label.setFont(new java.awt.Font("Impact", 1, i - 1));
                break;
            }
        }
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }

    private void mouseEntry(JLabel label) {
        label.setBackground(new java.awt.Color(46, 83, 197));
    }

    private void mouseExit(JLabel label) {
        label.setBackground(new java.awt.Color(38, 185, 193));
    }

    /*
     * private void playBtnMouseEntered(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_playBtnMouseEntered
     * //Camabio de color del botón
     * playBtn.setBackground(new java.awt.Color(46, 83, 197));
     * }//GEN-LAST:event_playBtnMouseEntered
     * 
     * private void playBtnMouseExited(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_playBtnMouseExited
     * // Regresa a su color
     * playBtn.setBackground(new java.awt.Color(38, 185, 193));
     * }//GEN-LAST:event_playBtnMouseExited
     * 
     * private void scoreBtnMouseEntered(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_scoreBtnMouseEntered
     * //Camabio de color del botón
     * scoreP.setBackground(new java.awt.Color(46, 83, 197));
     * }//GEN-LAST:event_scoreBtnMouseEntered
     * 
     * private void scoreBtnMouseExited(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_scoreBtnMouseExited
     * // Regresa a su color
     * scoreP.setBackground(new java.awt.Color(38, 185, 193));
     * }//GEN-LAST:event_scoreBtnMouseExited
     * 
     * private void configBtnMouseEntered(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_configBtnMouseEntered
     * //Camabio de color del botón
     * configP.setBackground(new java.awt.Color(46, 83, 197));
     * }//GEN-LAST:event_configBtnMouseEntered
     * 
     * private void configBtnMouseExited(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_configBtnMouseExited
     * // Regresa a su color
     * configP.setBackground(new java.awt.Color(38, 185, 193));
     * }//GEN-LAST:event_configBtnMouseExited
     * 
     * private void exitBtnMouseEntered(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_exitBtnMouseEntered
     * //Camabio de color del botón
     * exitP.setBackground(new java.awt.Color(46, 83, 197));
     * }//GEN-LAST:event_exitBtnMouseEntered
     * 
     * private void exitBtnMouseExited(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_exitBtnMouseExited
     * // Regresa a su color
     * exitP.setBackground(new java.awt.Color(38, 185, 193));
     * }//GEN-LAST:event_exitBtnMouseExited
     * 
     * private void exitBtnMouseClicked(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_exitBtnMouseClicked
     * // Sale del programa
     * System.exit(0);
     * }//GEN-LAST:event_exitBtnMouseClicked
     * 
     * private void playBtnMouseClicked(java.awt.event.MouseEvent evt)
     * {//GEN-FIRST:event_playBtnMouseClicked
     * // Va a la pestaña de juego
     * //De momento solo inicializa el juego
     * 
     * }//GEN-LAST:event_playBtnMouseClicked
     * 
     */

    /*
     * private void printImage(JLabel label, String ruta){//Para ajusstar la imagen
     * al JLabel
     * ImageIcon imagen= new ImageIcon(ruta);
     * ImageIcon icono= new
     * ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(),
     * label.getHeight(), Image.SCALE_DEFAULT));
     * label.setIcon(icono);
     * this.repaint();
     * }
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel configBtn;
    private javax.swing.JPanel configP;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JPanel exitP;
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondo2;
    private javax.swing.JLabel playBtn;
    private javax.swing.JPanel playP;
    private javax.swing.JLabel scoreBtn;
    private javax.swing.JPanel scoreP;
    public static javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
