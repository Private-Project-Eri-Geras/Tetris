package com.leviatanes.tetris.tetrisGame.game;

import com.leviatanes.tetris.SoundsPlayer;

/**
 * Clase que controla el hilo de ejecucion del juego
 * haciendo que el bloque baje el bloque
 * controla la velocidad del juego y el spawn de los bloques
 * 
 * @author Eriarer (Abraham)
 * 
 * @see GameArea
 */
public class GameThread extends Thread {
    /** bandera de juego pausado */
    private boolean paused;
    /** tiempo de espera normal */
    private static int waitingTime;
    /** tiempo de espera real */
    private int actualSpeed;
    /** game area para llamar a las funciones necesarias */
    private GameArea gameArea;
    /** contador de rotaciones permitidas */
    private int rotationCount;
    /** tiempo de inicio */
    private long startTime;
    private long startSettleTime;
    /** tiempo de fin */
    private long endTime;
    /** tiempo transcurrido */
    private long elapsedTime;
    private static final int maxRotations = 15;

    /**
     * Constructor por defecto
     * 
     * @param gameArea    Area de juego que controla el movimiento
     * @param tetrisPanel Panel de tetris tendra la informacion del juego a
     *                    actualizar
     */
    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
        this.paused = false;
        waitingTime = 1000;
        this.actualSpeed = waitingTime;
    }

    public void run() {
        SoundsPlayer.playGameMusic();
        while (true) {
            actualSpeed = waitingTime;
            if (this.spawn())
                break;

            waiting();
            // si se pulsa la tecla pausa se pausa el juego
            while (gameArea.moveDown()) {
                if (waiting())
                    break;
            }

            if (settleBlock())
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
        if (gameArea.isHardDrop() || gameArea.getBlock() == null)
            return true;
        startTime = System.currentTimeMillis();
        while (getElapsedTime() < actualSpeed) {
            if (this.paused)
                this.pause();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameArea.isBlockDropped() == true)
                break;
        }
        return false;
    }

    public void togglePause() {
        this.paused = !this.paused;

    }

    public void pause() {
        while (this.paused) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean settleBlock() {
        if (gameArea.getBlock() == null)
            return false;
        if (gameArea.isHardDrop()) {
            gameArea.moveBlockToBackGround();
            return true;
        }
        startSettleTime = System.currentTimeMillis();
        rotationCount = 0;
        while (getSettleTime() < 500) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (rotationCount >= maxRotations)
                break;
            if (this.paused) {
                this.pause();
                startTime = System.currentTimeMillis() - elapsedTime;
            }
            if (gameArea.isHardDrop()) {
                gameArea.moveBlockToBackGround();
                return true;
            }
        }
        if (gameArea.checkToDrop() == true) {
            while (gameArea.moveDown())
                ;
            gameArea.moveBlockToBackGround();
            gameArea.repaint();
            return true;
        }
        gameArea.disableBlockDropped();
        return false;
    }

    /**
     * Acelera la velocidad de caida a 1/10 de la velocidad actual
     */
    public void softDrop() {
        this.actualSpeed = waitingTime / 20;
    }

    /**
     * Restablece la velocidad de caida a la velocidad normal
     */
    public void restoreGameSpeed() {
        this.actualSpeed = waitingTime;
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

    /**
     * Resetea le tiempo de espera
     */
    public void resetTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Devuelve el tiempo transcurrido
     * 
     * @return long el tiempo transcurrido
     */
    private long getSettleTime() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startSettleTime;
        return elapsedTime;
    }

    /**
     * Devuelve el tiempo transcurrido
     * 
     * @return long el tiempo transcurrido
     */
    private long getElapsedTime() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        return elapsedTime;
    }

    /**
     * Genera un bloque nuevo
     * y verifica que no sea game over
     * si se pudo spawnear el bloque devuelve false
     */
    private boolean spawn() {
        if (gameArea.getBlock() == null) {
            if (!gameArea.spawnBlock())
                return true;
        }
        return false;
    }

    public static void updateWaitingTime(int value) {
        waitingTime -= value;
    }
}