package com.leviatanes.tetris;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.InputStream;

import com.leviatanes.tetris.tetrisGame.TetrisPanel;

public class Main extends javax.swing.JFrame {
    private TetrisPanel tetrisPanel;

    // Constants screen res
    final int BASE_WIDTH = 90;
    private final int BASE_HEIGHT = 80;
    private final int MATRIX_ROWS = 28;
    private final int MATRIX_COLUMNS = 2;

    // Variables
    private int width = 0;
    private int height = 0;
    private int multiplier = 1;

    // Get screen resolution
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenHeight = (int) screenSize.getHeight();

    // Create matrix with 28 different resolutions
    private int[][] resolution;

    /**
     * Creates new form Test
     */
    public Main() {
        // initComponents();
        initFont();
        this.initGame();
    }

    public Main(boolean b) {
        this.setUndecorated(true);
        this.setResizable(false);
        width = 0;
        height = 0;
        tetrisPanel = null;
        this.initGame();
        this.setVisible(true);
    }

    private void getMaxResolution() {
        resolution = new int[MATRIX_ROWS][MATRIX_COLUMNS];
        resolution[0][0] = BASE_WIDTH;
        resolution[0][1] = BASE_HEIGHT;
        for (multiplier = 1; multiplier < MATRIX_ROWS && resolution[multiplier - 1][1] < screenHeight; multiplier++) {
            resolution[multiplier][0] = resolution[0][0] * (multiplier + 1);
            resolution[multiplier][1] = resolution[0][1] * (multiplier + 1);
            width = resolution[multiplier - 1][0];
            height = resolution[multiplier - 1][1];
        }
        multiplier--;
        System.out.println("width: " + width + " height: " + height + " multiplier: " + multiplier);
    }

    private void generateCenter() {
        int x = (int) (screenSize.getWidth() - width) / 2;
        int y = (int) (screenSize.getHeight() - height) / 2;
        this.setLocation(x, y);
    }

    private void initFont() {
        String fontName = "Impact";
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = ge.getAllFonts();
        boolean foundFont = false;
        for (Font font : fonts) {
            if (font.getName().equals(fontName)) {
                foundFont = true;
                break;
            }
        }
        if (foundFont) {
            System.out.println("La fuente " + fontName + " está instalada.");
        } else {
            System.out.println("La fuente " + fontName + " no está instalada en el sistema.");
            try (InputStream inputStream = Main.class.getResourceAsStream("/com/leviatanes/tetris/Impact.ttf")) {
                Font impactFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(impactFont);
                System.out.println("La fuente " + fontName + " se ha instalado correctamente en el sistema.");
            } catch (Exception ex) {
                System.err.println("No se pudo instalar la fuente " + fontName + ".");
                ex.printStackTrace();
            }
        }
    }

    private void initGame() {
        // obtener resolucion maxima
        this.getMaxResolution();
        // generar centro de pantalla para el panel
        this.generateCenter();
        // inicializar el juego
        this.setResizable(false);
        this.setSize(width, height);
        this.tetrisPanel = new TetrisPanel(width, height, multiplier);
        this.add(tetrisPanel);
        this.setUndecorated(true);

        this.addKeyListener(tetrisPanel.getGameControls());
    }

    public static void main(String args[]) {
        new Main().setVisible(true);
    }
}