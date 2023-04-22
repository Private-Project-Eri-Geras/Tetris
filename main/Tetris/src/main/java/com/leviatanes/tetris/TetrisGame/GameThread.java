package com.leviatanes.tetris.TetrisGame;

import java.util.logging.Logger;
import java.util.logging.Level;

import com.leviatanes.tetris.TetrisPanel;

public class GameThread {
    private boolean paused;
    private int waitingTime;
    private GameArea gameArea;
    private TetrisPanel tetrisPanel;

    /**
     * Constructor por defecto
     * 
     * @param gameArea    Area de juego que controla el movimiento
     * @param tetrisPanel Panel de tetris tendra la informacion del juego a
     *                    actualizar
     */
    public GameThread(GameArea gameArea, TetrisPanel tetrisPanel) {
        this.gameArea = gameArea;
        this.tetrisPanel = tetrisPanel;
        this.paused = false;
        this.waitingTime = 1000;
    }

    public void run() {
        try {
            while (true) {
                gameArea.spawnBlock();
                Thread.sleep(waitingTime);
                gameArea.moveDown();
                Thread.sleep(waitingTime);
                while (gameArea.moveDown()) {
                    Thread.sleep(waitingTime);
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
