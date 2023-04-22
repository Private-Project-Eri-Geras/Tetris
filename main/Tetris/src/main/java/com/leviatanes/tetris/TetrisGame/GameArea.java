package com.leviatanes.tetris.TetrisGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import com.leviatanes.tetris.TetrisGame.Tetrinominos.*;

public class GameArea extends JPanel {
    /** Columnas del tablero del juego */
    private int colums;
    /** Filas del tablero del juego */
    private int rows;
    /** Tamaño de la baldoza */
    private int tileSize;
    /** El place holder */
    private JPanel placeHolder;
    /**
     * matriz del color de fondo
     * 
     * @apiNote bacgroun[0] = color oscuro de fondo
     * @apiNote bacgroun[1] = color claro de fondo
     * @apiNote bacgroun[2] = color del borde
     */
    private Color[][][] background;
    /** Bloque de tetris a usar */
    private TetrisBlock block;
    /**
     * Offset del cuadrado interior a dibujar es un 15% del tamaño de balsoza
     */
    private int drawOffset;

    /** color oscuro @apiNote rgb = (20,20,20) */
    private static final Color darkColor = new Color(20, 20, 20);
    /** color brillante @apiNote rgb = (30,30,30) */
    private static final Color lightColor = new Color(30, 30, 30);
    /** color brillante @apiNote rgb = (100,100,100) */
    private static final Color borderColor = new Color(100, 100, 100);
    /** Los 7 bloques diferentes que podemos utilizar */
    private static final TetrisBlock[] blocks = { new Ishape(), new Jshape(), new Lshape(), new Oshape(), new Sshape(),
            new Tshape(), new Zshape() };

    /**
     * Constructor de la clase
     * consigue el tamaño de la baldoza dividiendo el ancho
     * del place holder para sacar columnas y en propocion saca
     * el alto y el tamaño de la baldoza
     * 
     * @implNote
     *           utilizar un panel con base 30x72 pixeles
     * @param placeHolder JPanel panel que encierra el tablero
     * @param colums      int columnas del tablero
     */
    public GameArea(JPanel placeHolder, int colums) {
        this.placeHolder = placeHolder;
        this.placeHolder.setVisible(false);
        this.setBounds(placeHolder.getBounds(getBounds()));
        this.setBorder(this.placeHolder.getBorder());
        this.colums = colums;
        this.tileSize = this.getBounds().width / colums;
        this.rows = this.getBounds().height / tileSize;

        this.drawOffset = (int) (tileSize * 0.15);

        this.background = new Color[3][this.rows][this.colums];
        for (int i = 0; i < this.rows; i++) { // llenamos el fondo con los colores delimitados
            for (int j = 0; j < this.colums; j++) {
                this.background[0][i][j] = darkColor;
                this.background[1][i][j] = lightColor;
                this.background[2][i][j] = borderColor;
            }
        }
    }

    /** @return TetrisBlock */
    public TetrisBlock getBlock() {
        return this.block;
    }

    /** Spawnea un bloque aleatorio entre I, O, T, J, L, S, Z */
    public void spawnBlock() {
        Random random = new Random();
        this.block = blocks[random.nextInt(blocks.length)];
        block.spawn(this.colums);
    }

    /**
     * Termina la ejecucion del juego
     * si ya no se puede spawnear ningun
     * bloque nuevo
     * 
     * @return boolean true si el juego termino
     * @return boolean false si el juego no termino
     */
    public boolean isGameOver() {
        if (this.block == null)
            return false;
        int w = this.block.getWidth();
        int h = this.block.getHeight();
        int x, y;// se utilizaran para sacar el offsetverdadero y comparar correctamente
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                x = row + block.getX();
                y = col + block.getY();
                if (background[0][y][x] != darkColor) {
                    this.block = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Mueve el bloque para abajo
     * 
     * @return boolean true si se pudo mover
     * @return boolean false si no se pudo mover
     */
    public boolean moveDown() {
        if (this.block == null)
            return false;
        this.block.moveDown();
        repaint();
        return true;
    }

    /**
     * Mueve el bloque activo
     * al fondo del tablero
     * 
     * @return boolean true si se pudo mover
     * @return boolean false si no se pudo mover
     */
    public void moveBlockToBackGround() {
        if (block == null)
            return;
        int[][] shape = block.getBlock();
        int h = block.getHeight();
        int w = block.getWidth();
        int xPos = block.getX();
        int yPos = block.getY();
        Color darkColor = block.getDarkColor();
        Color brigthColor = block.getLightColor();
        Color olColor = block.getBorderColor();
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (shape[r][c] == 1) {
                    background[0][r + yPos][c + xPos] = darkColor;
                    background[1][r + yPos][c + xPos] = brigthColor;
                    background[2][r + yPos][c + xPos] = olColor;
                }
            }
        }
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
                if (background[0][y][x] == darkColor)
                    drawGameSquare(g, y, x, background[0][y][x], background[1][y][x], background[2][y][x]);
            }
        }
        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.colums; x++) {
                if (background[0][y][x] != darkColor)
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
        g.fillRect(Xi + drawOffset, Yi + drawOffset, tileSize - drawOffset, tileSize - drawOffset);
        g.setColor(bColor);
        g.drawRect(Xi, Yi, tileSize, tileSize);
    }

    @Override
    /**
     * se sobreescribe el metodo para dibujar el fondo
     * y el bloque
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.drawBackGround(g);
        this.drawBlock(g);
    }
}
