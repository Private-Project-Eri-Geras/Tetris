package com.leviatanes.tetris.TetrisGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControls implements KeyListener {
    private GameArea gameArea;
    private GameThread gameThread;

    public GameControls(GameArea gameArea, GameThread gameThread) {
        this.gameArea = gameArea;
        this.gameThread = gameThread;
    }

    private static final int rotate = 87;
    private static final int counterRotate = 69;
    private static final int left = 65;
    private static final int rigth = 68;
    private static final int down = 83;
    private static final int drop = 32;
    private static final int pause = 80;
    private static final int acelerate = 16;

    @Override
    /**
     * Metodo que se ejecuta cuando se presiona una tecla
     * detectaremos el momento de la pulsacion de las teclas para hacer acciones
     * 
     * w = rotar bloque (codigo 87)
     * e = contra rotar bloque (codigo 69)
     * a = mover bloque a la izquierda (codigo 65)
     * d = mover bloque hacia abajo (codigo 68)
     * s = mover bloque a la derecha (codigo 83)
     * spacio = bajar bloque (codigo 32)
     * shitf = acelerar caida (codigo 16)
     * p = pausar juego (codigo 80)
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
        if (key.getKeyCode() == acelerate)
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
                gameThread.resetTime(); // se resetea el tiempo para bloquear el bloque en el fondo
                break;
            case left:
                gameArea.moveLeft();
                break;
            case rigth:
                gameArea.moveRight();
                break;
            case down:
                gameArea.moveDown();
                break;
            case drop:
                gameArea.drop();
                break;
            case acelerate:
                gameThread.acelerateGameSpeed();
                break;
            case counterRotate:
                gameArea.rotateBack();
                gameThread.resetTime(); // se resetea el tiempo para bloquear el bloque en el fondo
            default:
                break;
        }
    }

}
