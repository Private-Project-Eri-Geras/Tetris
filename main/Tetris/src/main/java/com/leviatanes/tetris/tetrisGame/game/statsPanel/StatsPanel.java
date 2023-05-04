package com.leviatanes.tetris.tetrisGame.game.statsPanel;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StatsPanel extends javax.swing.JPanel {
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
    private int level = 1;

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
    // offset para el panel de figura guardada
    private final int stashShapeYoffset = 44;
    private final int stashShapeXoffset = 65;
    private final int stashShapeWidth = 20;
    private final int stashShapeHeight = 20;

    // offset para el linesLabel
    private final static int linesTxtLblX = 3;
    private final static int linesTxtLblY = 39;
    // offset para los paneles de lines
    private final static int linesY = 47;
    private final static int linesW = 4;
    private final static int linesH = 4;

    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/statsPanel/images/";

    public StatsPanel(int multiplier) {
        initComponents();
        this.setBounds(stashShapeXoffset * multiplier, stashShapeYoffset * multiplier,
                stashShapeWidth * multiplier, stashShapeHeight * multiplier);
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

    public int getLines() {
        return lines;
    }

    // =========[SETTERS]==========
    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    /** Actualiza los labels de score */
    public void updateScore(int score) {
        this.score += score;
        updateScoreLbl();
    }

    /** Actualiza el label del score */
    private void updateScoreLbl() {
        String scoreStr = String.valueOf(score);
        int socreLength = scoreStr.length();
        switch (socreLength) {
            case 1:
                updateLbl1(scoreStr);
                break;
            case 2:
                updateLbl2(scoreStr);
                break;
            case 3:
                updateLbl3(scoreStr);
                break;
            case 4:
                updateLbl4(scoreStr);
                break;
            default:
                updateLbl5(scoreStr);
                break;
        }
    }

    /** Actualiza el label 1 */
    private void updateLbl1(String scoreValue) {
        String imageName;
        imageName = scoreValue.charAt(0) + ".png";

        System.out.println(scoreValue);
        setIcon(scoreLbl1, folderPath + imageName, scoreW, scoreH);
    }

    /** Actualiza el label 2 y los anteriores */
    private void updateLbl2(String scoreValue) {
        String imageName;
        imageName = scoreValue.charAt(0) + ".png";
        setIcon(scoreLbl2, folderPath + imageName, scoreW, scoreH);

        System.out.println(scoreValue);
        updateLbl1(scoreValue.charAt(1) + "");
    }

    /** Actualiza el label 3 y los anteriores */
    private void updateLbl3(String scoreValue) {
        String imageName;
        imageName = scoreValue.charAt(0) + ".png";
        setIcon(scoreLbl3, folderPath + imageName, scoreW, scoreH);

        System.out.println(scoreValue);
        updateLbl2(scoreValue.substring(1, scoreValue.length()));
    }

    /** Actualiza el label 4 y los anterires */
    private void updateLbl4(String scoreValue) {
        String imageName;
        imageName = scoreValue.charAt(0) + ".png";
        setIcon(scoreLbl4, folderPath + imageName, scoreW, scoreH);

        System.out.println(scoreValue);
        updateLbl3(scoreValue.substring(1, scoreValue.length()));
    }

    /** Actualiza el valor del label 5 y los anteriores */
    private void updateLbl5(String scoreValue) {
        String imageName;
        imageName = scoreValue.charAt(0) + ".png";
        setIcon(scoreLbl5, folderPath + imageName, scoreW, scoreH);

        System.out.println(scoreValue);
        updateLbl4(scoreValue.substring(1, scoreValue.length()));
    }

    /** Actualiza los labels de nivel */
    public void updateLevel(int level) {
        this.level = level;
        updateLevelLbl();
    }

    /** Actualiza los labels de score */
    private void updateLevelLbl() {
        String levelStr = String.valueOf(level);
        int levelLength = levelStr.length();
        switch (levelLength) {
            case 1:
                updateLevelLbl1(levelStr);
                break;
            default:
                updateLevelLbl2(levelStr);
                break;
        }
    }

    /** Actualiza el label 1 */
    private void updateLevelLbl1(String levelValue) {
        String imageName;
        imageName = levelValue.charAt(0) + ".png";
        setIcon(levelLbl1, folderPath + imageName, levelW, levelH);
    }

    /** Actualiza el label 2 y los anteriores */
    private void updateLevelLbl2(String levelValue) {
        String imageName;
        imageName = levelValue.charAt(1) + ".png";
        setIcon(levelLbl2, folderPath + imageName, levelW, levelH);
        updateLevelLbl1(levelValue.charAt(1) + "");
    }

    /** Actualiza los labels de lineas */
    public void updateLines(int lines) {
        this.lines += lines;
        updateLinesLbl();
    }

    /** Actualiza los labels de lineas */
    private void updateLinesLbl() {
        String linesStr = String.valueOf(lines);
        System.out.println(linesStr);
        int linesLength = linesStr.length();
        switch (linesLength) {
            case 1:
                updateLinesLbl1(linesStr);
                break;
            case 2:
                updateLinesLbl2(linesStr);
                break;
            default:
                updateLinesLbl3(linesStr);
                break;
        }
    }

    /** Actualiza el label 1 */
    private void updateLinesLbl1(String linesValue) {
        String imageName;
        imageName = linesValue.charAt(0) + ".png";
        setIcon(linesLbl1, folderPath + imageName, linesW, linesW);
    }

    /** Actualiza el label 2 y los anteriores */
    private void updateLinesLbl2(String linesValue) {
        String imageName;
        imageName = linesValue.charAt(0) + ".png";
        setIcon(linesLbl2, folderPath + imageName, linesW, linesW);
        updateLinesLbl1(linesValue.charAt(1) + "");
    }

    /** Actualiza el label 3 y los anteriores */
    private void updateLinesLbl3(String linesValue) {
        String imageName;
        imageName = linesValue.charAt(0) + ".png";
        setIcon(linesLbl3, folderPath + imageName, linesW, linesW);
        updateLinesLbl2(linesValue.substring(1, linesValue.length()));
    }

    private void initPanels() {
        // inicializar los labels de score
        initScores();
        // inicializar los labels de nivel
        initLevel();
        // inicializar los labels de lineas
        initLines();

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
        initLabel(levelLbl1, levelLblrealX, levelY, levelW, levelH, "1.png");
        levelLblrealX -= 5;
        this.levelLbl2 = new JLabel();
        initLabel(levelLbl2, levelLblrealX, levelY, levelW, levelH, "0.png");

        this.add(levelTextLbl);
        this.add(levelLbl1);
        this.add(levelLbl2);

    }

    /** Inicia lo labels que demuestran las lineas */
    private void initLines() {
        // etiqueta con el label de lineas
        this.linesTextLbl = new JLabel();
        initLabel(linesTextLbl, linesTxtLblX, linesTxtLblY, scoreTxtLblW, scoreTxtLblH, "Lines.png");
        // etiquetas con los numeros de lineas
        int linesLblrealX = 15;
        this.linesLbl1 = new JLabel();
        initLabel(linesLbl1, linesLblrealX, linesY, linesW, linesH, "0.png");
        linesLblrealX -= 5;
        this.linesLbl2 = new JLabel();
        initLabel(linesLbl2, linesLblrealX, linesY, linesW, linesH, "0.png");
        linesLblrealX -= 5;
        this.linesLbl3 = new JLabel();
        initLabel(linesLbl3, linesLblrealX, linesY, linesW, linesH, "0.png");

        this.add(linesTextLbl);
        this.add(linesLbl1);
        this.add(linesLbl2);
        this.add(linesLbl3);
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

    /** pone un icono a un label ya existente */
    private void setIcon(JLabel label, String imagePath, int width, int height) {
        width = width * multiplier;
        height = height * multiplier;
        getIcon(label, imagePath, width, height);
    }

    /** Obtiene un icono y lo escala para colocarlo */
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
