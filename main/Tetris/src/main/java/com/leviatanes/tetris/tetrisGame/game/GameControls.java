package com.leviatanes.tetris.tetrisGame.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.leviatanes.tetris.tetrisGame.game.sidePanels.*;

public class GameControls implements KeyListener {
    private GameArea gameArea;
    private GameThread gameThread;
    private HoldPanel stashShape;

    public GameControls(GameArea gameArea, GameThread gameThread, HoldPanel stashShape) {
        this.gameArea = gameArea;
        this.gameThread = gameThread;
        this.stashShape = stashShape;
    }

    private static final int rotate = 87;
    private static final int counterRotate = 69;
    private static final int left = 65;
    private static final int left2 = 37;
    private static final int rigth = 68;
    private static final int rigth2 = 39;
    private static final int down = 83;
    private static final int down2 = 40;
    private static final int shoftDrop = 16;
    private static final int hardDrop = 32;
    private static final int pause = 80;
    private static final int hold = 82;
    private static final int hold2 = 17;

    @Override
    /**
     * Metodo que se ejecuta cuando se presiona una tecla
     * detectaremos el momento de la pulsacion de las teclas para hacer acciones
     * 
     * w = rotar bloque (codigo 87)
     * e = contra rotar bloque (codigo 69)
     * a = mover bloque a la izquierda (codigo 65)
     * flecha derecha = mover bloque a la derecha (codigo 39)
     * d = mover bloque hacia abajo (codigo 68)
     * flecha abajo = bajar bloque (codigo 40)
     * s = mover bloque a la derecha (codigo 83)
     * flecha izquierda = mover bloque a la izquierda (codigo 37)
     * spacio = hardDrop (codigo 32)
     * shitf = softDrop (codigo 16)
     * p = pausar juego (codigo 80)
     * r = hacer swap de piezas (codigo 82)
     * control = hacer swap de piezas (codigo 17)
     */
    public void keyPressed(KeyEvent key) {
        if (this.isPause(key)) // si se pulsa la tecla pausa se pausa el juego
            return;
        if (gameArea.isGameOver() == false) // si el juego no ha terminado se controla el movimiento
            this.controlGame(key);
    }

    @Override
    /**
     * Utilizaremos en caso de ser nesesario detectar una realese
     */
    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == shoftDrop)
            gameThread.restoreGameSpeed();
    }

    @Override
    /** Metodo no usado */
    public void keyTyped(KeyEvent key) {

    }

    /**
     * setea la pausa
     * 
     * @return true si se pauso el juego
     */
    private boolean isPause(KeyEvent key) {
        if (key.getKeyCode() == pause) {
            gameThread.togglePause();
            return true;
        }
        return false;
    }

    /**
     * Controla el movimiento del juego
     * 
     */
    private void controlGame(KeyEvent key) {
        if (gameArea.getBlock() == null || gameThread.isPaused())
            return;

        switch (key.getKeyCode()) {
            case rotate:
                gameArea.rotate();
                gameThread.resetSettleTime(); // se resetea el tiempo para bloquear el bloque en el fondo
                break;
            case left2:
            case left:
                gameArea.moveLeft();
                break;
            case rigth2:
            case rigth:
                gameArea.moveRight();
                break;
            case down2:
            case down:
                gameArea.moveDown();
                gameThread.resetTime(); // se resetea el tiempo para bajar el bloque
                break;
            case shoftDrop:
                gameThread.softDrop();
                break;
            case hardDrop:
                gameArea.hardDrop();
                break;
            case counterRotate:
                gameArea.rotateBack();
                gameThread.resetSettleTime(); // se resetea el tiempo para bloquear el bloque en el fondo
                break;
            case hold2:
            case hold:
                if (!stashShape.isHoldAllowed())
                    return;
                gameArea.swap();
                stashShape.setHoldAllowed(false);
                stashShape.setHoldedShape(gameArea.getHoldedBlock());
                break;

            default:
                break;
        }
    }

}
