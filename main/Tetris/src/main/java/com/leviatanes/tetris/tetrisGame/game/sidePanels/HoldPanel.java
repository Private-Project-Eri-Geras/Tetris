package com.leviatanes.tetris.tetrisGame.game.sidePanels;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;

/**
 * [ PANEL DE LA PIEZA SIGUIENTE ]
 * Se encarga de mostrar un panel con la pieza siguiente
 * mostrando el color si esta activo o no
 * 
 * @author Leonardo
 * @author Eriarer (Abraham)
 * 
 * @see ShapeHolder
 * @see TetrisBlock
 */
public class HoldPanel extends javax.swing.JPanel {
    /** Label de la pieza siguiente */
    private JLabel holdedShapeLabel;
    /** Panel de la pieza siguiente */
    private ShapeHolder holdedShape;
    /** Multiplicador */
    private int multiplier;
    /** Path de la carpeta de imagenes */
    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/sidePanels/images/";
    // offset para el label de la pieza siguiente
    private final int nextShapeLabelYoffset = 1;
    private final int nextShapeLabelXoffset = 1;
    private final int nextShapeLabelWidth = 18;
    private final int nextShapeLabelHeight = 6;

    public HoldPanel(int multiplier) {
        initComponents();
        this.multiplier = multiplier;
        initPanels();
        this.setVisible(true);
    }

    public boolean isHoldAllowed() {
        return this.holdedShape.isHoldAllowed();
    }

    public void toogleHoldAllowed() {
        this.holdedShape.toggleHoldAllowed();
    }

    public void setHoldAllowed(boolean holdAllowed) {
        this.holdedShape.setHoldAllowed(holdAllowed);
    }

    public void setHoldedShape(TetrisBlock nextShape) {
        this.holdedShape.setBlock(nextShape);
    }

    public ShapeHolder getShapePlaceHolder() {
        return this.holdedShape;
    }

    public void repaintShape() {
        this.holdedShape.repaint();
    }

    private void initPanels() {
        this.holdedShapeLabel = new JLabel();
        initLabel(holdedShapeLabel, nextShapeLabelXoffset, nextShapeLabelYoffset, nextShapeLabelWidth,
                nextShapeLabelHeight, "Hold.png");

        this.add(holdedShapeLabel);

        this.holdedShape = new ShapeHolder(multiplier);
        this.holdedShape.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.add(holdedShape);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    }

    /** Inicializa un label asignandole la imagen correspondiente */
    private void initLabel(JLabel label, int x, int y, int width, int height, String imagePath) {
        x = x * multiplier;
        y = y * multiplier;
        width = width * multiplier;
        height = height * multiplier;
        label.setBounds(x, y, width, height);
        getIcon(label, folderPath + imagePath, width, height);
    }

    /** Obtiene un icono y lo escala para colocarlo */
    private void getIcon(JLabel label, String imagePath, int width, int height) {
        ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
        Image img = image.getImage();
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 160, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 160, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}