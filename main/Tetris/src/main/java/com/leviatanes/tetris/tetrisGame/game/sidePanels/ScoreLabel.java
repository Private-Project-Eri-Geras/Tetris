package com.leviatanes.tetris.tetrisGame.game.sidePanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScoreLabel extends JPanel {

    // label SCORE
    private JLabel scoreTextLbl;
    // coordenadas
    private int textX;
    private int textY;
    private int textW;
    private int textH;
    // contenedores de score
    private JLabel scoreLbl1;
    private JLabel scoreLbl2;
    private JLabel scoreLbl3;
    private JLabel scoreLbl4;
    private JLabel scoreLbl5;
    // coordenadas
    private int scoreX;
    private int scoreY;
    private int scoreW;
    private int scoreH;
    private int scoreXoffset;

    // coordenadas para este panel
    private int x;
    private int y;
    private int w;
    private int h;
    private int multiplier;

    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/sidePanels/images/";

    private int score;
    boolean drawBG;

    /**
     * Constructor
     * 
     * @param textX        coordenada x del label de texto
     * @param textY        coordenada y del label de texto
     * @param textW        ancho del label de texto
     * @param textH        alto del label de texto
     * @param scoreX       coordenada x del label de score
     * @param scoreY       coordenada y del label de score
     * @param scoreW       ancho del label de score
     * @param scoreH       alto del label de score
     * @param scoreXoffset offset del label de score
     * @param score        score del juego
     * @param multiplier   multiplicador de tamaño
     * @param x            coordenada x del panel
     * @param y            coordenada y del panel
     * @param w            ancho del panel
     * @param h            alto del panel
     * @param drawBG       bandera para dibujar el fondo
     */
    public ScoreLabel(int textX, int textY, int textW, int textH, int scoreX, int scoreY, int scoreW, int scoreH,
            int scoreXoffset, int score, int multiplier, int x, int y, int w, int h, boolean drawBG) {
        this.textX = textX;
        this.textY = textY;
        this.textW = textW;
        this.textH = textH;
        this.scoreX = scoreX;
        this.scoreY = scoreY;
        this.scoreW = scoreW;
        this.scoreH = scoreH;
        this.scoreXoffset = scoreXoffset;
        this.score = score;
        this.multiplier = multiplier;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.drawBG = drawBG;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.setOpaque(false);
        x = x * multiplier;
        y = y * multiplier;
        w = w * multiplier;
        h = h * multiplier;
        this.setBounds(x, y, w, h);
        initPanels();
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    private void initPanels() {
        scoreTextLbl = new JLabel();
        this.add(scoreTextLbl);
        this.initLabel(scoreTextLbl, textX, textY, textW, textH, "score.png");
        String pointsStr = Integer.toString(score);
        while (pointsStr.length() < 5) {
            pointsStr = "0" + pointsStr;
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
            realscoreX += scoreXoffset;
        }
        this.add(scoreLbl1);
        this.add(scoreLbl2);
        this.add(scoreLbl3);
        this.add(scoreLbl4);
        this.add(scoreLbl5);
    }

    public int getScore() {
        System.out.println("Score: " + this.score);
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /** Inicializa un label asignandole la imagen correspondiente */
    private void initLabel(JLabel label, int x, int y, int w, int h, String imagePath) {
        x = x * multiplier;
        y = y * multiplier;
        w = w * multiplier;
        h = h * multiplier;
        label.setBounds(x, y, w, h);
        if (!imagePath.contains(".png")) {
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

    /** pone un icono a un label ya existente */
    private void setIcon(JLabel label, String imagePath, int width, int height) {
        width = width * multiplier;
        height = height * multiplier;
        getIcon(label, imagePath, width, height);
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
        setIcon(scoreLbl5, folderPath + imageName, scoreW, scoreH);
    }

    /** Actualiza el label 2 y los anteriores */
    private void updateLbl2(String scoreValue) {
        String imageName;
        imageName = scoreValue.charAt(0) + ".png";
        setIcon(scoreLbl4, folderPath + imageName, scoreW, scoreH);

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
        setIcon(scoreLbl1, folderPath + imageName, scoreW, scoreH);

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawBG == true) {
            g.setColor(new Color(255, 255, 255, 200));
            int x = scoreX * multiplier;
            int y = (scoreY - 2) * multiplier;
            int width = scoreW * 5 * multiplier;
            int height = (scoreH + 4) * multiplier;
            g.fillRect(x, y, width, height);
        }
    }
}
