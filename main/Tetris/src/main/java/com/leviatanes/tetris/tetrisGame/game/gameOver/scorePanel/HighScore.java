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

import com.leviatanes.Main;
import com.leviatanes.tetris.tetrisGame.game.gameOver.GameOver;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.scorePanels.ScoreLabel;

/**
 * [ HIGH SCORE ]
 * Esta clase es un panel que se encarga de mostrar
 * una ventana interactiva para que el usuario pueda
 * ingresar su nombre y guardar su puntaje en el
 * archivo de puntajes.
 * 
 * @author Leonardo
 * @author Eriarer (Abraham)
 * 
 * @see GameOver
 * @see ScoreLabel
 */
public class HighScore extends JPanel {
    /** Label de score */
    private ScoreLabel scoreLabel;
    /** score del juego */
    private int score = 0;

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

    // Labels de seleccion de letras
    private JLabel letter1;
    private JLabel letter2;
    private JLabel letter3;

    // letter size
    private final static int letterX = 10;
    private final static int letterY = 12;
    private final static int letterW = 14;
    private final static int letterH = 20;
    private final static int lXoffset = 18;

    private int multiplier;

    // offset para este panel
    private final static int scorePanelW = 70;
    private final static int scorePanelH = 50;

    private final String folderPath = "/com/leviatanes/images/";

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

    private JLabel caption;

    // offset para el caption
    private final static int capX = 10;
    private final static int capY = 33;
    private final static int capW = 50;
    private final static int capH = 8;

    private JLabel exit;
    private JLabel menu;
    private Color baseColor = new Color(255, 255, 255, 100);
    private Color hoverColor = new Color(255, 255, 255, 255);
    private Main main;

    public HighScore(int multiplier, int score, Main main) {
        this.main = main;
        this.setLayout(null);
        this.multiplier = multiplier;
        this.setBounds(0, 0, scorePanelW * multiplier, scorePanelH * multiplier);
        this.score = score;
        initPanels();
        initButtons();
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
        letter1.setOpaque(true);
        letter1.setBackground(baseColor);
        initLabel(letter1, letterX, letterY, letterW, letterH, "A");
        // Listener para registrar clicks del mouse
        letter1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // Button1 = click izquierdo
                // Button3 = click derecho
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    mouseClick(letter1);
                } else if (evt.getButton() == MouseEvent.BUTTON3) {
                    mouseClickR(letter1);
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                letter1.setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                letter1.setBackground(baseColor);
                repaint();
            }
        });
        this.add(letter1);

        letter2 = new JLabel();
        letter2.setOpaque(true);
        letter2.setBackground(baseColor);
        initLabel(letter2, letterX + lXoffset, letterY, letterW, letterH, "A");
        // Listener para registrar clicks del mouse
        letter2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // Button1 = click izquierdo
                // Button3 = click derecho
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    mouseClick(letter2);
                } else if (evt.getButton() == MouseEvent.BUTTON3) {
                    mouseClickR(letter2);
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                letter2.setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                letter2.setBackground(baseColor);
                repaint();
            }
        });
        this.add(letter2);

        letter3 = new JLabel();
        letter3.setOpaque(true);
        letter3.setBackground(baseColor);
        initLabel(letter3, letterX + lXoffset * 2, letterY, letterW, letterH, "A");
        // Listener para registrar clicks del mouse
        letter3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // Button1 = click izquierdo
                // Button3 = click derecho
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    mouseClick(letter3);
                } else if (evt.getButton() == MouseEvent.BUTTON3) {
                    mouseClickR(letter3);
                }
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                letter3.setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                letter3.setBackground(baseColor);
                repaint();
            }
        });
        this.add(letter3);

        // Label para mostrar guia de que hacer
        caption = new JLabel();
        caption.setOpaque(true);
        caption.setBackground(hoverColor.darker());
        initLabel(caption, capX, capY, capW, capH, "  CLICK TO CHANGE LETTER  ");
        this.add(caption);
    }

    /**
     * Inicializa los "botones" para salir y volver al menu
     */
    private void initButtons() {
        exit = new JLabel();
        exit.setOpaque(true);
        exit.setBackground(baseColor);
        this.initLabel(exit, exitX, exitY, exitW, exitH, "EXIT");
        // Listener para registrar clicks del mouse
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameOver.highScoreEnd();
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(baseColor);
                repaint();
            }
        });
        this.add(exit);

        menu = new JLabel();
        menu.setOpaque(true);
        menu.setBackground(baseColor);
        this.initLabel(menu, menuX, menuY, menuW, menuH, "MENU");
        // Listener para registrar clicks del mouse
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameOver.highScoreEnd();
                main.MenuInicio();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menu.setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menu.setBackground(baseColor);
                repaint();
            }
        });
        this.add(menu);
    }

    /** Inicializa un label asignandole la imagen correspondiente */
    private void initLabel(JLabel label, int x, int y, int w, int h, String imagePath) {
        x = x * multiplier;
        y = y * multiplier;
        w = w * multiplier;
        h = h * multiplier;
        label.setBounds(x, y, w, h);
        if (!imagePath.contains(".png")) {
            setLetter(label, imagePath, w, h);
        } else {
            getIcon(label, folderPath + imagePath, w, h);
        }
    }

    /** Coloca una letra en el label */
    private void mouseClick(JLabel label) {
        char auxLetter = label.getText().charAt(0);
        auxLetter++;
        if (auxLetter > 'Z') {
            auxLetter = 'A';
        }
        setLetter(label, auxLetter + "", letterW * multiplier, letterH * multiplier);
    }

    /** Coloca una letra en el label */
    private void mouseClickR(JLabel label) {
        char auxLetter = label.getText().charAt(0);
        auxLetter--;
        if (auxLetter < 'A') {
            auxLetter = 'Z';
        }
        setLetter(label, auxLetter + "", letterW * multiplier, letterH * multiplier);
    }

    /** Obtiene un icono y lo escala para colocarlo */
    private void getIcon(JLabel label, String imagePath, int width, int height) {
        ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
        Image img = image.getImage();
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
    }

    /** Coloca una letra en el label y ajustarla al tamaño correspondiente */
    private void setLetter(JLabel label, String letter, int w, int h) {
        label.setText(letter);
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
    }

    /** Regresa el nombre conformado por las 3 letras */
    public String getName() {
        return letter1.getText() + letter2.getText() + letter3.getText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}