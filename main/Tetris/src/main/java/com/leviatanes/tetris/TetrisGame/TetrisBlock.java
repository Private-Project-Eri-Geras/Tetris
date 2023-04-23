package com.leviatanes.tetris.TetrisGame;

import java.awt.Color;

public class TetrisBlock {
    // Informacion del bloque
    private int[][] block; // Bloque actual
    private int[][][] blockRotations; // Rotaciones del bloque
    private char blockType; // Tipo de bloque
    // Colores del bloque
    private Color darkColor;
    private Color lightColor;
    private Color borderColor;
    // Coordenadas del bloque
    private int x;
    private int y;
    private int currentRotation;

    /**
     * Constructor por defecto
     *
     * @param block          Bloque actual
     * @param blockRotations Lista de rotacion de bloques
     * @param darkColor      Color oscuro del bloque
     * @param lightColor     Color claro del bloque
     * @param borderColor    Color del borde
     */
    public TetrisBlock(int[][] block, int[][][] blockRotations, char blockType, Color darkColor, Color lightColor) {
        this.block = block;
        this.blockRotations = blockRotations;
        this.blockType = blockType;
        this.darkColor = darkColor;
        this.lightColor = lightColor;
        this.borderColor = new Color(42, 42, 42);
        this.x = 0;
        this.y = 0;
        this.currentRotation = 0;
    }

    // Getters
    /**
     * Devuelve el bloque actual
     * 
     * @return int[][] block
     */
    public int[][] getBlock() {
        return block;
    }

    /**
     * Devuelve el color oscuro del bloque
     * 
     * @return Color darkColor Color oscuro del bloque
     */
    public Color getDarkColor() {
        return darkColor;
    }

    /**
     * Devuelve el color claro del bloque
     * 
     * @return Color lightColor Color claro del bloque
     */
    public Color getLightColor() {
        return lightColor;
    }

    /**
     * Devuelve el color del borde
     * 
     * @return Color borderColor Color del borde
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Devuelve una coordenada del bloque
     * 
     * @return x coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * Devuelve una coordenada del bloque
     * 
     * @return y coordenada y
     */
    public int getY() {
        return y;
    }

    /**
     * Devuelve la rotacion actual del bloque
     * 
     * @return int rotacion actual del bloque
     */
    public int getCurrentRotation() {
        return currentRotation;
    }

    /**
     * Devuelve el tipo de bloque
     * 
     * @return char
     */
    public char getType() {
        return this.blockType;
    }

    /**
     * Devuelve el ancho del bloque
     * 
     * @return int
     */
    public int getWidth() {
        return this.block[0].length;
    }

    /**
     * Devuelve el alto del bloque
     * 
     * @return int
     */
    public int getHeight() {
        return this.block.length;
    }

    /**
     * Devuelve la coordenada y + el alto del bloque
     * 
     * @return int height
     */
    public int getfullHeight() {
        return getHeight() + this.y;
    }

    /**
     * Devuelve la coordenada x + el ancho del bloque
     * 
     * @return int width
     */
    public int getFullWidth() {
        return getWidth() + this.x;
    }

    // Setters

    /**
     * Establece una coordenada del bloque
     * 
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Establece una coordenada del bloque
     * 
     * @param y int coordenada y
     */
    public void setY(int y) {
        this.y = y;
    }

    // Metodos

    /**
     * Establece el bloque actual
     * 
     * @param block int[][] bloque actual
     */
    public void setBlock(int[][] block) {
        this.block = block;
    }

    /**
     * Establece la rotacion actual del bloque
     * 
     * @param rotation int rotacion actual del bloque
     */
    public void setCurentRotation(int rotation) {
        this.currentRotation = rotation;
    }

    /**
     * Se encarga de rotar el bloque en
     * dirreccion de las manecillas delreloj
     */
    public void rotate() {
        this.currentRotation = (this.currentRotation + 1) % 4;
        this.block = this.blockRotations[this.currentRotation];
    }

    /**
     * Se encarga de rotar el bloque en
     * dirreccion contraria de las manecillas delreloj
     */
    public void rotateBack() {
        if (this.currentRotation == 0) {
            this.currentRotation = 3;
        } else {
            this.currentRotation--;
        }
        this.block = this.blockRotations[this.currentRotation];
    }

    /**
     * Mueve el bloque
     * hacia la izquierda
     */
    public void moveLeft() {
        this.x--;
    }

    /**
     * Muve el bloque
     * hacia la derecha
     */
    public void moveRight() {
        this.x++;
    }

    /**
     * Muve el bloque
     * hacia abajo
     */
    public void moveDown() {
        this.y++;
    }

    /**
     * Retorna el borde inferior
     * del bloque
     * 
     * @return coordenada inferior
     */
    public int getBottomEdge() {
        return this.getfullHeight();
    }

    /**
     * Retorna el borde superior
     * del bloque
     * 
     * @return Y coordenada superior
     */
    public int getTopEdge() {
        return this.y;
    }

    /**
     * Retorna el borde izquierdo
     * del bloque
     * 
     * @return X coordenada izquierda
     */
    public int getLeftEdge() {
        return this.x;
    }

    /**
     * Retorna el borde derecho
     * del bloque
     * 
     * @return int coordenada derecha
     */
    public int getRightEdge() {
        return this.getFullWidth();
    }

    /**
     * Genera un bloque
     * en las mitad del tablero
     * y en la parte superior del tablero
     * 
     * @param colums cuantas columnas tiene el tablero
     */
    public void spawn(int colums) {
        this.currentRotation = 0;
        this.block = this.blockRotations[this.currentRotation];

        this.x = (colums - this.getWidth()) / 2;
        this.y = 0;
    }
}
