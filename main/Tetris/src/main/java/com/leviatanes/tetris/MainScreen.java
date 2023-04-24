package com.leviatanes.tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leviatanes.tetris.TetrisGame.TetrisPanel;

public class MainScreen extends javax.swing.JFrame {
    private TetrisPanel tetrisPanel;

    public MainScreen() {
        initComponents();
        // Menus
        JPanel menu1 = new JPanel();// Creando nuevo panel
        menu1.setLayout(new GridBagLayout());// Agregando GridLayout panel
        menu1.setBackground(Color.BLACK);// Agregando color al panel
        // Configurando la distribución de los elementos en menu1
        GridBagConstraints gridBagCon = new GridBagConstraints();
        gridBagCon.gridy = 0; // Primer elemento tiene coordenadas (1,0)
        gridBagCon.weightx = 1;
        gridBagCon.insets = new Insets(50, 50, 50, 50); // Para indicar el borde de cada uno

        // Creando elementos para el menu1
        JLabel titulo = new javax.swing.JLabel();// Nuevo JLabel
        titulo.setText("TETRIS");
        titulo.setToolTipText("");
        titulo.setFont(new Font("Impact", Font.BOLD, 50));
        titulo.setForeground(Color.RED);

        // Creando botones
        javax.swing.JButton boton1 = new javax.swing.JButton();
        boton1.setText("Jugar");
        boton1.setToolTipText("");
        boton1.setFont(new Font("Impact", Font.BOLD, 20));
        boton1.setForeground(Color.WHITE);
        boton1.setBackground(Color.BLUE);

        javax.swing.JButton boton2 = new javax.swing.JButton();
        boton2.setText("Score");
        boton2.setToolTipText("");
        boton2.setFont(new Font("Impact", Font.BOLD, 20));
        boton2.setForeground(Color.WHITE);
        boton2.setBackground(Color.BLUE);

        javax.swing.JButton boton3 = new javax.swing.JButton();
        boton3.setText("Config");
        boton3.setToolTipText("");
        boton3.setFont(new Font("Impact", Font.BOLD, 20));
        boton3.setForeground(Color.WHITE);
        boton3.setBackground(Color.BLUE);

        javax.swing.JButton boton4 = new javax.swing.JButton();
        boton4.setText("Exit");
        boton4.setToolTipText("");
        boton4.setFont(new Font("Impact", Font.BOLD, 20));
        boton4.setForeground(Color.WHITE);
        boton4.setBackground(Color.BLUE);

        //
        menu1.add(titulo, gridBagCon);
        gridBagCon.gridy = 1; // Para que el siguiente elemento tenga coordenadas (1,1)
        gridBagCon.insets = new Insets(5, 5, 5, 5); // Para indicar el borde de cada uno
        menu1.add(boton1, gridBagCon);
        gridBagCon.gridy = 2;
        menu1.add(boton2, gridBagCon);
        gridBagCon.gridy = 3;
        menu1.add(boton3, gridBagCon);
        gridBagCon.gridy = 4;
        menu1.add(boton4, gridBagCon);

        menu1.setSize(720, 600);
        content.removeAll();
        content.add(menu1);
        content.revalidate();
        content.repaint();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        placeHolder = new javax.swing.JPanel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        placeHolder.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
                contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 720, Short.MAX_VALUE));
        contentLayout.setVerticalGroup(
                contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE));

        javax.swing.GroupLayout placeHolderLayout = new javax.swing.GroupLayout(placeHolder);
        placeHolder.setLayout(placeHolderLayout);
        placeHolderLayout.setHorizontalGroup(
                placeHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        placeHolderLayout.setVerticalGroup(
                placeHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(placeHolder, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(placeHolder, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JPanel placeHolder;
    // End of variables declaration//GEN-END:variables
}
