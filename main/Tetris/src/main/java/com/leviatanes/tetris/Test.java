/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.leviatanes.tetris;

import Ventanas.Escalar;
import Ventanas.Inicio;
import static Ventanas.Inicio.titulo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import com.leviatanes.tetris.TetrisGame.TetrisPanel;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Eri
 */
public class Test extends javax.swing.JFrame {
    private TetrisPanel tetrisPanel;

   // Escalar escal= new Escalar();
    int mouseX, mouseY;
    private ImageIcon imagen;
    private Icon icono;

    final int BASE_WIDTH = 90;
    private final int BASE_HEIGHT = 80;
    private final int MATRIX_ROWS = 28;
    private final int MATRIX_COLUMNS = 2;

    // Variables
    private int width = 0;
    private int height = 0;
    public int multiplier = 1;

    // Get screen resolution
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenHeight = (int) screenSize.getHeight();

    // Create matrix with 28 different resolutions
    private int[][] resolution;

    /**
     * Creates new form Test
     */
    public Test() {
        initComponents();
        
        // obtener resolucion maxima
        this.setResizable(false);
        this.getMaxResolution();
        // generar centro de pantalla para el panel
        //this.generateCenter();
        this.setBounds(0,0,width, height);
        System.out.println("width: " + width + " height: " + height + " multiplier: " + multiplier);
        this.setLocationRelativeTo(this);//Para centrar el Frame //mio
        this.MenuInicio();
        this.revalidate();
        this.repaint();
        this.setVisible(true);
        // inicializar el juego
        //this.initGame();
    }

    private void getMaxResolution() {
        resolution = new int[MATRIX_ROWS][MATRIX_COLUMNS];
        resolution[0][0] = BASE_WIDTH;
        resolution[0][1] = BASE_HEIGHT;
        for (multiplier = 1; multiplier < MATRIX_ROWS && resolution[multiplier - 1][1] < screenHeight; multiplier++) {
            if (multiplier == 9)// Condición de paro. Multiplicador deseado +1
                break;
            resolution[multiplier][0] = resolution[0][0] * (multiplier + 1);
            resolution[multiplier][1] = resolution[0][1] * (multiplier + 1);
            width = resolution[multiplier - 1][0];
            height = resolution[multiplier - 1][1];
        }
        multiplier--;
        System.out.println("width: " + width + " height: " + height + " multiplier: " + multiplier);
    }

    private void generateCenter() {
        int x = (int) (screenSize.getWidth() - width) / 2;
        int y = (int) (screenSize.getHeight() - height) / 2;
        this.setLocation(x, y);
    }

    private void initGame() {
        this.setResizable(false);
        this.setSize(width, height);
        this.tetrisPanel = new TetrisPanel(width, height, multiplier);
        tetrisPanel.setSize(width, height);
        PlaceHolder.removeAll();
        PlaceHolder.add(tetrisPanel);
        PlaceHolder.revalidate();
        PlaceHolder.repaint();
        this.addKeyListener(tetrisPanel.getGameControls());
    }

    private void MenuInicio(){
        
        //this.setSize(width, height);
        //this.tetrisPanel = new TetrisPanel(width, height, multiplier);
        Inicio menuIni = new Inicio(width, height, multiplier);
        menuIni.setSize(width, height);
        menuIni.setLocation(0, 0);
        PlaceHolder.removeAll();
        PlaceHolder.add(menuIni);
        PlaceHolder.revalidate();
        PlaceHolder.repaint();
       // this.printImage(titulo, "src/main/java/imag/Tetris.png");
        
        //PlaceHolder.repaint();
        
        //this.addKeyListener(tetrisPanel.getGameControls());
    }

    //get de multiplier
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PlaceHolder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout PlaceHolderLayout = new javax.swing.GroupLayout(PlaceHolder);
        PlaceHolder.setLayout(PlaceHolderLayout);
        PlaceHolderLayout.setHorizontalGroup(
                PlaceHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 720, Short.MAX_VALUE));
        PlaceHolderLayout.setVerticalGroup(
                PlaceHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 640, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PlaceHolder, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PlaceHolder, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        new Test().setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PlaceHolder;
    // End of variables declaration//GEN-END:variables
}