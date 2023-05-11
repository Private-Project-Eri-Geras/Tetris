package com.leviatanes.tetris.tetrisGame.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.InputStream;

import com.leviatanes.tetris.SoundsPlayer;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.StatsPanel;
import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;

public class GameThread extends Thread {
    /** bandera de juego pausado */
    private boolean paused;
    /** tiempo de espera normal */
    private int waitingTime;
    /** tiempo de espera real */
    private int actualSpeed;
    /** game area para llamar a las funciones necesarias */
    private GameArea gameArea;
    /** nos permite modificar las estadisticas */
    private StatsPanel statsPanel;
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
    public GameThread(GameArea gameArea, StatsPanel statsPanel) {
        this.gameArea = gameArea;
        this.statsPanel = statsPanel;
        this.paused = false;
        this.waitingTime = 1000;
        this.actualSpeed = this.waitingTime;
    }

    public void run() {
        int linesClearedAtOnce = 0;
        SoundsPlayer.playGameMusic();
        while (true) {
            System.out.println("    run");
            if (this.spawn())
                break;

            waiting();
            // si se pulsa la tecla pausa se pausa el juego
            System.out.println("    bef while moveDown");
            while (gameArea.moveDown()) {
                if (waiting())
                    break;
            }

            System.out.println("    aft while moveDown");
            if (settleBlock()) {
                linesClearedAtOnce = gameArea.clearLines();
                statsPanel.updateLines(linesClearedAtOnce);
                scoring(linesClearedAtOnce);
                leveling();

                gameArea.repaint();
            }
            System.out.println("    reset run");
        }
        System.out.println("    end run");
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
        System.out.println("    waiting");
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

    public boolean settleBlock() {
        if (gameArea.getBlock() == null)
            return false;
        if (gameArea.isHardDrop()) {
            gameArea.moveBlockToBackGround();
            return true;
        }
        System.out.println("    settleBlock");
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
        }
        System.out.println("    bef checkToDrop");
        if (gameArea.checkToDrop() == true) {
            System.out.println("    aft checkToDrop en settleBlock");
            while (gameArea.moveDown())
                ;
            gameArea.moveBlockToBackGround();
            return true;
        }
        gameArea.disableBlockDropped();
        return false;
    }

    /**
     * Acelera la velocidad de caida a 1/10 de la velocidad actual
     */
    public void softDrop() {
        this.actualSpeed = this.waitingTime / 20;
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
     * Calcula y actualiza el score por linea
     * single = 10 * (level)
     * double = 30 * (level)
     * triple = 50 * (level)
     * tetris = 80 * (level)
     */
    private void scoring(int linesClearedAtOnce) {
        int level = statsPanel.getLevel();
        int score = 0;
        switch (linesClearedAtOnce) {
            case 1:
                score += 10 * level;
                break;
            case 2:
                score += 30 * level;
                break;
            case 3:
                score += 50 * level;
                break;
            case 4:
                score += 80 * level;
                break;
        }
        statsPanel.updateScore(score);
    }

    /**
     * Calcula y actualiza el nivel
     * 10 lineas = 1 nivel
     */
    private void leveling() {
        int lines = statsPanel.getLines();
        if (lines == 0)
            return;
        int actualLevel = statsPanel.getLevel();
        int level = lines / 10 + 1;
        if (level > actualLevel) {
            statsPanel.updateLevel(level);
            this.waitingTime -= level * level;
        }
    }

    /**
     * Genera un bloque nuevo
     * y verifica que no sea game over
     */
    private boolean spawn() {
        if (gameArea.getBlock() == null) {
            gameArea.spawnBlock();
            while (gameArea.getSpawnedFlag())
                ;
            if (gameArea.isGameOver()) {
                return true;
            }
            gameArea.repaint();
        }
        return false;
    }
}