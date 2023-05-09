package com.leviatanes.tetris.tetrisGame.game.gameOver;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JPanel {
    // base resolution
    private static final int width = 90;
    private static final int height = 80;
    // offset del label de GAME OVER
    private static final int gameOverX = 20;
    private static final int gameOverY = 10;
    private static final int gameOverW = 50;
    private static final int gameOverH = 10;
    // base resolutions of ScorePanels
    private static final int scoreX = 10;
    private static final int scoreY = 25;
    private static final int scoreW = 70;
    private static final int scoreH = 50;
    // multiplicador
    private int multiplier;

    private JLabel lblGameOver;
    private int score;
    private Score scores[];
    private ScoreReader read;

    public GameOver(int multiplier) {
        this.multiplier = multiplier;
        this.read = new ScoreReader();
        this.initComponents();

    }

    public void setScore(int score) {
        this.score = score;
    }

    private void initComponents() {
        this.setBounds(0, 0, width * multiplier, height * multiplier);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 125));
        int x = gameOverX * multiplier;
        int y = gameOverY * multiplier;
        int w = gameOverW * multiplier;
        int h = gameOverH * multiplier;
        this.lblGameOver = new JLabel("GAME OVER");
        this.lblGameOver.setBounds(x, y, w, h);
        this.lblGameOver.setForeground(Color.WHITE);
        this.lblGameOver.setHorizontalAlignment(JLabel.CENTER);
        this.lblGameOver.setVerticalAlignment(JLabel.CENTER);
        // ajustar el tamaño de la fuente al tamaño de la etiqueta
        for (int i = 1;; i++) {
            this.lblGameOver.setFont(new java.awt.Font("Tahoma", 1, i));
            int fontW = lblGameOver.getFontMetrics(lblGameOver.getFont()).stringWidth(lblGameOver.getText());
            int fontH = lblGameOver.getFontMetrics(lblGameOver.getFont()).getHeight();
            if (fontW > w || fontH > h) {
                this.lblGameOver.setFont(new java.awt.Font("Tahoma", 1, i - 1));
                break;
            }
        }
        this.add(lblGameOver);
        scores = read.readScores();
    }

    public void endGame() {
        boolean isHighScore = false;
        for (int i = 0; i < scores.length; i++) {
            if (this.score > scores[i].getScore()) {
                isHighScore = true;
                break;
            }
        }
        if (isHighScore) {
            this.lblGameOver.setText("HIGH SCORE");
        } else {
            this.lblGameOver.setText("GAME OVER");
        }
    }
}
