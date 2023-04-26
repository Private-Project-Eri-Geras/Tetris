package com.leviatanes.tetris.tetrisGame.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;
import com.leviatanes.tetris.tetrisGame.tetrisBlocks.tetrinominos.*;

public class GameArea extends JPanel {
    /** Columnas del tablero del juego */
    private int colums;// se usa para x
    /** Filas del tablero del juego */
    private int rows;// se usa para y
    /** Tamaño de la baldoza */
    private int tileSize;

    // ===========[ BANDERAS DE SECCIONES CRITICAS ]================//
    /** Bandera de rotacion */
    private boolean rotateFlag = false;
    /** Bandera de movimiento abajo */
    private boolean moveDownFlag = false;
    /** Bandera de movimiento izquierda derecha */
    private boolean moveFlag = false;
    /** Bandera de bloque soltado */
    private boolean dropedFalg = false;
    /** Bandera de revision si hay que soltar el bloque */
    private boolean checkToDropFlag = false;
    /** Bandera de limpado de lineas */
    private boolean clearLinesFlag = false;
    /** Bandera para mover el bloque al fondo */
    private boolean moveBlockToBottomFlag = false;
    /** Bandera de spawneo */
    private boolean spawnFlag = false;
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
    /** Bloque de tetris que sigue despues */
    private TetrisBlock nextBlock;
    /** Bloque de tetris que se ha deseado guardar */
    private TetrisBlock savedBlock;
    /**
     * Offset del cuadrado interior a dibujar es un 15% del tamaño de balsoza
     */
    private int drawOffset;
    private int drawOffset2;
    /** Bandera si el bloque fue "Dropeado" */
    private boolean blockDropped;
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
    public GameArea(int x, int y, int width, int height, int colums) {
        this.initGame(x, y, width, height, colums);
    }

    /**
     * Constructor de la clase inicializa lo necesario para arrancar el juego
     * 
     * @param placeHolder
     * @param colums
     */
    private void initGame(int x, int y, int width, int height, int colums) {
        this.setBounds(x, y, width, height);
        this.colums = colums;
        this.tileSize = width / this.colums;
        this.rows = height / tileSize;

        this.drawOffset = (int) (tileSize * 0.20);
        this.drawOffset2 = -(2 * drawOffset) + 1;

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

    /** @param block TetrisBlock */
    public void setBlock(TetrisBlock block) {
        this.block = block;
    }

    /** @return TetrisBlock */
    public TetrisBlock getNextBlock() {
        return this.nextBlock;
    }

    /** @param block TetrisBlock */
    public void setNextBlock(TetrisBlock block) {
        this.nextBlock = block;
    }

    /** @return TetrisBlock */
    public TetrisBlock getSavedBlock() {
        return this.savedBlock;
    }

    /** @param block TetrisBlock */
    public void setSavedBlock(TetrisBlock block) {
        this.savedBlock = block;
    }

    /**
     * retorna la bandera del bloque dropeado
     * 
     * @return true si fue dropeado
     */
    public boolean isBlockDropped() {
        return this.blockDropped;
    }

    /** deshabilita la bandera del bloque dropeado */
    public void disableBlockDropped() {
        this.blockDropped = false;
    }

    /** @return bandera de seccion critica */
    public boolean getRotateFlag() {
        return this.rotateFlag;
    }

    /** @return bandera de seccion critica */
    public boolean getSpawnedFlag() {
        return this.spawnFlag;
    }
    //
    //
    //
    //
    //

    /** Spawnea un bloque aleatorio entre I, J, L, O, S, T, Z */
    public void spawnBlock() {
        this.spawnFlag = true;
        Random random = new Random();
        if (this.nextBlock == null) {
            this.block = blocks[random.nextInt(blocks.length)];
            block.spawn(this.colums);
            System.out.println("IF Block spawned coord " + this.block.getX() + " " + this.block.getY());
            this.nextBlock = blocks[random.nextInt(blocks.length)];
        } else {
            this.block = new TetrisBlock(nextBlock);
            block.spawn(this.colums);
            System.out.println("ELSE Block spawned coord " + this.block.getX() + " " + this.block.getY());
            this.nextBlock = blocks[random.nextInt(blocks.length)];
        }
        System.out.println("Block spawned coord " + this.block.getX() + " " + this.block.getY());
        repaint();
        this.spawnFlag = false;
        // == TESTING ==//
        // this.blockDropped = false;
        // this.block = testBlocks[blockCounter];
        // blockCounter = (blockCounter + 1) % testBlocks.length;
        // == TESTING ==//
    }

    /**
     * Valida si el swap es valido
     * 
     * @return true si el swap es valido
     */
    private boolean checkSwap() {
        int[][] shape = this.savedBlock.getBlock();
        int w = this.savedBlock.getWidth();
        int h = this.savedBlock.getHeight();
        if (this.checkOutOfBounnds())
            return true;
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {
                    int x = col + this.savedBlock.getX();
                    int y = row + this.savedBlock.getY();
                    if (this.background[0][y][x] != darkColor)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Termina la ejecucion del juego
     * si ya no se puede spawnear ningun
     * bloque nuevo
     * 
     * @return boolean true si el juego termino
     */
    public boolean isGameOver() {
        if (this.block == null)
            return false;
        int[][] shape = this.block.getBlock();
        int w = this.block.getWidth();
        int h = this.block.getHeight();
        int x, y;// se utilizaran para sacar el offsetverdadero y comparar correctamente
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {
                    x = col + block.getX();
                    y = row + block.getY();
                    if (background[0][y][x] != darkColor) {
                        this.block = null;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Mueve el bloque para abajo
     * 
     * @return boolean true si se pudo mover
     */
    public boolean moveDown() {
        // espera a que se termine la seccion critica
        while (checkToDropFlag || clearLinesFlag || moveBlockToBottomFlag)
            ;
        System.out.println("moveDown");
        if (this.block == null) {
            System.out.println("exit moveDown");
            return false;
        }
        // bansera critica
        this.moveDownFlag = true; // !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        if (this.checkBottom()) {
            this.moveDownFlag = false; // !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
            System.out.println("exit moveDown");
            return false;
        }

        this.block.moveDown();
        repaint();
        // bansera critica
        this.moveDownFlag = false; // !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        System.out.println("exit moveDown");
        return true;
    }

    /**
     * Suelta el bloque en la posicion mas baja posible
     */
    public void drop() {
        // espera a que se termine la seccion critica
        while (rotateFlag || moveFlag || checkToDropFlag || clearLinesFlag || moveBlockToBottomFlag)
            ;
        System.out.println("drop");
        if (this.block == null) {
            System.out.println("exit drop null");
            return;
        }
        this.dropedFalg = true; // !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        while (this.moveDown())
            ;
        this.blockDropped = true;
        this.dropedFalg = false; // !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        repaint();
        System.out.println("exit drop");
    }

    /**
     * Verifica el borde inferior del bloque en 2 condiciones
     * toco el borde del tablero o a otro bloque
     * 
     * @return boolean true si el bloque llego al fondo o toco otro bloque
     */
    public boolean checkBottom() throws ArrayIndexOutOfBoundsException {
        // espera a que se termine la seccion critica
        while (moveFlag || clearLinesFlag || moveBlockToBottomFlag)
            ;
        this.moveDownFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        if (this.block == null) {
            this.moveDownFlag = false;
            return true;
        }
        if (this.block.getBottomEdge() == this.rows) {
            this.moveDownFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
            return true;
        }
        // obtencion de datos del bloque
        int shape[][] = this.block.getBlock();
        int w = this.block.getWidth();
        int h = this.block.getHeight();
        int x, y;// se utilizaran para sacar el offsetverdadero y comparar correctamente
        if (block.getHeight() != 1) {
            for (int col = 0; col < w; col++) {
                for (int row = h - 1; row >= 0; row--) {
                    if (shape[row][col] != 0) {
                        x = col + block.getX();
                        y = row + block.getY() + 1;
                        if (background[0][y][x] != darkColor) {
                            this.moveDownFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
                            return true;
                        }
                        break;
                    }
                }
            }
        } else {
            for (int col = 0; col < w; col++) {
                x = col + block.getX();
                y = block.getY() + 1;
                if (y < 0)
                    break;
                if (background[0][y][x] != darkColor) {
                    this.moveDownFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
                    return true;
                }
            }
        }
        this.moveDownFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        return false;
    }

    /**
     * Checa se hay mas de 2 espacios en blanco bajo la pieza
     * si es asi la pieza se no se dropea
     * en caso contrario lo hace
     * Con exepcion de la pieza I
     * en esta se checan 3 espacios
     * 
     * @return true si se tiene que dropear
     */
    public boolean checkToDrop() {
        // espera a que se termine la seccion critica
        while (rotateFlag || moveFlag || moveDownFlag || dropedFalg || clearLinesFlag || moveBlockToBottomFlag)
            ;
        System.out.println("checkToDrop");
        if (this.block == null) {
            System.out.println("exit checkToDrop");
            return false;
        }
        this.checkToDropFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        if (block.getType() == 'I') {
            if (this.block.getBottomEdge() + 2 >= this.rows) {
                this.checkToDropFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
                System.out.println("exit checkToDrop Bottmo edge not I");
                return true;
            }
        } else {
            if (this.block.getBottomEdge() + 1 >= this.rows) {
                this.checkToDropFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
                System.out.println("exit checkToDrop Bottmo edge");
                return true;
            }
        }
        int shape[][] = this.block.getBlock();
        int w = this.block.getWidth();
        int h = this.block.getHeight();
        int x, y;// se utilizaran para sacar el offsetverdadero y comparar correctamente
        if (this.checkBottom()) {
            this.checkToDropFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
            System.out.println("exit checkToDrop checkBottom");
            return true;
        }
        if (block.getType() != 'I') {
            for (int col = 0; col < w; col++) {
                for (int row = h - 1; row >= 0; row--) {
                    if (shape[row][col] != 0) {
                        x = col + block.getX();
                        y = row + block.getY() + 1;
                        if (background[0][y][x] != darkColor || background[0][y + 1][x] != darkColor) {
                            this.checkToDropFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
                            System.out.println("exit checkToDrop while not I");
                            return true;
                        }
                        break;
                    }
                }
            }
        } else {
            for (int col = 0; col < w; col++) {
                x = col + block.getX();
                y = block.getY() + 1;
                if (y < 0)
                    break;
                if (background[0][y][x] != darkColor || background[0][y + 1][x] != darkColor
                        || background[0][y + 2][x] != darkColor) {
                    this.checkToDropFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
                    System.out.println("exit checkToDrop while I");
                    return true;
                }
            }
        }
        this.checkToDropFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        System.out.println("exit checkToDrop");
        return false;
    }

    /**
     * Mueve el bloque para la izquierda
     * 
     * @return boolean true si se pudo mover
     */
    public boolean moveLeft() {
        // espera a que se termine la seccion critica
        while (rotateFlag || moveDownFlag || checkToDropFlag || dropedFalg || moveFlag || clearLinesFlag
                || moveBlockToBottomFlag)
            ;
        System.out.println("moveLeft");
        if (this.block == null) {
            System.out.println("exit moveLeft block null");
            return false;
        }
        this.moveFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        if (this.checkLeft()) {
            this.moveFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
            System.out.println("exit moveLeft checkLeft");
            return false;
        }
        this.block.moveLeft();
        repaint();
        this.moveFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        System.out.println("exit moveLeft");
        return true;
    }

    /**
     * Verifica el borde izquierdo del bloque en 2 condiciones
     * toco el borde del tablero o a otro bloque
     * 
     * @return boolean true si el bloque llego al borde o toco otro bloque
     */
    private boolean checkLeft() {
        if (this.block == null)
            return true;
        if (this.block.getLeftEdge() == 0)
            return true;
        int shape[][] = this.block.getBlock();
        int w = this.block.getWidth();
        int h = this.block.getHeight();
        int x, y;// se utilizaran para sacar el offsetverdadero y comparar correctamente
        if (w != 1) {
            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    if (shape[row][col] == 1) {
                        x = col + block.getX() - 1;
                        y = row + block.getY();
                        if (background[0][y][x] != darkColor) {
                            return true;
                        }
                        break;
                    }
                }
            }
        } else {
            for (int row = 0; row < h; row++) {
                x = block.getX() - 1;
                y = row + block.getY();
                if (background[0][y][x] != darkColor) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Mueve el bloque para la derecha
     * 
     * @return boolean true si se pudo mover
     */
    public boolean moveRight() {
        // espera a que se termine la seccion critica
        while (rotateFlag || moveDownFlag || checkToDropFlag || dropedFalg || moveFlag || clearLinesFlag
                || moveBlockToBottomFlag)
            ;
        System.out.println("moveRight");
        if (this.block == null) {
            System.out.println("exit moveRight block null");
            return false;
        }
        this.moveFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        if (this.checkRight()) {
            this.moveFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
            System.out.println("exit moveRight checkRight");
            return false;
        }
        this.block.moveRight();
        repaint();
        this.moveFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        System.out.println("exit moveRight");
        return true;
    }

    /**
     * Verifica el borde derecho del bloque en 2 condiciones
     * toco el borde del tablero o a otro bloque
     * 
     * @return boolean true si el bloque llego al borde o toco otro bloque
     */
    private boolean checkRight() {
        if (this.block == null)
            return true;
        if (this.block.getRightEdge() == this.colums)
            return true;
        int shape[][] = this.block.getBlock();
        int w = this.block.getWidth();
        int h = this.block.getHeight();
        int x, y;// se utilizaran para sacar el offsetverdadero y comparar correctamente
        if (w != 1) {
            for (int row = 0; row < h; row++) {
                for (int col = w - 1; col >= 0; col--) {
                    if (shape[row][col] == 1) {
                        x = col + block.getX() + 1;
                        y = row + block.getY();
                        if (y < 0)
                            break;
                        if (background[0][y][x] != darkColor) {
                            return true;
                        }
                        break;
                    }
                }
            }
        } else {
            for (int row = 0; row < h; row++) {
                x = block.getX() + 1;
                y = row + block.getY();
                if (y < 0)
                    break;
                if (background[0][y][x] != darkColor) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Gira el bloque */
    public void rotate() {
        // esperar a secciones criticas
        while (moveDownFlag || moveFlag || checkToDropFlag || clearLinesFlag || moveBlockToBottomFlag)
            ;
        System.out.println("rotate");
        if (this.block == null) {
            System.out.println("exit rotate block null");
            return;
        }
        this.rotateFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        this.checkRotate();
        this.rotateFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        repaint();
        System.out.println("exit rotate");
    }

    /**
     * Arreglo de pruebas de rotacion J,L,S,Z,T
     * int[TipoDeRotacion][NumeroDeTest][cooredandas]
     */
    private int[][][] rotationTests = {
            { { 0, 0 }, { -1, 0 }, { -1, -1 }, { 0, 2 }, { -1, 2 } }, // 0->1
            { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 0, -2 }, { 1, -2 } }, // 1->2
            { { 0, 0 }, { 1, 0 }, { 1, -1 }, { 0, 2 }, { 1, 2 } }, // 2->3
            { { 0, 0 }, { -1, 0 }, { -1, 1 }, { 0, -2 }, { -1, -2 } }// 3->0
    };

    /**
     * Arreglo de pruebas de rotacion I
     * int[TipoDeRotacion][NumeroDeTest][cooredandas]
     */
    private int[][][] rotationTestI = {
            { { 0, 0 }, { -2, 0 }, { 1, 0 }, { -2, 1 }, { 1, -2 } }, // 0->1
            { { 0, 0 }, { -1, 0 }, { 2, 0 }, { -1, -2 }, { 2, 1 } }, // 1->2
            { { 0, 0 }, { 2, 0 }, { -1, 0 }, { 2, -1 }, { -1, 2 } }, // 2->3
            { { 0, 0 }, { 1, 0 }, { -2, 0 }, { 1, 2 }, { -2, -1 } } // 3->0
    };

    /**
     * Verifica si la rotacion del bloque es valida
     * no es valida si se solapa a otro bloque ya existente
     * o si se sale de los limites del tablero
     * si es valida se deja la rotacion
     * si no es valida se regresa la rotacion anterior
     */
    private void checkRotate() {
        if (this.block.getType() == 'O') {
            return;
        }
        int[][][] rotationTest;
        if (this.block.getType() == 'I') {
            rotationTest = rotationTestI;
        } else {
            rotationTest = rotationTests;
        }

        int currentRotation = this.block.getCurrentRotation();
        this.block.rotate();
        int x = this.block.getLeftEdge();
        int y = this.block.getTopEdge();
        for (int i = 0; i < 5; i++) {
            this.block.setX(x);
            this.block.setY(y);
            block.addX(rotationTest[currentRotation][i][0]);
            block.addY(rotationTest[currentRotation][i][1]);
            System.out
                    .println("validando wallKick test " + (i + 1) + " offset: x " + rotationTest[currentRotation][i][0]
                            + " y " + (-rotationTest[currentRotation][i][1]));
            if (this.wallKickTest()) {
                System.out.println("wallkick test passed, current rotation: " + this.block.getCurrentRotation());
                return;
            }
        }
        System.out.println("wallkick test failed, current rotation: " + this.block.getCurrentRotation());
        this.block.setX(x);
        this.block.setY(y);
        this.block.rotateBack();
    }

    /**
     * Verifica que el "wallkick"
     * sea una rotacion valida
     * 
     * @return boolean true si la rotacion es valida
     */
    private boolean wallKickTest() {
        if (this.checkOutOfBounnds())
            return false;
        int shape[][] = block.getBlock();
        int w = block.getWidth();
        int h = block.getHeight();
        int x, y;// se utilizaran para sacar el offsetverdadero y comparar correctamente
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {
                    x = col + block.getX();
                    y = row + block.getY();
                    if (background[0][y][x] != darkColor) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /** Gira el bloque en contra de las manecillas del reloj */
    public void rotateBack() {
        // esperar a secciones criticas
        while (moveDownFlag || moveFlag || checkToDropFlag || clearLinesFlag)
            ;
        System.out.println("rotateBack");
        if (this.block == null) {
            System.out.println("exit rotateBack block null");
            return;
        }
        this.rotateFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        this.checkRotateBack();
        this.rotateFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        repaint();
        System.out.println("exit rotateBack");
    }

    /**
     * Arreglo de pruebas de contraRotacion J,L,S,Z,T
     * int[TipoDeRotacion][NumeroDeTest][cooredandas]
     */
    private int[][][] counterRotationTests = {
            { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 0, -2 }, { 1, -2 } }, // 1->0
            { { 0, 0 }, { -1, 0 }, { -1, -1 }, { 0, 2 }, { -1, 2 } }, // 2->1
            { { 0, 0 }, { -1, 0 }, { -1, 1 }, { 0, -2 }, { -1, -2 } }, // 3->2
            { { 0, 0 }, { 1, 0 }, { 1, -1 }, { 0, 2 }, { 1, 2 } } // 0->3
    };
    /**
     * Arreglo de pruebas de contraRotacion I
     * int[TipoDeRotacion][NumeroDeTest][cooredandas]
     * La coordenada Y tiene signo negativo al estandard de tetris
     */
    private int[][][] counterRotationTestI = {
            { { 0, 0 }, { 2, 0 }, { -1, 0 }, { 2, -1 }, { -1, 2 } }, // 1->0
            { { 0, 0 }, { 1, 0 }, { -2, 0 }, { 1, 2 }, { -2, -1 } }, // 2->1
            { { 0, 0 }, { -2, 0 }, { 1, 0 }, { -2, 1 }, { 1, -2 } }, // 3->2
            { { 0, 0 }, { -1, 0 }, { 2, 0 }, { -1, -2 }, { 2, 1 } } // 0->3
    };

    /**
     * Verifica si la rotacion del bloque es valida
     * no es valida si se solapa a otro bloque ya existente
     * o si se sale de los limites del tablero
     * si es valida se deja la rotacion
     */
    private void checkRotateBack() {
        if (this.block.getType() == 'O') {
            return;
        }
        int[][][] rotationTest;
        if (this.block.getType() == 'I') {
            rotationTest = counterRotationTestI;
        } else {
            rotationTest = counterRotationTests;
        }

        int currentRotation = this.block.getCurrentRotation();
        currentRotation = currentRotation == 0 ? 3 : currentRotation - 1;
        this.block.rotateBack();
        int x = this.block.getLeftEdge();
        int y = this.block.getTopEdge();
        for (int i = 0; i < 5; i++) {
            this.block.setX(x);
            this.block.setY(y);
            block.addX(rotationTest[currentRotation][i][0]);
            block.addY(rotationTest[currentRotation][i][1]);
            System.out
                    .println("validando wallKick test " + (i + 1) + " offset: x " + rotationTest[currentRotation][i][0]
                            + " y " + (-rotationTest[currentRotation][i][1]));
            if (this.wallKickTest()) {
                System.out.println("wallkick test passed, current rotation: " + this.block.getCurrentRotation());
                return;
            }
        }
        this.block.setX(x);
        this.block.setY(y);
        this.block.rotate();
        System.out.println("wallkick test failed, current rotation: " + this.block.getCurrentRotation());
    }

    /**
     * Verifica si el bloque esta fuera de los limites del tablero
     * 
     * @return true si esta fuera de los limites
     */
    private boolean checkOutOfBounnds() {
        if (block.getLeftEdge() < 0) {
            System.out.println("left edge: " + block.getLeftEdge());
            return true;
        }
        if (block.getRightEdge() > this.colums) {
            System.out.println("right edge: " + block.getRightEdge());
            return true;
        }
        if (block.getBottomEdge() > this.rows) {
            System.out.println("bottom edge: " + block.getBottomEdge());
            return true;
        }
        if (block.getTopEdge() < 0) {
            System.out.println("top edge: " + block.getTopEdge());
            return true;
        }
        return false;
    }

    /**
     * Se encarba de eliminar las lineas completadas
     * y mover las lineas superiores hacia abajo los espacios que se crearon
     * 
     * @return lineas completadas
     */
    public int clearLines() {
        // esperar a secciones criticas
        while (moveDownFlag || moveFlag || rotateFlag || checkToDropFlag || moveBlockToBottomFlag)
            ;
        System.out.println("clearLines");
        this.clearLinesFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        int linesCleared = 0;
        boolean fillLine = true;
        for (int row = this.rows - 1; row >= 0; row--) {
            fillLine = true;
            for (int col = 0; col < this.colums; col++) {
                if (background[0][row][col] == darkColor) {
                    fillLine = false;
                    break;
                }
            }
            if (fillLine == true) {
                this.clearLine(row);
                this.shiftDown(row);
                row++;
                linesCleared++;
                repaint();
            }
        }
        this.clearLinesFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        System.out.println("exit clearLines");
        return linesCleared;
    }

    /**
     * Elimina la linea indicada
     * 
     * @param r linea a eliminar
     */
    private void clearLine(int r) {
        for (int col = 0; col < this.colums; col++) {
            background[0][r][col] = darkColor;
            background[1][r][col] = lightColor;
            background[2][r][col] = borderColor;
        }
    }

    /**
     * Mueve las lineas superiores de la
     * linea eliminada hacia abajo
     * 
     * @param r linea eliminada
     */
    private void shiftDown(int r) {
        for (int row = r; row >= 0; row--) {
            for (int col = 0; col < this.colums; col++) {
                if (row == 0) {
                    clearLine(0);
                    break;
                }
                background[0][row][col] = background[0][row - 1][col];
                background[1][row][col] = background[1][row - 1][col];
                background[2][row][col] = background[2][row - 1][col];
            }
        }
    }

    /**
     * Mueve el bloque activo
     * al fondo del tablero
     */
    public void moveBlockToBackGround() {
        // esperar a secciones criticas
        while (this.moveDownFlag || this.moveFlag || this.rotateFlag || this.checkToDropFlag || this.clearLinesFlag)
            ;
        System.out.println("moveBlockToBackGround");
        if (block == null) {
            System.out.println("exit moveBlockToBackGround block null");
            return;
        }
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
        System.out.println("exit moveBlockToBackGround");
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
        while (this.moveDownFlag || this.moveFlag || this.rotateFlag || this.checkToDropFlag || this.clearLinesFlag
                || this.moveBlockToBottomFlag)
            ;
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
     * se sobreescribe el metodo para dibujar el fondo
     * y el bloque
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.drawBackGround(g);
        this.drawBlock(g);
    }
}
