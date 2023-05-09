package com.leviatanes.tetris.tetrisGame.game.gameOver;

import java.awt.Color;

import javax.swing.JPanel;

public class GameOver extends JPanel {
    // base resolution
    private static final int width = 90;
    private static final int height = 80;
    // multiplicador
    private int multiplier;

    public GameOver(int multiplier) {
        this.multiplier = multiplier;
        this.initComponents();
    }

    private void initComponents() {
        this.setBounds(0, 0, width * multiplier, height * multiplier);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 125));
    }

}
