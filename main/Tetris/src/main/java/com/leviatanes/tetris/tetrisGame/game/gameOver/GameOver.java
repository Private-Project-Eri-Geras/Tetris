package com.leviatanes.tetris.tetrisGame.game.gameOver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leviatanes.tetris.SoundsPlayer;

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
    /** highscore */
    private static HighScore highScore = null;
    /** panel de puntos */
    private JPanel scorePanel;
    /** puntos actuales */
    private static int actualScore = 0;
    /** goat */
    private static boolean isGoat = true;
    /** lineas actuales */
    private int lines;
    /** vector de scres del txt */
    private Score scores[];
    /** objeto de lectura del score */
    private static ScoreReader read;

    public GameOver(int multiplier) {
        this.multiplier = multiplier;
        read = new ScoreReader();
        this.initComponents();

    }

    public void setActualScore(int score1) {
        actualScore = score1;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setPuntuation(int score1, int lines) {
        actualScore = score1;
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
        this.lblGameOver = new JLabel();
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
                if (actualScore > scores[i].getScore()) {
                    isHighScore = true;
                    break;
                }
            }
        if (actualScore == 0 || isHighScore == false)
            loseGame();
        else
            highScore();

    }

    private void loseGame() {
        this.lblGameOver.setText("GAME OVER");
        System.out.println("Score" + actualScore);
        NormalScore normalScore = new NormalScore(multiplier, actualScore, this.lines);
        this.scorePanel.add(normalScore, BorderLayout.CENTER);
        this.scorePanel.revalidate();
        this.scorePanel.repaint();
    }

    private void highScore() {

        this.lblGameOver.setText("YOU ARE THE GOAT");
        for (int i = scores.length - 1; i >= 0; i--) {
            if (actualScore <= scores[i].getScore()) {
                this.lblGameOver.setText("YOU ARE THE NUMBER " + (i + 2) + "!");
                isGoat = false;
                break;
            }
        }
        setLabelText(lblGameOver);
        highScore = new HighScore(multiplier, actualScore);
        this.scorePanel.add(highScore, BorderLayout.CENTER);
        this.scorePanel.revalidate();
        this.scorePanel.repaint();    
    }

    public static void highScoreEnd(){
        Score score = new Score(highScore.getName(), actualScore);
        read.replaceScore(score);
        System.out.println("Score final" + score);
        if (isGoat)
            SoundsPlayer.playHighestScore();
        else
            SoundsPlayer.playHighScore();
    }
    private void setLabelText(JLabel label) {
        int w = label.getWidth();
        int h = label.getHeight();
        for (int i = 1;; i++) {
            label.setFont(new java.awt.Font("Impact", 1, i));
            int fontW = label.getFontMetrics(label.getFont()).stringWidth(label.getText());
            int fontH = label.getFontMetrics(label.getFont()).getHeight();
            if (fontW > w || fontH > h) {
                label.setFont(new java.awt.Font("Impact", 1, i - 1));
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, width * multiplier, height * multiplier);
    }
}
