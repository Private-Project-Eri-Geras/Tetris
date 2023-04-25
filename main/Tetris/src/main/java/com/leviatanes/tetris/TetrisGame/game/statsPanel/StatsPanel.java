package com.leviatanes.tetris.tetrisGame.game.statsPanel;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.leviatanes.tetris.tetrisGame.game.GameArea;

public class StatsPanel extends javax.swing.JPanel {
    // private GameArea gameArea;
    /** Label de score */
    private JLabel scoreTextLbl;
    private JLabel scoreLbl1;
    private JLabel scoreLbl2;
    private JLabel scoreLbl3;
    private JLabel scoreLbl4;
    private JLabel scoreLbl5;
    /** score del juego */
    private int score = 0;

    /** Label del nivel */
    private JLabel levelTextLbl;
    private JLabel levelLbl1;
    private JLabel levelLbl2;

    /** nivel del juego */
    private int level = 0;

    /** Label de lineas */
    private JLabel linesTextLbl;
    private JLabel linesLbl1;
    private JLabel linesLbl2;
    private JLabel linesLbl3;

    /** lineas del juego */
    private int lines = 0;

    private int multiplier;

    // offset para el scoreLaber
    private final static int scoreTxtLblX = 3;
    private final static int scoreTxtLblY = 5;
    private final static int scoreTxtLblW = 18;
    private final static int scoreTxtLblH = 6;
    // offset para los paneles de score
    private final static int scoreY = 13;
    private final static int scoreW = 4;
    private final static int scoreH = 4;

    // offset para el levelLabel
    private final static int levelTxtLblX = 3;
    private final static int levelTxtLblY = 22;
    // offset para los paneles de level
    private final static int levelY = 30;
    private final static int levelW = 4;
    private final static int levelH = 4;
    //
    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/statsPanel/images/";

    public StatsPanel(int multiplier) {
        initComponents();
        this.multiplier = multiplier;
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        initPanels();
        repaint();
        this.setVisible(true);

    }

    // =========[GETTERS]==========
    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    // =========[SETTERS]==========
    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setGameArea(GameArea gameArea) {
        // this.gameArea = gameArea;
    }

    private void initPanels() {
        // inicializar los labels de score
        initScores();
        // inicializar los labels de nivel
        initLevel();

    }

    /** Iniciale los labels que demuestran el score */
    private void initScores() {
        // etiqueta con el label de score
        this.scoreTextLbl = new JLabel();
        initLabel(scoreTextLbl, scoreTxtLblX, scoreTxtLblY, scoreTxtLblW, scoreTxtLblH, "Score.png");
        // etiquetas con los numeros del score
        String scoreStr;
        JLabel scoreLbl = null;
        int scoreLblrealX = 20;
        for (int i = 1; i < 6; i++) {
            scoreStr = "scoreLbl" + i;
            // obtener el nombre de la variable segun scoreStr
            try {
                scoreLbl = new JLabel();
                initLabel(scoreLbl, scoreLblrealX, scoreY, scoreW, scoreH, "0.png");
                // conseguir el scoreLbl con el nombre de scoreStr
                java.lang.reflect.Field field = this.getClass().getDeclaredField(scoreStr);
                field.setAccessible(true);
                field.set(this, scoreLbl); // se le asigna el objeto JLabel a la variable
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
            scoreLblrealX -= 5;
        }
        this.add(scoreTextLbl);
        this.add(scoreLbl1);
        this.add(scoreLbl2);
        this.add(scoreLbl3);
        this.add(scoreLbl4);
        this.add(scoreLbl5);
    }

    /** Inicia los labels que demuestran el nivle */
    private void initLevel() {
        // etiqueta con el label de nivel
        this.levelTextLbl = new JLabel();
        initLabel(levelTextLbl, levelTxtLblX, levelTxtLblY, scoreTxtLblW, scoreTxtLblH, "Level.png");
        // etiquetas con los numeros del nivel
        int levelLblrealX = 12;
        this.levelLbl1 = new JLabel();
        initLabel(levelLbl1, levelLblrealX, levelY, levelW, levelH, "0.png");
        levelLblrealX -= 5;
        this.levelLbl2 = new JLabel();
        initLabel(levelLbl2, levelLblrealX, levelY, levelW, levelH, "0.png");

        this.add(levelTextLbl);
        this.add(levelLbl1);
        this.add(levelLbl2);

    }

    /** Inicializa un label asignandole la imagen correspondiente */
    private void initLabel(JLabel label, int x, int y, int width, int height, String imagePath) {
        x = x * multiplier;
        y = y * multiplier;
        width = width * multiplier;
        height = height * multiplier;
        label.setBounds(x, y, width, height);
        getIcon(label, folderPath + imagePath, width, height);
    }

    private void getIcon(JLabel label, String imagePath, int width, int height) {
        ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
        Image img = image.getImage();
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 192, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 448, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
