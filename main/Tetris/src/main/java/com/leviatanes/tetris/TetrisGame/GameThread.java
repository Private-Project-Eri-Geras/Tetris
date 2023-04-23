package com.leviatanes.tetris.TetrisGame;

import java.util.logging.Logger;
import java.nio.channels.Pipe;
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
    private long startSettleTime;
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
        while (true) {
            if (gameArea.getBlock() == null) {
                startTime = System.currentTimeMillis();
                while (getElapsedTime() < 500)
                    ;
                gameArea.spawnBlock();
                if (gameArea.isGameOver())
                    break;
                gameArea.repaint();
                waiting();
            }
            // si se pulsa la tecla pausa se pausa el juego

            while (gameArea.moveDown()) {
                if (waiting() == true)
                    break;
                if (this.paused)
                    pause();
            }

            settleBlock();

            gameArea.clearLines();
        }

    }

    /**
     * Funcion que hace esperar al hilo de ejecucion
     * si el bloque dejo de moverse es necesario spawnear uno de inmediato
     * 
     * @return true si el bloque dejo de moverse, false si no
     */
    public boolean waiting() {
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

    public void pause() {
        TetrisBlock block = gameArea.getBlock();
        gameArea.setBlock(null);
        while (this.paused) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameArea.setBlock(block);
    }

    public void settleBlock() {
        startSettleTime = System.currentTimeMillis();
        rotationCount = 0;
        while (getSettleTime() < 500) {
            System.out.println(getSettleTime());
            if (rotationCount >= maxRotations)
                break;
            if (this.paused) {
                this.pause();
                startTime = System.currentTimeMillis();
            }
        }
        if (gameArea.dropPiece() == true) {
            while (gameArea.moveDown())
                ;
            gameArea.moveBlockToBackGround();
            gameArea.setBlock(null);
        }
        gameArea.disableBlockDropped();
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
    public void resetSettleTime() {
        this.rotationCount++;
        startSettleTime = System.currentTimeMillis();
    }

    private long getSettleTime() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startSettleTime;
        return elapsedTime;
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