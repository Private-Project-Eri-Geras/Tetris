package com.leviatanes.tetris.tetrisGame.game.sidePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;

/**
 * {@link ShapeHolder}
 * Panel que se encarga de dibujar las piezas en un panel extra
 * 
 * @author Eriarer (Abraham)
 */
public class ShapeHolder extends javax.swing.JPanel {
    /** TetrisBlock Actual */
    private TetrisBlock block;
    /** Tamaño del tile a dibujar */
    private int tileSize;
    /** Cantidad de filas */
    private int rows;
    /** Cantidad de columnas */
    private int colums;
    /** Bandera de hold permitido a swap */
    private boolean holdAllowed;
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
        this.holdAllowed = true;
    }

    public boolean isHoldAllowed() {
        return holdAllowed;
    }

    public void toggleHoldAllowed() {
        this.holdAllowed = !this.holdAllowed;
        this.repaint();
    }

    public void setHoldAllowed(boolean holdAllowed) {
        this.holdAllowed = holdAllowed;
        this.repaint();
    }

    public void setBlock(TetrisBlock block) {
        String classPath = "com.leviatanes.tetris.tetrisGame.tetrisBlocks.tetrinominos.";
        String blockType = classPath + block.getType() + "shape"; // conseguir el tipo de bloque que es
        Object newBlock = null; // este objeto sera el nuevo bloque
        Class<? extends TetrisBlock> blockClass = null;
        Constructor<? extends TetrisBlock> constructor = null;
        try {
            // crear una clase con el nombre del tipo de bloque
            blockClass = Class.forName(blockType).asSubclass(TetrisBlock.class);
            // crear el constructor de la calse
            constructor = blockClass.getDeclaredConstructor();
            newBlock = constructor.newInstance(); // asignar el bloque al resultado de llamar al constructor
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        this.block = (TetrisBlock) newBlock;

        if (block.getType() == 'I') {
            this.block.setX(0);
            this.block.setY(2);
        } else if (block.getType() == 'O') {
            this.repaint();
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
        if (holdAllowed == false) {
            darkColor = darkColor.darker();
            darkColor = darkColor.darker();
            brigthColor = brigthColor.darker();
            brigthColor = brigthColor.darker();
            olColor = olColor.darker();
        }
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
