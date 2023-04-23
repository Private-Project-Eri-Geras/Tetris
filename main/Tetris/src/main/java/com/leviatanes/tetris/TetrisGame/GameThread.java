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
    /** contador de rotaciones permitidas */
    private int rotationCount;
    /** tiempo de inicio */
    private long startTime;
    /** tiempo de fin */
    private long endTime;
    /** tiempo transcurrido */
    private long elapsedTime;
    /** numero maximo de rotaciones */
    private static final int maxRotations = 15;

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
                Thread.sleep(100);
                gameArea.spawnBlock();
                if (gameArea.isGameOver())
                    break;
                gameArea.repaint();
                waiting();
                // si se pulsa la tecla pausa se pausa el juego

                if (gameArea.isBlockDropped() == false)
                    while (gameArea.moveDown()) {
                        if (waiting() == true)
                            break;
                        if (this.paused)
                            pause();
                    }
                settleBlock();

                gameArea.clearLines();
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funcion que hace esperar al hilo de ejecucion
     * si el bloque dejo de moverse es necesario spawnear uno de inmediato
     * 
     * @return true si el bloque dejo de moverse, false si no
     * @throws InterruptedException
     */
    public boolean waiting() throws InterruptedException {
        startTime = System.currentTimeMillis();
        while (getElapsedTime() < actualSpeed) {
            if (this.paused)
                this.pause();
            if (gameArea.isBlockDropped())
                return true;
            if (gameArea.checkBottom())
                return true;
        }
        return false;
    }

    public void togglePause() {
        this.paused = !this.paused;

    }

    public void pause() throws InterruptedException {
        TetrisBlock block = gameArea.getBlock();
        gameArea.setBlock(null);
        while (this.paused) {
            Thread.sleep(100);
        }
        gameArea.setBlock(block);
    }

    public void settleBlock() throws InterruptedException {
        startTime = System.currentTimeMillis();
        while (getElapsedTime() < 500 && rotationCount < maxRotations) {
            if (this.paused)
                this.pause();
        }
        gameArea.moveBlockToBackGround();
        gameArea.setBlock(null);
        rotationCount = 0;
    }

    /**
     * Acelera la velocidad de caida a 1/10 de la velocidad actual
     */
    public void acelerateGameSpeed() {
        this.actualSpeed = this.waitingTime / 30;
    }

    /**
     * Restablece la velocidad de caida a la velocidad normal
     */
    public void restoreGameSpeed() {
        this.actualSpeed = this.waitingTime;
    }

    /**
     * Devuelve el estado de pausa del juego
     * 
     * @return true si esta pausado, false si no lo esta
     */
    public boolean isPaused() {
        return this.paused;
    }

    /**
     * Resetea el tiempo de inicio
     */
    public void resetTime() {
        this.rotationCount++;
        startTime = System.currentTimeMillis();
    }

    /**
     * Devuelve el tiempo transcurrido
     * 
     * @return
     */
    private long getElapsedTime() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        return elapsedTime;
    }
}