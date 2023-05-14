package com.leviatanes.tetris;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.InputStream;

import com.leviatanes.tetris.tetrisGame.TetrisPanel;
import com.leviatanes.menus.*;

/**
 * Main class
 * ventana principal del juego
 * 
 * @author Leonardo
 * @author Eriarer (Abraham)
 * @author Gerardo
 * @author Mariana
 * 
 * @see TetrisPanel
 * @see Minicio
 */
public class Main extends javax.swing.JFrame {
    private TetrisPanel tetrisPanel;
    private Minicio menuIni;
    private Mscores menuScores;

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

        this.setLayout(null);
        // obtener resolucion maxima
        this.getMaxResolution();

        // generar centro de pantalla para el panel
        this.generateCenter();
        this.setSize(width, height);

        this.setUndecorated(true);
        this.setResizable(false);

        this.MenuInicio();

        this.revalidate();
        this.repaint();

    }

    public void getMaxResolution() {
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

    /** Inicializa el juego y eliminqa el menu de inicio */
    public void initGame() {
        if (menuIni != null) {
            this.remove(menuIni);
            menuIni = null;
        }
        if (menuScores != null) {
            this.remove(menuScores);
            menuScores = null;
        }
        // inicializar el juego
        this.tetrisPanel = new TetrisPanel(width, height, multiplier, this);
        this.add(tetrisPanel);
        this.addKeyListener(tetrisPanel.getGameControls());
        this.revalidate();
        this.repaint();
        SoundsPlayer.stopMusic();
    }

    public void MenuInicio() {
        // Se inicializa el menu de inicio
        Thread loadMenu = new Thread(new Runnable() {
            @Override
            public void run() {
                menuIni = new Minicio(width, height, multiplier, Main.this);
                Main.this.add(menuIni);
                Main.this.revalidate();
                Main.this.repaint();
                if(!SoundsPlayer.isMenuMusicPlaying()){
                    SoundsPlayer.stopMusic();
                    SoundsPlayer.playMenuMusic();
                }
            }
        });
        loadMenu.start();
        if (tetrisPanel != null) {
            this.removeKeyListener(tetrisPanel.getGameControls());
            this.remove(tetrisPanel);
            tetrisPanel = null;
        }
        if (menuScores != null) {
            this.remove(menuScores);
            menuScores = null;
        }
        if(loadMenu.isAlive()){
            try {
                loadMenu.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void menuScore() {
        if (tetrisPanel != null) {
            this.removeKeyListener(tetrisPanel.getGameControls());
            this.remove(tetrisPanel);
            tetrisPanel = null;
        }
        ;
        if (menuIni != null) {
            this.remove(menuIni);
            menuIni = null;
        }
        menuScores = new Mscores(width, height, multiplier, this);
        this.add(menuScores);
        this.revalidate();
        this.repaint();
    }

    // Setters de higth, whidth y multiplier (variables que definen el tamaño de la
    // ventana)
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    // Getters de higth, whidth y multiplier
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static void main(String args[]) {
        new Main().setVisible(true);
        System.out.println("Main fin");
    }
}