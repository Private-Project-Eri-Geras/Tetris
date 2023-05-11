package com.leviatanes.tetris.tetrisGame.game.gameOver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leviatanes.tetris.SoundsPlayer;
import com.leviatanes.tetris.tetrisGame.game.GameThread;

import com.leviatanes.tetris.tetrisGame.game.gameOver.scorePanel.*;

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

    /** etiqueta de juego perdido */
    private JLabel lblGameOver;
    /** panel de puntos */
    private JPanel scorePanel;
    /** puntos actuales */
    private int score;
    /** lineas actuales */
    private int lines;
    /** vector de scres del txt */
    private Score scores[];
    /** objeto de lectura del score */
    private ScoreReader read;

    public GameOver(int multiplier) {
        this.multiplier = multiplier;
        this.read = new ScoreReader();
        this.initComponents();

    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setPuntuation(int score, int lines) {
        this.score = score;
        this.lines = lines;
    }

    public void revalidation() {
        this.initComponents();
        this.repaint();
    }

    private void initComponents() {
        this.setBounds(0, 0, width * multiplier, height * multiplier);
        this.setLayout(null);
        this.setOpaque(false);
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
            this.lblGameOver.setFont(new java.awt.Font("Impact", 1, i));
            int fontW = lblGameOver.getFontMetrics(lblGameOver.getFont()).stringWidth(lblGameOver.getText());
            int fontH = lblGameOver.getFontMetrics(lblGameOver.getFont()).getHeight();
            if (fontW > w || fontH > h) {
                this.lblGameOver.setFont(new java.awt.Font("Impact", 1, i - 1));
                break;
            }
        }
        x = scoreX * multiplier;
        y = scoreY * multiplier;
        w = scoreW * multiplier;
        h = scoreH * multiplier;
        this.scorePanel = new JPanel();
        this.scorePanel.setBounds(x, y, w, h);
        this.scorePanel.setLayout(null);
        this.scorePanel.setOpaque(false);
        this.add(scorePanel);
        this.add(lblGameOver);
        scores = read.readScores();
    }

    public void endGame() {
        SoundsPlayer.fadeOutMain();
        boolean isHighScore = false;
        if (scores.length < 10)
            isHighScore = true;
        else
            for (int i = 0; i < scores.length; i++) {
                if (this.score > scores[i].getScore()) {
                    isHighScore = true;
                    break;
                }
            }
        if (isHighScore && this.score > 0) {
            this.lblGameOver.setText("HIGH SCORE");
            Score score = new Score("bbb", this.score);
            System.out.println("Score" + this.score);
            this.read.replaceScore(score);
            HighScore highScore = new HighScore(multiplier, this.score);
            this.scorePanel.add(highScore, BorderLayout.CENTER);
            this.scorePanel.revalidate();
            this.scorePanel.repaint();
        } else {
            this.lblGameOver.setText("GAME OVER");
            System.out.println("Score" + this.score);
            NormalScore normalScore = new NormalScore(multiplier, this.score, this.lines);
            this.scorePanel.add(normalScore, BorderLayout.CENTER);
            this.scorePanel.revalidate();
            this.scorePanel.repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, width * multiplier, height * multiplier);
    }
}
