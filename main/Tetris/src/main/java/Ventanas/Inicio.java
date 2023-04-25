/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Ventanas;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author maria
 */
public class Inicio extends javax.swing.JPanel {
    int mouseX, mouseY;
    private ImageIcon imagen;
    private Icon icono;
    Escalar escal= new Escalar();
    
    //Offset para el fondo1
    private final int fondo1HolderYoffset=30;
    private final int fondo1HolderXoffset=30;
    private final int fondo1HolderWidth=150;
    private final int fondo1HolderHeight=580;
    //offset para el fondo2
    private final int fondo2HolderYoffset=30;
    private final int fondo2HolderXoffset=540;
    private final int fondo2HolderWidth=150;
    private final int fondo2HolderHeight=580;
    //offset para el titulo
    private final int tituloHolderYoffset=30;
    private final int tituloHolderXoffset=210;
    private final int tituloHolderWidth=300;
    private final int tituloHolderHeight=100;
    //offset para el playP
    private final int playPHolderYoffset=225;
    private final int playPHolderXoffset=260;
    private final int playPHolderWidth=200;
    private final int playPHolderHeight=60;
    /*//offset para el playBtn
    private final int playBtnHolderYoffset=225;
    private final int playBtnHolderXoffset=260;
    private final int playBtnHolderWidth=200;
    private final int playBtnHolderHeight=60;*/
    //offset para el scoreP
    private final int scorePHolderYoffset=315;
    private final int scorePHolderXoffset=260;
    private final int scorePHolderWidth=200;
    private final int scorePHolderHeight=60;
   /* //offset para el scoreBtn
    private final int scoreBtnHolderYoffset=315;
    private final int scoreBtnHolderXoffset=260;
    private final int scoreBtnHolderWidth=200;
    private final int scoreBtnHolderHeight=60;*/
    //offset para el configP
    private final int configPHolderYoffset=405;
    private final int configPHolderXoffset=260;
    private final int configPHolderWidth=200;
    private final int configPHolderHeight=60;
    /*//offset para el configBtn
    private final int configBtnHolderYoffset=405;
    private final int configBtnHolderXoffset=260;
    private final int configBtnHolderWidth=200;
    private final int configBtnHolderHeight=60;*/
    //offset para el exitP
    private final int exitPHolderYoffset=495;
    private final int exitPHolderXoffset=260;
    private final int exitPHolderWidth=200;
    private final int exitPHolderHeight=60;
    /*//offset para el exitBtn
    private final int exitBtnHolderYoffset=495;
    private final int exitBtnHolderXoffset=260;
    private final int exitBtnHolderWidth=200;
    private final int exitBtnHolderHeight=60;*/
    
    public Inicio(int width, int height, int multiplier) {
        initComponents();
        initPanel( width,  height,  multiplier);
        
       // escal.escalarLabel(fondo1, "/imag/FondoT.png");
        //this.printImage(titulo, "/imag/Tetris.png");
    }

    private void initPanel(int width, int height, int multiplier){
         fondo1.setBounds(fondo1HolderXoffset * multiplier, fondo1HolderYoffset * multiplier,
                fondo1HolderWidth * multiplier, fondo1HolderHeight * multiplier);
        fondo2.setBounds(fondo2HolderXoffset * multiplier, fondo2HolderYoffset * multiplier,
                fondo2HolderWidth * multiplier, fondo2HolderHeight * multiplier);
        titulo.setBounds(tituloHolderXoffset * multiplier, tituloHolderYoffset * multiplier,
                tituloHolderWidth * multiplier, tituloHolderHeight * multiplier);
        playP.setBounds(playPHolderXoffset * multiplier, playPHolderYoffset * multiplier,
                playPHolderWidth * multiplier, playPHolderHeight * multiplier);
        playBtn.setBounds(playPHolderXoffset * multiplier, playPHolderYoffset * multiplier,
                playPHolderWidth * multiplier, playPHolderHeight * multiplier);
        scoreP.setBounds(scorePHolderXoffset * multiplier, scorePHolderYoffset * multiplier,
                scorePHolderWidth * multiplier, scorePHolderHeight * multiplier);
        scoreBtn.setBounds(scorePHolderXoffset * multiplier, scorePHolderYoffset * multiplier,
                scorePHolderWidth * multiplier, scorePHolderHeight * multiplier);
        configP.setBounds(configPHolderXoffset * multiplier, configPHolderYoffset * multiplier,
                configPHolderWidth * multiplier, configPHolderHeight * multiplier);
        configBtn.setBounds(configPHolderXoffset * multiplier, configPHolderYoffset * multiplier,
                configPHolderWidth * multiplier, configPHolderHeight * multiplier);
        exitP.setBounds(exitPHolderXoffset * multiplier, exitPHolderYoffset * multiplier,
                exitPHolderWidth * multiplier, exitPHolderHeight * multiplier);
        exitBtn.setBounds(exitPHolderXoffset * multiplier, exitPHolderYoffset * multiplier,
                exitPHolderWidth * multiplier, exitPHolderHeight * multiplier);
        
        escal.escalarLabel(titulo, "/imag/Tetris.png", multiplier);
        escal.escalarLabel(fondo1, "/imag/FondoT.png", multiplier);
        escal.escalarLabel(fondo2, "/imag/FondoT.png", multiplier);
        this.setSize(width, height);
        this.setVisible(true);
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        playBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        playBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playBtn.setText("PLAY");

        javax.swing.GroupLayout playPLayout = new javax.swing.GroupLayout(playP);
        playP.setLayout(playPLayout);
        playPLayout.setHorizontalGroup(
            playPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        playPLayout.setVerticalGroup(
            playPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        scoreBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        scoreBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreBtn.setText("SCORE");

        javax.swing.GroupLayout scorePLayout = new javax.swing.GroupLayout(scoreP);
        scoreP.setLayout(scorePLayout);
        scorePLayout.setHorizontalGroup(
            scorePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scoreBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        scorePLayout.setVerticalGroup(
            scorePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scoreBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        configBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        configBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        configBtn.setText("CONFIG");

        javax.swing.GroupLayout configPLayout = new javax.swing.GroupLayout(configP);
        configP.setLayout(configPLayout);
        configPLayout.setHorizontalGroup(
            configPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(configBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        configPLayout.setVerticalGroup(
            configPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(configBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        exitBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        exitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitBtn.setText("EXIT");

        javax.swing.GroupLayout exitPLayout = new javax.swing.GroupLayout(exitP);
        exitP.setLayout(exitPLayout);
        exitPLayout.setHorizontalGroup(
            exitPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        exitPLayout.setVerticalGroup(
            exitPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(fondo1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(playP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(configP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scoreP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exitP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(fondo2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fondo1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fondo2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(playP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(scoreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(configP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(exitP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
    }// </editor-fold>//GEN-END:initComponents
    /*private void printImage(JLabel label, String ruta){//Para ajusstar la imagen al JLabel
        ImageIcon imagen= new ImageIcon(ruta);
        ImageIcon icono= new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icono);
        this.repaint();
    }*/

    
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
