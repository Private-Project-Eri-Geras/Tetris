package com.leviatanes.tetris.tetrisGame.game.gameOver.scorePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leviatanes.tetris.tetrisGame.game.gameOver.Score;

public class HighScore extends JPanel {
    /** Label de score */
    private JLabel scoreTextLbl;
    private JLabel scoreLbl1;
    private JLabel scoreLbl2;
    private JLabel scoreLbl3;
    private JLabel scoreLbl4;
    private JLabel scoreLbl5;
    /** score del juego */
    private int score = 0;

    private int multiplier;

    // offset para este panel
    private final static int scorePanelW = 70;
    private final static int scorePanelH = 50;
    // offset para el scoreLaber
    private final static int scoreTextX = 0;
    private final static int scoreTextY = 10;
    private final static int scoreTextW = 30;
    private final static int scoreTextH = 10;
    // offset para los paneles de score
    private final static int scoreY = 12;
    private final static int scoreX = 40;
    private final static int scoreW = 6;
    private final static int scoreH = 6;

    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/sidePanels/images/";

    public HighScore(int multiplier, int score2) {
        this.setLayout(null);
        this.multiplier = multiplier;
        this.setBounds(0, 0, scorePanelW * multiplier, scorePanelH * multiplier);
        System.out.println("Bounds " + this.getBounds());
        this.score = score2;
        initPanels();
        revalidate();
        repaint();
        this.setVisible(true);
        this.setOpaque(false);
    }

    private void initPanels() {
        scoreTextLbl = new JLabel();
        this.add(scoreTextLbl);
        this.initLabel(scoreTextLbl, scoreTextX, scoreTextY, scoreTextW, scoreTextH, "score.png");
        score = score * 10;
        String pointsStr = Integer.toString(score);
        if (pointsStr.length() < 6) {
            int zeros = 6 - pointsStr.length();
            for (int i = 0; i < zeros; i++) {
                pointsStr = "0" + pointsStr;
            }
        }
        String scoreStr;
        JLabel scoreLbl = null;
        int realscoreX = scoreX;
        for (int i = 1; i < 6; i++) {
            scoreStr = "scoreLbl" + i;
            // obtener el nombre de la variable segun scoreStr
            try {
                scoreLbl = new JLabel();
                initLabel(scoreLbl, realscoreX, scoreY, scoreW, scoreH, pointsStr.charAt(i - 1) + ".png");
                scoreLbl.setBackground(Color.WHITE);
                // conseguir el scoreLbl con el nombre de scoreStr
                java.lang.reflect.Field field = this.getClass().getDeclaredField(scoreStr);
                field.setAccessible(true);
                field.set(this, scoreLbl); // se le asigna el objeto JLabel a la variable
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
            realscoreX += 6;
        }
        this.add(scoreLbl1);
        this.add(scoreLbl2);
        this.add(scoreLbl3);
        this.add(scoreLbl4);
        this.add(scoreLbl5);
    }

    /** Inicializa un label asignandole la imagen correspondiente */
    private void initLabel(JLabel label, int x, int y, int width, int height, String imagePath) {
        x = x * multiplier;
        y = y * multiplier;
        width = width * multiplier;
        height = height * multiplier;
        label.setBounds(x, y, width, height);
        System.out.println("Bounds " + x + " " + y + " " + width + " " + height);
        getIcon(label, folderPath + imagePath, width, height);
    }

    /** Obtiene un icono y lo escala para colocarlo */
    private void getIcon(JLabel label, String imagePath, int width, int height) {
        ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
        Image img = image.getImage();
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 255, 255, 200));
        int x = scoreX * multiplier;
        int y = (scoreY - 2) * multiplier;
        int width = scoreW * 5 * multiplier;
        int height = (scoreH + 4) * multiplier;
        g.fillRect(x, y, width, height);
    }
}
