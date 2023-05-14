package com.leviatanes.tetris.tetrisGame.game.gameOver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leviatanes.tetris.Main;
import com.leviatanes.tetris.SoundsPlayer;

import com.leviatanes.tetris.tetrisGame.game.gameOver.scorePanel.*;

/**
 * [GAME OVER]
 * Este panel se encarga de mostrar el menu final
 * y controlar cual de los dos menus se muestra
 * dependiendo de si el usuario consiguió un highscore o no
 * La clase se apoya de un Score [String int] para gestionar los puntajes
 * y esto los lee de un archivo de texto apoyandose de la clase ScoreReader
 * 
 * @autor Leonardo
 * @autor Eriarer (Abraham)
 * 
 * @see NormalScore
 * @see HighScore
 * @see ScoreReader
 * @see Score
 */
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
    private Main main;

    public GameOver(int multiplier, Main main) {
        this.main = main;
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

    /**
     * Empieza el proceso para termiar el juego
     * llamando a los paneles correspondientes
     * segun el puntaje obtenido
     */
    public void endGame() {
        SoundsPlayer.fadeOutMain();
        boolean isHighScore = false;
        if (scores.length < 10) {// inicio iff
            isHighScore = true;
        } else {
            // busca si el puntaje es mayor a alguno de los 10
            for (int i = 0; i < scores.length; i++) {
                if (actualScore > scores[i].getScore()) {
                    isHighScore = true;
                    break;
                }
            }
        }
        // fin if
        // si el score es 0 o no es highscore se pierde
        if (actualScore == 0 || isHighScore == false)
            loseGame();
        else
            highScore();

    }

    /**
     * Muestra el panel del fin del juego
     * en caso de que el usuario haya perdido
     * o no haya conseguido un highscore
     */
    private void loseGame() {
        this.lblGameOver.setText("GAME OVER");
        NormalScore normalScore = new NormalScore(multiplier, actualScore, this.lines,this.main);
        this.scorePanel.add(normalScore, BorderLayout.CENTER);
        this.scorePanel.revalidate();
        this.scorePanel.repaint();
    }

    /**
     * Muestra el panel del fin del juego
     * en caso de que el usuario haya conseguido
     * un highscore y lo muestra en pantalla
     * llama a la clase HighScore la cual
     * terminara el juego llamando al metodo {@link #highScoreEnd()}
     */
    private void highScore() {
        this.lblGameOver.setText("YOU ARE THE GOAT");
        // busca descartar la idea de que es el mejor puntaje
        // si no lo es se busca en que posicion quedo
        for (int i = scores.length - 1; i >= 0; i--) {
            if (actualScore <= scores[i].getScore()) {
                this.lblGameOver.setText("YOU ARE THE NUMBER " + (i + 2) + "!");
                isGoat = false;
                break;
            }
        }
        // reproduce un sonido
        if (isGoat)
            SoundsPlayer.playHighestScore();
        else
            SoundsPlayer.playHighScore();
        setLabelText(lblGameOver);
        highScore = new HighScore(multiplier, actualScore,main);
        this.scorePanel.add(highScore, BorderLayout.CENTER);
        this.scorePanel.revalidate();
        this.scorePanel.repaint();
    }

    /**
     * Termina completamente el juego
     * metodo exclusivamente llamado por la clase {@link HighScore}
     * remplace el puntaje en el archivo txt consiguiento
     * el nombre ingresado por el usuario
     * el puntaje conseguido
     */
    public static void highScoreEnd() {
        Score score = new Score(highScore.getName(), actualScore);
        read.replaceScore(score);
    }

    /** pone un texto en el label ajustando su tamaño */
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
