package com.leviatanes.tetris.tetrisGame.game.nextShape;

import java.awt.Color;
import java.awt.Graphics;

import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;

public class ShapeHolder extends javax.swing.JPanel {
    /** TetrisBlock Actual */
    private TetrisBlock block;
    /** Tamaño del tile a dibujar */
    private int tileSize;
    /** Cantidad de filas */
    private int rows;
    /** Cantidad de columnas */
    private int colums;
    /**
     * matriz del color de fondo
     * 
     * @apiNote bacgroun[0] = color oscuro de fondo
     * @apiNote bacgroun[1] = color claro de fondo
     * @apiNote bacgroun[2] = color del borde
     */
    private Color[][][] background;
    /**
     * Offset del cuadrado interior a dibujar es un 15% del tamaño de balsoza
     */
    private int drawOffset;
    private int drawOffset2;
    /** color oscuro @apiNote rgb = (20,20,20) */
    private static final Color darkColor = new Color(20, 20, 20);
    /** color brillante @apiNote rgb = (30,30,30) */
    private static final Color lightColor = new Color(30, 30, 30);
    /** color brillante @apiNote rgb = (100,100,100) */
    private static final Color borderColor = new Color(100, 100, 100);
    // offset para el placeholder de la pieza siguiente
    private final int nextShapePlaceHolderYoffset = 7;
    private final int nextShapePlaceHolderXoffset = 4;
    private final int nextShapePlaceSides = 12;

    public ShapeHolder(int multiplier) {
        initComponents();
        this.init(multiplier);
        this.colums = 4;
        this.tileSize = this.getBounds().width / colums;
        this.rows = 4;

        this.background = new Color[3][rows][colums];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                background[0][i][j] = darkColor;
                background[1][i][j] = lightColor;
                background[2][i][j] = borderColor;
            }
        }
        this.drawOffset = (int) (tileSize * 0.20);
        this.drawOffset2 = -(2 * drawOffset) + 1;
        this.setVisible(true);
        System.out.println("ShapeHolder creado");
    }

    public void setBlock(TetrisBlock block) {
        String blockType = block.getType() + "shape";
        // create a new instance of the block
        // using generics checking the type of the block
        // and casting it to the correct type
        // and not using a deprecated method
        Object newBlock = null;
        try {
            newBlock = Class.forName("com.leviatanes.tetris.tetrisGame.tetrisBlocks.tetrinominos." + blockType)
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.block = (TetrisBlock) newBlock;

        if (block.getType() == 'I') {
            this.block.setX(0);
            this.block.setY(2);
        } else if (block.getType() == 'O') {
            this.block.setX(1);
            this.block.setY(1);
        } else {
            this.block.setX(0);
            this.block.setY(1);
        }

        this.repaint();
    }

    /**
     * Dibuja el fondo del tablero
     * dibuja primero el color oscuro y luego el claro
     * 
     * @param g Graphics
     */
    private void drawBackGround(Graphics g) {
        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.colums; x++) {
                drawGameSquare(g, y, x, background[0][y][x], background[1][y][x], background[2][y][x]);
            }
        }
    }

    /**
     * Dibuja el bloque que
     * estamos controlando
     * 
     * @param g Graphics
     */
    private void drawBlock(Graphics g) {
        if (block == null)
            return;
        int yi;
        int xi;
        Color darkColor = block.getDarkColor();
        Color brigthColor = block.getLightColor();
        Color olColor = block.getBorderColor();
        int heigth = block.getHeight();
        int width = block.getWidth();
        for (int row = 0; row < heigth; row++) {
            for (int col = 0; col < width; col++) {
                if (block.getBlock()[row][col] == 1) {
                    yi = row + block.getY();
                    xi = col + block.getX();
                    this.drawGameSquare(g, yi, xi, darkColor, brigthColor, olColor);
                }
            }
        }
    }

    /**
     * Dibuja una baldoza del tablero
     * 
     * @param g       Graphics
     * @param y       int cordenada inicial y
     * @param x       int cordenada inicial x
     * @param dColor  Color color oscuro
     * @param lColor  Color color claro
     * @param blColor Color color de borde
     */
    private void drawGameSquare(Graphics g, int y, int x, Color dColor, Color lColor, Color bColor) {
        int Xi = x * tileSize;
        int Yi = y * tileSize;
        g.setColor(dColor);
        g.fillRect(Xi, Yi, tileSize, tileSize);
        g.setColor(lColor);
        g.fillRect(Xi + drawOffset, Yi + drawOffset, tileSize + drawOffset2, tileSize + drawOffset2);
        g.setColor(bColor);
        g.drawRect(Xi, Yi, tileSize, tileSize);
    }

    @Override
    /**
     * Dibuja el bloque
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.drawBackGround(g);
        this.drawBlock(g);
    }

    /**
     * Inicializa el panel
     */
    private void init(int multiplier) {
        int x = nextShapePlaceHolderXoffset * multiplier;
        int y = nextShapePlaceHolderYoffset * multiplier;
        int side = nextShapePlaceSides * multiplier;
        this.setBounds(x, y, side, side);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.setVisible(true);
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
    }
    // </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
