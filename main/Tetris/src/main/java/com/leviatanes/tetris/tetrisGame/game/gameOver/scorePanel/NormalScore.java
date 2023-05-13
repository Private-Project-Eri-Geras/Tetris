package com.leviatanes.tetris.tetrisGame.game.gameOver.scorePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.leviatanes.tetris.Main;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.scorePanels.ScoreLabel;

/**
 * [ SCORE ]
 * Se encarga de mostrar un menú final el cual
 * muestra el puntaje y lineas total que el
 * usuario consiguió a lo largo de la ejecución
 * 
 * @author Leonardo
 * @author Eriarer (Abraham)
 * 
 * @see GameOver
 * @see ScoreLabel
 */
public class NormalScore extends JPanel {
    /** Label de score */
    private ScoreLabel scoreLabel;
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
    /** score del juego */
    private int score = 0;

    /** nivel del juego */
    private int lines = 0;

    /** Label de lineas */
    private JLabel linesTextLbl;
    private JLabel linesLbl1;
    private JLabel linesLbl2;
    private JLabel linesLbl3;
    private int multiplier;

    // offset para este panel
    private final static int scorePanelW = 70;
    private final static int scorePanelH = 50;

    // offset para el linesClearedLabel
    private final static int linesTextX = 0;
    private final static int linesTextY = 26;
    private final static int linesTextW = 30;
    private final static int linesTextH = 10;
    // offset para los paneles de linesCleared
    private final static int linesClearedX = 43;
    private final static int linesClearedY = 28;
    private final static int linesClearedW = 6;
    private final static int linesClearedH = 6;
    // boton de EXIT
    private final static int exitX = 15;
    private final static int exitY = 42;
    private final static int exitW = 15;
    private final static int exitH = 8;
    // boton de regresar al menu
    private final static int menuX = 40;
    private final static int menuY = 42;
    private final static int menuW = 15;
    private final static int menuH = 8;

    /** boton de salida */
    private JLabel exit;
    private Color exitColor;
    /** boton de regresar al menu */
    private JLabel menu;
    private Color menuColor;
    /** ruta de las imagenes */
    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/sidePanels/images/";

    private Color baseColor = new Color(255, 255, 255, 200);
    private Color hoverColor = new Color(255, 255, 255, 255);

    public NormalScore(int multiplier, int score2, int lines2) {
        this.setLayout(null);
        this.multiplier = multiplier;
        this.setBounds(0, 0, scorePanelW * multiplier, scorePanelH * multiplier);
        this.score = score2;
        this.lines = lines2;
        initPanels();
    }

    /** constructor extraido para mas "legibilidad" */
    private void initPanels() {
        initScrose();
        initLinesCleared();
        initButtons();
        setOpaque(false);
        revalidate();
        repaint();
        setVisible(true);
    }

    /** Inicializa el panel de score */
    private void initScrose() {
        scoreLabel = new ScoreLabel(stxtX, stxtY, stxtW, stxtH, scX, scY, scW, scH, scXpad, score, multiplier,
                0, 0, 70, 50, true);
        this.add(scoreLabel);
    }

    /** Inicializa el panel de lineas */
    private void initLinesCleared() {
        linesTextLbl = new JLabel();
        this.add(linesTextLbl);
        this.initLabel(linesTextLbl, linesTextX, linesTextY, linesTextW, linesTextH, "Lines.png");
        String linesStr = Integer.toString(lines);
        if (linesStr.length() < 3) {
            int zeros = 3 - linesStr.length();
            for (int i = 0; i < zeros; i++) {
                linesStr = "0" + linesStr;
            }
        }
        String linesClearedStr;
        JLabel linesClearedLbl = null;
        int reallinesClearedX = linesClearedX;
        for (int i = 1; i < 4; i++) {
            linesClearedStr = "linesLbl" + i;
            // obtener el nombre de la variable segun linesClearedStr
            try {
                linesClearedLbl = new JLabel();
                initLabel(linesClearedLbl, reallinesClearedX, linesClearedY, linesClearedW, linesClearedH,
                        linesStr.charAt(i - 1) + ".png");
                // conseguir el linesClearedLbl con el nombre de linesClearedStr
                java.lang.reflect.Field field = this.getClass().getDeclaredField(linesClearedStr);
                field.setAccessible(true);
                field.set(this, linesClearedLbl); // se le asigna el objeto JLabel a la variable
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
            reallinesClearedX += 9;
        }
        this.add(linesLbl1);
        this.add(linesLbl2);
        this.add(linesLbl3);
    }

    /** Inicializa los "botones" */
    private void initButtons() {
        exit = new JLabel();
        this.add(exit);
        this.initLabel(exit, exitX, exitY, exitW, exitH, "EXIT");
        exitColor = baseColor;
        // registrar el click del mouse
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        // iluminar el boton cuando el mouse esta encima
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitColor = hoverColor;
                repaint();
            }
        });
        // volver a la normalidad cuando el mouse sale
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                exitColor = baseColor;
                repaint();
            }
        });
        menu = new JLabel();
        this.add(menu);
        this.initLabel(menu, menuX, menuY, menuW, menuH, "MENU");
        menuColor = baseColor;
        // registrar el click del mouse
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ========================== MENU ===========================//
                System.exit(0);
            }
        });
        // iluminar el boton cuando el mouse esta encima
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuColor = hoverColor;
                repaint();
            }
        });
        // volver a la normalidad cuando el mouse sale
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menuColor = baseColor;
                repaint();
            }
        });
    }

    /** Inicializa un label asignandole la imagen correspondiente */
    private void initLabel(JLabel label, int x, int y, int w, int h, String imagePath) {
        x = x * multiplier;
        y = y * multiplier;
        w = w * multiplier;
        h = h * multiplier;
        label.setBounds(x, y, w, h);
        // si la imagen es un texto se le asigna el texto
        if (imagePath.contains(".png")) {
            getIcon(label, folderPath + imagePath, w, h);

        } else {
            label.setText(imagePath);
            // se le asigna la fuente mas grande posible para que quepa en el label
            for (int i = 1;; i++) {
                label.setFont(new java.awt.Font("Impact", 0, i));
                int fontW = label.getFontMetrics(label.getFont()).stringWidth(label.getText());
                int fontH = label.getFontMetrics(label.getFont()).getHeight();
                if (fontW > w || fontH > h) {
                    label.setFont(new java.awt.Font("Impact", 0, i - 1));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                }

            } // fin for
        }
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
        int x = 40 * multiplier;
        int y = 26 * multiplier;
        int width = 30 * multiplier;
        int height = 10 * multiplier;
        g.fillRect(x, y, width, height);

        x = exitX * multiplier;
        y = exitY * multiplier;
        width = exitW * multiplier;
        height = exitH * multiplier;
        g.setColor(exitColor);
        g.fillRect(x, y, width, height);

        x = menuX * multiplier;
        y = menuY * multiplier;
        width = menuW * multiplier;
        height = menuH * multiplier;
        g.setColor(menuColor);
        g.fillRect(x, y, width, height);
    }
}
