package com.leviatanes.tetris.tetrisGame.game.gameOver.scorePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

    // letter size
    private final static int letterX = 10;
    private final static int letterY = 30;
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
        this.score = score2;
        initPanels();
        this.setOpaque(false);
        revalidate();
        repaint();
        this.setVisible(true);
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
        g.setColor(new Color(255, 255, 255, 200));
        int x = scoreX * multiplier;
        int y = (scoreY - 2) * multiplier;
        int width = scoreW * 5 * multiplier;
        int height = (scoreH + 4) * multiplier;
        g.fillRect(x, y, width, height);
        paintLetter(g, letter1);
        paintLetter(g, letter2);
        paintLetter(g, letter3);
    }

    private void paintLetter(Graphics g, JLabel label) {
        int x = label.getBounds().x;
        int y = label.getBounds().y;
        int width = label.getBounds().width;
        int height = label.getBounds().height;
        g.fillRect(x, y, width, height);
    }
}