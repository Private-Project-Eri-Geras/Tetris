package com.leviatanes.tetris.tetrisGame.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;
import com.leviatanes.tetris.tetrisGame.tetrisBlocks.tetrinominos.*;
import com.leviatanes.tetris.SoundsPlayer;
import com.leviatanes.tetris.tetrisGame.TetrisPanel;
import com.leviatanes.tetris.tetrisGame.game.gameOver.GameOver;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.*;

/**
 * !! CONTROL DEL JUEGO !!
 * Lleva el control y gestion de todo el juego
 * actualizando:
 * - tablero {@link GameArea}
 * - bloque actual {@link TetrisBlock}
 * - bloque siguiente {@link NextPanel}
 * - hold {@link HoldPanel}
 * - puntaje {@link StatsPanel}
 * - lineas {@link StatsPanel}
 * - nivel {@link StatsPanel}
 * - velocidad {@link GameThread}
 * - terminar el juego {@link GameOver}
 * - llamada a sonidos {@link SoundsPlayer}
 * Lleva acabo la logica del juego y validaciones
 * 
 * @implNote {@link GameControls} y {@link GameThread} se encarga de la llamada
 *           de los metodos
 * 
 * @author Leonardo
 * @author Eriarer (Abraham)
 * @author Gerardo
 * @author Mariana
 * 
 * @see GameControls
 * @see GameArea
 * @see TetrisBlock
 * @see NextPanel
 * @see HoldPanel
 * @see StatsPanel
 * @see GameThread
 * @see GameOver
 * @see SoundsPlayer
 */
public class GameArea extends JPanel {
    /** Ancho de la pantalla */
    private int width;
    /** Alto de la pantalla */
    private int height;
    /** Coordenada X */
    private int x;
    /** Coordenada Y */
    private int y;
    /** Columnas del tablero del juego */
    private int colums;// se usa para x
    /** Filas del tablero del juego */
    private int rows;// se usa para y
    /** Tamaño de la baldoza */
    private int tileSize;
    /** Bandera de HardDrop */
    private boolean hardDropFlag = false;
    /** bandera de pausa */
    private boolean pause = false;
    /** bandera de Tspin */
    private boolean tspinFlag = false;

    // ===========[ BANDERAS DE SECCIONES CRITICAS ]================//
    /** Bandera de rotacion */
    private boolean rotateFlag = false;
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
    private TetrisBlock holdedBlock;
    /** BLoque fantaste de tetris que muestra la posicion */
    private TetrisBlock ghostBlock;
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
    private static final Color olColor = new Color(42, 42, 42);
    /** Los 7 bloques diferentes que podemos utilizar */
    private static final TetrisBlock[] blocks = { new Ishape(), new Jshape(), new Lshape(), new Oshape(), new Sshape(),
            new Tshape(), new Zshape() };
    /** lista de bloques para que queden repartidos equitativamente */
    private TetrisBlock[] bockArray = new TetrisBlock[7];
    /** contador del indice de la lista de bloques */
    private int blockIndex = 0;

    // ===================[ OTHER PANELS ]===========================//
    /** panel de pieza siguiente */
    private NextPanel nextShape;
    /** panel de pieza a mantener */
    private HoldPanel holdShape;
    /** panel de estadisticas */
    private StatsPanel stats;
    /** panel de fin de juego */
    private GameOver gameOver;
    /** gameThread */
    private GameThread gameThread;
    /** panel de pausa */
    private PausedGame pausePanel;
    /** Tetris Panel */
    private TetrisPanel tetrisPanel;

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
    public GameArea(int x, int y, int width, int height, NextPanel nextShape, HoldPanel holdShape, StatsPanel stas,
            GameOver gameOver, TetrisPanel tetrisPanel) {
        this.setLayout(null);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colums = 10;
        this.nextShape = nextShape;
        this.holdShape = holdShape;
        this.stats = stas;
        this.gameOver = gameOver;
        this.tetrisPanel = tetrisPanel;
        this.initGame();
    }

    /** set del Game Thread */
    public void setGameThread(GameThread gameThread) {
        this.gameThread = gameThread;
    }

    /**
     * Constructor de la clase inicializa lo necesario para arrancar el juego
     * 
     * @param placeHolder
     * @param colums
     */
    private void initGame() {
        this.gameOver.setVisible(false);
        this.setBounds(x, y, width, height);
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
        fillBlockArray();
        this.nextBlock = this.bockArray[this.blockIndex];
        this.blockIndex++;
    }

    /** retorna el spawned Flag */
    public boolean getSpawnedFlag() {
        return this.spawnFlag;
    }

    /** @return boolean isHardDroped? */
    public boolean isHardDrop() {
        return this.hardDropFlag;
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
    public TetrisBlock getHoldedBlock() {
        return this.holdedBlock;
    }

    /** @param block TetrisBlock */
    public void setHoldedBlock(TetrisBlock block) {
        this.holdedBlock = block;
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

    /** activa o desactiva la pausa */
    public void togglePause() {
        if (pausePanel != null) {
            this.remove(pausePanel);
            pausePanel = null;

            this.revalidate();
            this.repaint();
            pause = false;
        } else {
            pausePanel = new PausedGame(this);
            this.add(pausePanel);
            this.revalidate();
            this.repaint();
            pause = true;
        }
        gameThread.togglePause();
    }

    /** reinicia el juego */
    public void restart() {
        tetrisPanel.restart();
    }

    /** menu de inicio */
    public void goToMainMenu() {
        tetrisPanel.goToMainMenu();
    }

    /** llena la lista de bloques con 1 de cada bloque de manera aleatoria */
    private void fillBlockArray() {
        Random random = new Random();
        for (int i = 0; i < bockArray.length; i++) {
            bockArray[i] = blocks[i];
        }
        // hacer 7 swaps para que no esten en orden
        for (int i = 0; i < bockArray.length; i++) {
            int randomIndex = random.nextInt(bockArray.length);
            TetrisBlock temp = bockArray[randomIndex];
            bockArray[randomIndex] = bockArray[i];
            bockArray[i] = temp;
        }
    }

    /**
     * Spawnea un bloque aleatorio entre I, J, L, O, S, T, Z
     * 
     * @return ture si se puede spawnear
     */
    public boolean spawnBlock() {
        this.blockDropped = false;
        this.spawnFlag = true;
        this.block = nextBlock;
        block.spawn(this.colums);

        if (isGameOver())
            return false;

        this.nextBlock = this.bockArray[this.blockIndex];
        this.blockIndex++;
        if (this.blockIndex >= this.bockArray.length) {
            this.blockIndex = 0;
            fillBlockArray();
        }
        nextShape.setNextShape(this.nextBlock);
        holdShape.setHoldAllowed(true);
        this.hardDropFlag = false;
        setGhostBlock();
        this.spawnFlag = false;
        this.tspinFlag = false;

        repaint();
        return true;
    }

    /**
     * Termina la ejecucion del juego
     * si ya no se puede spawnear ningun
     * bloque nuevo
     * 
     * @return boolean true si el juego termino
     */
    public boolean isGameOver() {
        if (this.pause)
            return false;
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
                        this.gameOver.setPuntuation(stats.getScore(), stats.getLines());
                        this.gameOver.endGame();
                        this.gameOver.setVisible(true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Hace un cambio de piezas */
    public void swap() {
        if (this.pause)
            return;
        if (this.holdedBlock == null) {
            this.holdedBlock = this.block;
            this.spawnBlock();
        } else {
            TetrisBlock temp = this.block;
            this.block = this.holdedBlock;
            this.holdedBlock = temp;
            this.block.spawn(this.colums);
            this.setGhostBlock();
            repaint();
        }
        SoundsPlayer.playHold();
    }

    /**
     * Mueve el bloque para abajo
     * 
     * @return boolean true si se pudo mover
     */
    public boolean moveDown() {
        if (this.pause)
            return false;
        if (this.block == null) {
            return false;
        }
        // bansera critica
        if (this.checkBottom()) {
            return false;
        }
        if (this.hardDropFlag == false) {
            SoundsPlayer.playSoftDrop();
        }
        this.block.moveDown();
        setGhostBlock();
        repaint();
        return true;
    }

    /** Hace hardDrop */
    public void hardDrop() {
        if (this.pause)
            return;
        this.hardDropFlag = true;
        this.drop();
        SoundsPlayer.playHardDrop();
    }

    /**
     * Suelta el bloque en la posicion mas baja posible
     */
    private void drop() {
        if (this.pause)
            return;
        if (this.block == null) {
            return;
        }
        while (this.moveDown())
            ;
        this.blockDropped = true;
        setGhostBlock();
        repaint();
    }

    /**
     * Verifica el borde inferior del bloque en 2 condiciones
     * toco el borde del tablero o a otro bloque
     * 
     * @return boolean true si el bloque llego al fondo o toco otro bloque
     */
    public boolean checkBottom() throws ArrayIndexOutOfBoundsException {
        if (this.pause)
            return true;
        if (this.block == null) {
            return true;
        }
        if (this.block.getBottomEdge() == this.rows) {
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
                    return true;
                }
            }
        }
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
        if (this.pause)
            return false;
        if (this.block == null) {
            return false;
        }
        if (this.checkBottom()) {
            return true;
        }
        return false;
    }

    /**
     * Mueve el bloque para la izquierda
     * 
     * @return boolean true si se pudo mover
     */
    public boolean moveLeft() {
        if (this.pause)
            return false;
        if (this.block == null) {
            return false;
        }
        if (this.checkLeft()) {
            return false;
        }
        this.block.moveLeft();
        SoundsPlayer.playMove();
        setGhostBlock();
        repaint();
        return true;
    }

    /**
     * Verifica el borde izquierdo del bloque en 2 condiciones
     * toco el borde del tablero o a otro bloque
     * 
     * @return boolean true si el bloque llego al borde o toco otro bloque
     */
    private boolean checkLeft() {
        if (this.pause)
            return true;
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
        if (this.pause)
            return false;
        if (this.block == null) {
            return false;
        }
        if (this.checkRight()) {
            return false;
        }
        this.block.moveRight();
        SoundsPlayer.playMove();
        setGhostBlock();
        repaint();
        return true;
    }

    /**
     * Verifica el borde derecho del bloque en 2 condiciones
     * toco el borde del tablero o a otro bloque
     * 
     * @return boolean true si el bloque llego al borde o toco otro bloque
     */
    private boolean checkRight() {
        if (this.pause)
            return true;
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
        if (this.pause)
            return;
        if (this.block == null) {
            return;
        }
        this.rotateFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        this.checkRotate();
        this.rotateFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        setGhostBlock();
        repaint();
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
     * Arreglo de pruebas para la Tspin
     * int[NumeroDeTest][cooredandas1][cooredandas2]
     */
    private int[][][] tSpinTest = {
            { { 0, 0 }, { 2, 0 } },
            { { 1, 0 }, { 1, 2 } },
            { { 0, 1 }, { 2, 1 } },
            { { 0, 0 }, { 0, 2 } }
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
            SoundsPlayer.playRotate();
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
            if (this.wallKickTest()) {
                SoundsPlayer.playRotate();
                if (this.block.getType() == 'T') {
                    if (this.tSpingTest()) {
                        this.tspinFlag = true;
                        SoundsPlayer.playTspin();
                    }
                }
                return;
            }
        }
        this.block.setX(x);
        this.block.setY(y);
        this.block.rotateBack();
    }

    /**
     * Verifica si se ha realizado un Tspin
     * 
     * @return true si se ha realizado un Tspin
     */
    private boolean tSpingTest() {
        if (this.block.getType() != 'T')
            return false;
        int currentRotation = this.block.getCurrentRotation();
        int x = this.block.getLeftEdge() + tSpinTest[currentRotation][0][0];
        int y = this.block.getTopEdge() + tSpinTest[currentRotation][0][1];
        int x2 = this.block.getLeftEdge() + tSpinTest[currentRotation][1][0];
        int y2 = this.block.getTopEdge() + tSpinTest[currentRotation][1][1];
        if (this.checkBackGround(x, y) && this.checkBackGround(x2, y2))
            return true;
        return false;
    }

    /** mini coords */
    private boolean checkBackGround(int x, int y) {
        if (background[0][y][x] != darkColor) {
            return true;
        }
        return false;
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
        if (this.pause)
            return;
        if (this.block == null) {
            return;
        }
        this.rotateFlag = true;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        this.checkRotateBack();
        this.rotateFlag = false;// !!!!!!!!!!! BANDERA CRITICA !!!!!!!!!!!!!
        setGhostBlock();
        repaint();
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
            SoundsPlayer.playRotate();
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
            if (this.wallKickTest()) {
                SoundsPlayer.playRotate();
                if (this.block.getType() == 'T') {
                    if (this.tSpingTest()) {
                        this.tspinFlag = true;
                        SoundsPlayer.playTspin();
                    }
                }
                return;
            }
        }
        this.block.setX(x);
        this.block.setY(y);
        this.block.rotate();
    }

    /**
     * Verifica si el bloque esta fuera de los limites del tablero
     * 
     * @return true si esta fuera de los limites
     */
    private boolean checkOutOfBounnds() {
        if (block.getLeftEdge() < 0) {
            return true;
        }
        if (block.getRightEdge() > this.colums) {
            return true;
        }
        if (block.getBottomEdge() > this.rows) {
            return true;
        }
        if (block.getTopEdge() < 0) {
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
        this.updateStats(linesCleared);
        return linesCleared;
    }

    private void updateStats(int linesClear) {
        if (linesClear == 0)
            return;
        int level = this.stats.getLevel();
        int score = 0;
        switch (linesClear) {
            case 1:
                if (tspinFlag) {
                    score = 40 * level;
                } else {
                    score = 10 * level;
                }
                SoundsPlayer.playSingle();
                break;
            case 2:
                if (tspinFlag) {
                    score = 80 * level;
                } else {
                    score = 30 * level;
                }
                SoundsPlayer.playSingle();
                break;
            case 3:
                if (tspinFlag) {
                    score = 120 * level;
                } else {
                    score = 50 * level;
                }
                SoundsPlayer.playTriple();
                break;
            case 4:
                if (tspinFlag) {
                    score = 160 * level;
                } else {
                    score = 100 * level;
                }
                SoundsPlayer.playTetris();
                break;
            default:
        }
        this.stats.updateScore(score);
        this.stats.updateLines(linesClear);
        int lines = stats.getLines();
        int actualLevel = stats.getLevel();
        level = lines / 10 + 1;
        if (level > actualLevel) {
            stats.updateLevel(level);
            GameThread.updateWaitingTime(level * level);
            new Thread(() -> {
                try {
                    if (linesClear == 4)
                        Thread.sleep(800);
                    else
                        Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SoundsPlayer.playLevelUp();
            }).start();
        }
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
     * Se encarga de tomar los datos del bloque fantasma
     * llevarlo lo mas bajo posible pardar un señalamiento
     * de donde caera el bloque activo
     */
    private void setGhostBlock() {
        if (this.pause)
            return;
        if (block == null) {
            this.ghostBlock = null;
            return;
        }
        // ghostBlock = new TetrisBlock(block.getBlock(), block.getBlockRotations(),
        // block.getType(),
        // new Color(199, 199, 199), new Color(223, 223, 223));
        ghostBlock = new TetrisBlock(block.getBlock(), block.getBlockRotations(), block.getType(),
                block.getDarkColor().darker().darker(), (block.getLightColor()).darker().darker());
        ghostBlock.setX(block.getX());
        ghostBlock.setY(block.getY());
        ghostBlock.setCurrentRotation(block.getCurrentRotation());

        while (this.moveDownGhost())
            ;
    }

    /**
     * Mueve el bloque para abajo
     * 
     * @return boolean true si se pudo mover
     */
    public boolean moveDownGhost() {
        if (this.pause)
            return false;
        if (this.ghostBlock == null) {
            return false;
        }
        if (this.checkBottomGhost()) {
            return false;
        }

        this.ghostBlock.moveDown();
        return true;
    }

    public boolean checkBottomGhost() throws ArrayIndexOutOfBoundsException {
        if (this.ghostBlock.getBottomEdge() == this.rows) {
            return true;
        }
        // obtencion de datos del bloque
        int shape[][] = this.ghostBlock.getBlock();
        int w = this.ghostBlock.getWidth();
        int h = this.ghostBlock.getHeight();
        int x, y;// se utilizaran para sacar el offsetverdadero y comparar correctamente
        if (ghostBlock.getHeight() != 1) {
            for (int col = 0; col < w; col++) {
                for (int row = h - 1; row >= 0; row--) {
                    if (shape[row][col] != 0) {
                        x = col + ghostBlock.getX();
                        y = row + ghostBlock.getY() + 1;
                        if (background[0][y][x] != darkColor) {
                            return true;
                        }
                        break;
                    }
                }
            }
        } else {
            for (int col = 0; col < w; col++) {
                x = col + ghostBlock.getX();
                y = ghostBlock.getY() + 1;
                if (y < 0)
                    break;
                if (background[0][y][x] != darkColor) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Mueve el bloque fantasma
     * al fondo del tablero
     */
    public void moveBlockToBackGround() {
        if (this.pause)
            return;
        if (block == null) {
            return;
        }
        int[][] shape = block.getBlock();
        int h = block.getHeight();
        int w = block.getWidth();
        int xPos = block.getX();
        int yPos = block.getY();
        Color darkColor = block.getDarkColor();
        Color brigthColor = block.getLightColor();
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (shape[r][c] == 1) {
                    background[0][r + yPos][c + xPos] = darkColor;
                    background[1][r + yPos][c + xPos] = brigthColor;
                    background[2][r + yPos][c + xPos] = olColor;
                }
            }
        }
        this.block = null;
        if (hardDropFlag)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        SoundsPlayer.playFall();
    }

    /**
     * Verifica el borde inferior del bloque fantasma en 2 condiciones
     * toco el borde del tablero o a otro bloque
     * 
     * @return boolean true si el bloque llego al fondo o toco otro bloque
     */

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
        int[][] shape = block.getBlock();
        int heigth = block.getHeight();
        int width = block.getWidth();
        for (int row = 0; row < heigth; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] == 1) {
                    yi = row + block.getY();
                    xi = col + block.getX();
                    this.drawGameSquare(g, yi, xi, darkColor, brigthColor, olColor);
                }
            }
        }
    }

    /**
     * Dibuja el bloque
     * fantasma del tablero
     * 
     * @param g Graphics
     */
    private void drawGhostBlock(Graphics g) {
        ;
        if (this.block == null)
            return;
        if (ghostBlock == null)
            return;
        int yi;
        int xi;
        Color darkColor = ghostBlock.getDarkColor();
        Color brigthColor = ghostBlock.getLightColor();
        Color olColor = block.getBorderColor();
        int heigth = ghostBlock.getHeight();
        int width = ghostBlock.getWidth();
        for (int row = 0; row < heigth; row++) {
            for (int col = 0; col < width; col++) {
                if (ghostBlock.getBlock()[row][col] == 1) {
                    yi = row + ghostBlock.getY();
                    xi = col + ghostBlock.getX();
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
        this.drawGhostBlock(g);
        this.drawBlock(g);
    }
}
