package com.leviatanes.tetris.tetrisGame.game.sidePanels;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.scorePanels.ScoreLabel;

public class StatsPanel extends javax.swing.JPanel {
    /** Label de score */
    private ScoreLabel scoreLabel;
    // offset para el scoreLaber
    private final static int stxtX = 3;
    private final static int stxtY = 0;
    private final static int stxtW = 18;
    private final static int stxtH = 6;
    // offset para los paneles de score
    private final static int scX = 0;
    private final static int scY = 8;
    private final static int scW = 4;
    private final static int scH = 4;
    private final static int scXpad = 5;

    // offset del panel de score
    private final static int scPX = 0;
    private final static int scPY = 5;
    private final static int scPW = 24;
    private final static int scPH = 12;

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

    // offset para el levelLabel
    private final static int levelTxtLblX = 3;
    private final static int levelTxtLblY = 22;
    // offset para los paneles de level
    private final static int levelY = 30;
    private final static int levelW = 4;
    private final static int levelH = 4;

    // offset para el linesLabel
    private final static int linesTxtLblX = 3;
    private final static int linesTxtLblY = 39;
    // offset para los paneles de lines
    private final static int linesY = 47;
    private final static int linesW = 4;
    private final static int linesH = 4;

    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/sidePanels/images/";

    public StatsPanel(int multiplier) {
        this.setLayout(null);
        this.multiplier = multiplier;
        initPanels();
        revalidate();
        repaint();
        this.setOpaque(true);
        this.setVisible(true);
    }

    // =========[GETTERS]==========
    public int getScore() {
        return scoreLabel.getScore();
    }

    public int getLevel() {
        return level;
    }

    public int getLines() {
        return lines;
    }

    // =========[SETTERS]==========
    public void setScore(int score) {
        scoreLabel.setScore(score);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    /** Actualiza los labels de score */
    public void updateScore(int score) {
        scoreLabel.updateScore(score);
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
        scoreLabel = new ScoreLabel(stxtX, stxtY, stxtW, stxtH, scX, scY, scW, scH, scXpad, 0, multiplier, scPX, scPY,
                scPW, scPH, false);
        add(scoreLabel);
        // // etiqueta con el label de score
        // this.scoreTextLbl = new JLabel();
        // initLabel(scoreTextLbl, stxtX, stxtY, stxtW, stxtH, "Score.png");
        // // etiquetas con los numeros del score
        // String scoreStr;
        // JLabel scoreLbl = null;
        // int scoreLblrealX = 20;
        // for (int i = 1; i < 6; i++) {
        // scoreStr = "scoreLbl" + i;
        // // obtener el nombre de la variable segun scoreStr
        // try {
        // scoreLbl = new JLabel();
        // initLabel(scoreLbl, scoreLblrealX, scY, scW, scH, "0.png");
        // // conseguir el scoreLbl con el nombre de scoreStr
        // java.lang.reflect.Field field = this.getClass().getDeclaredField(scoreStr);
        // field.setAccessible(true);
        // field.set(this, scoreLbl); // se le asigna el objeto JLabel a la variable
        // } catch (IllegalArgumentException | IllegalAccessException |
        // NoSuchFieldException | SecurityException e) {
        // e.printStackTrace();
        // }
        // scoreLblrealX -= 5;
        // }
        // this.add(scoreTextLbl);
        // this.add(scoreLbl1);
        // this.add(scoreLbl2);
        // this.add(scoreLbl3);
        // this.add(scoreLbl4);
        // this.add(scoreLbl5);
    }

    /** Inicia los labels que demuestran el nivle */
    private void initLevel() {
        // etiqueta con el label de nivel
        this.levelTextLbl = new JLabel();
        initLabel(levelTextLbl, levelTxtLblX, levelTxtLblY, stxtW, stxtH, "Level.png");
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
        initLabel(linesTextLbl, linesTxtLblX, linesTxtLblY, stxtW, stxtH, "Lines.png");
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
}
