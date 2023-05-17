package com.leviatanes.tetris.tetrisGame.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.leviatanes.tetris.SoundsPlayer;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.*;

import com.leviatanes.menus.SettingsReader;

/**
 * Clase que lee las teclas presionadas por el usuario y
 * ejecuta las acciones correspondientes
 * 
 * @author Gerardo
 */
public class GameControls implements KeyListener {
    private GameArea gameArea;
    private GameThread gameThread;
    private HoldPanel stashShape;
    private static boolean keyTyped[] = new boolean[256];

    private int left;
    private int left2;
    private int rigth;
    private int rigth2;
    private int rotate;
    private int down;
    private int down2;
    private int counterRotate;
    
    
    private int softDrop;
    private int hardDrop;
    private int pause;
    private int hold;
    private int hold2;
    private int mute;

    public GameControls(GameArea gameArea, GameThread gameThread, HoldPanel stashShape) {
        this.gameArea = gameArea;
        this.gameThread = gameThread;
        this.stashShape = stashShape;
        this.setKeys();
    }

    private void setKeys() {
        rotate = SettingsReader.getRotate();
        counterRotate = SettingsReader.getCounterRotate();
        left = SettingsReader.getLeft();
        left2 = SettingsReader.getLeft2();
        rigth = SettingsReader.getRigth();
        rigth2 = SettingsReader.getRigth2();
        down = SettingsReader.getDown();
        down2 = SettingsReader.getDown2();
        softDrop = SettingsReader.getSoftDrop();
        hardDrop = SettingsReader.getHardDrop();
        pause = SettingsReader.getPause();
        hold = SettingsReader.getHold();
        hold2 = SettingsReader.getHold2();
        mute = SettingsReader.getMute();
    }

    @Override

    public void keyPressed(KeyEvent key) {
        if (keyTyped[key.getKeyCode()] == true)
            return;
        if (this.isPause(key)) // si se pulsa la tecla pausa se pausa el juego
            return;
        if (gameArea.isGameOver() == false) // si el juego no ha terminado se controla el movimiento
            this.controlGame(key);
        keyTyped[key.getKeyCode()] = true;
    }

    @Override
    /**
     * Utilizaremos en caso de ser nesesario detectar una realese
     */
    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == softDrop)
            gameThread.restoreGameSpeed();
        keyTyped[key.getKeyCode()] = false;
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
    private void controlGame(KeyEvent keyPressed) {
        if (gameArea.getBlock() == null || gameThread.isPaused())
            return;
        final int key = keyPressed.getKeyCode();
        if (key == down || key == down2)
            gameArea.moveDown();
        if (key == rotate)
            gameArea.rotate();
        if (key == left || key == left2)
            gameArea.moveLeft();
        if (key == rigth || key == rigth2)
            gameArea.moveRight();
        if (key == softDrop)
            gameThread.softDrop();
        if (key == hardDrop)
            gameArea.hardDrop();
        if (key == counterRotate)
            gameArea.rotateBack();
        if (key == hold || key == hold2) {
            if (!stashShape.isHoldAllowed())
                return;
            gameArea.swap();
            stashShape.setHoldAllowed(false);
            stashShape.setHoldedShape(gameArea.getHoldedBlock());
        }
        if (key == mute)
            SoundsPlayer.toggleMuteMain();
    }

}
