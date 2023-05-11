package com.leviatanes.tetris.tetrisGame.game.gameOver.scorePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.leviatanes.tetris.tetrisGame.game.sidePanels.ScoreLabel;

public class HighScore extends JPanel {
    /** Label de score */
    private ScoreLabel scoreLabel;
    /** score del juego */
    private int score = 0;

    // letter size
    private final static int letterX = 10;
    private final static int letterY = 12;
    private final static int letterW = 14;
    private final static int letterH = 20;
    private final static int lXoffset = 18;

    // Labels de seleccion de letras
    private JLabel letter1;
    private JLabel letter2;
    private JLabel letter3;

    private int multiplier;

    // offset para este panel
    private final static int scorePanelW = 70;
    private final static int scorePanelH = 50;
    // offset para el scoreLabel
    private final static int stxtX = 0;
    private final static int stxtY = 0;
    private final static int stxtW = 30;
    private final static int stxtH = 10;
    // offset para los paneles de score
    private final static int scX = 40;
    private final static int scY = 2;
    private final static int scW = 6;
    private final static int scH = 6;
    private final static int scXpad = 6;

    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/sidePanels/images/";

    public HighScore(int multiplier, int score) {
        this.setLayout(null);
        this.multiplier = multiplier;
        this.setBounds(0, 0, scorePanelW * multiplier, scorePanelH * multiplier);
        this.score = score;
        System.out.println(" Iniciando con score= " + this.score + " score 2= " + score);
        initPanels();
        this.setOpaque(false);
        revalidate();
        repaint();
        this.setVisible(true);
    }

    private void initPanels() {
        scoreLabel = new ScoreLabel(stxtX, stxtY, stxtW, stxtH, scX, scY, scW, scH, scXpad, score, multiplier,
                0, 0, 70, 50, true);
        this.add(scoreLabel);
        letter1 = new JLabel();
        initLabel(letter1, letterX, letterY, letterW, letterH, "a");
        this.add(letter1);
        letter2 = new JLabel();
        initLabel(letter2, letterX + lXoffset, letterY, letterW, letterH, "a");
        this.add(letter2);
        letter3 = new JLabel();
        initLabel(letter3, letterX + lXoffset * 2, letterY, letterW, letterH, "a");
        this.add(letter3);
    }

    /** Inicializa un label asignandole la imagen correspondiente */
    private void initLabel(JLabel label, int x, int y, int w, int h, String imagePath) {
        x = x * multiplier;
        y = y * multiplier;
        w = w * multiplier;
        h = h * multiplier;
        label.setBounds(x, y, w, h);
        if (imagePath.equals("a")) {
            label.setText("A");
            for (int i = 1;; i++) {
                label.setFont(new java.awt.Font("Impact", 1, i));
                int fontW = label.getFontMetrics(label.getFont()).stringWidth(label.getText());
                int fontH = label.getFontMetrics(label.getFont()).getHeight();
                if (fontW > w || fontH > h) {
                    label.setFont(new java.awt.Font("Impact", 1, i - 1));
                    break;
                }

            }
            label.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            getIcon(label, folderPath + imagePath, w, h);
        }
    }

    /** Obtiene un icono y lo escala para colocarlo */
    private void getIcon(JLabel label, String imagePath, int width, int height) {
        ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
        Image img = image.getImage();
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
    }

    public String getLetter() {
        return letter1.getText() + letter2.getText() + letter3.getText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintLetter(g, letter1);
        paintLetter(g, letter2);
        paintLetter(g, letter3);
    }

    private void paintLetter(Graphics g, JLabel label) {
        g.setColor(new Color(255, 255, 255, 200));
        int x = label.getBounds().x;
        int y = label.getBounds().y;
        int width = label.getBounds().width;
        int height = label.getBounds().height;
        g.fillRect(x, y, width, height);
    }
}