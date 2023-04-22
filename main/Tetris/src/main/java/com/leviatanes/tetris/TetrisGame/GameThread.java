package com.leviatanes.tetris.TetrisGame;

import java.util.logging.Logger;
import java.util.logging.Level;

import com.leviatanes.tetris.TetrisPanel;

public class GameThread extends Thread {
    private boolean paused;
    private int waitingTime;
    private int actualSpeed;
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
        this.actualSpeed = this.waitingTime;
    }

    public void run() {
        try {
            while (true) {
                gameArea.spawnBlock();
                Thread.sleep(waitingTime);
                gameArea.moveDown();
                Thread.sleep(waitingTime);
                // si se pulsa la tecla pausa se pausa el juego
                if (this.paused)
                    pause();

                while (gameArea.moveDown()) {
                    Thread.sleep(waitingTime);
                    if (this.paused)
                        pause();
                }

                if (gameArea.isGameOver()) {
                    break;
                }

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void togglePause() {
        this.paused = !this.paused;

    }

    public void pause() throws InterruptedException {
        while (this.paused) {
            Thread.sleep(100);
            if (!this.paused)
                break;
            Thread.sleep(100);
        }
    }

    /**
     * Acelera la velocidad de caida a 1/10 de la velocidad actual
     */
    public void acelerateGameSpeed() {
        this.actualSpeed = this.waitingTime / 10;
    }

    /**
     * Restablece la velocidad de caida a la velocidad normal
     */
    public void restoreGameSpeed() {
        this.actualSpeed = this.waitingTime;
    }

}
