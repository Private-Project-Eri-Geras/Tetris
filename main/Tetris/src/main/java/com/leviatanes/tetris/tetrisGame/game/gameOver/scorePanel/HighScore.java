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

import com.leviatanes.tetris.tetrisGame.game.gameOver.GameOver;
import com.leviatanes.tetris.Main;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.scorePanels.ScoreLabel;

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
    private Color letter1Color;
    private JLabel letter2;
    private Color letter2Color;
    private JLabel letter3;
    private Color letter3Color;

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

    private final String folderPath = "/com/leviatanes/tetris/tetrisGame/game/sidePanels/images/";

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
    private Color exitColor;
    private JLabel menu;
    private Color menuColor;
    private Color baseColor = new Color(255, 255, 255, 100);
    private Color hoverColor = new Color(255, 255, 255, 255);
    private boolean ended = false;

    public HighScore(int multiplier, int score) {
        this.setLayout(null);
        this.multiplier = multiplier;
        this.setBounds(0, 0, scorePanelW * multiplier, scorePanelH * multiplier);
        this.score = score;
        ended = false;
        System.out.println(" Iniciando con score= " + this.score + " score 2= " + score);
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
        letter1Color = new Color(110, 110, 110, 255);
        //añadir mause  click listener
        letter1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1){
                    mouseClick(letter1);
                }else if(evt.getButton() == MouseEvent.BUTTON3){
                    mouseClickR(letter1);
                }
            }
        });
        letter1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                letter1Color = hoverColor;
                repaint();
            }
        });
        letter1.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                letter1Color = baseColor;
                repaint();
            }
        });
        initLabel(letter1, letterX, letterY, letterW, letterH, "A");
        this.add(letter1);


        letter2 = new JLabel();
        letter2Color = new Color(110, 110, 110, 255);
        letter2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1){
                    mouseClick(letter2);
                }else if(evt.getButton() == MouseEvent.BUTTON3){
                    mouseClickR(letter2);
                }
            }
        });
        letter2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                letter2Color = hoverColor;
                repaint();
            }
        });
        letter2.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                letter2Color = baseColor;
                repaint();
            }
        });
        initLabel(letter2, letterX + lXoffset, letterY, letterW, letterH, "A");
        this.add(letter2);


        letter3 = new JLabel();
        letter3Color = new Color(110, 110, 110, 255);
        letter3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1){
                    mouseClick(letter3);
                }else if(evt.getButton() == MouseEvent.BUTTON3){
                    mouseClickR(letter3);
                }
            }
        });
        letter3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                letter3Color = hoverColor;
                repaint();
            }
        });
        letter3.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                letter3Color = baseColor;
                repaint();
            }
        });
        initLabel(letter3, letterX + lXoffset * 2, letterY, letterW, letterH, "A");
        this.add(letter3);

        caption = new JLabel();
        initLabel(caption, capX, capY, capW, capH, "CLICK TO SET NAME");
        this.add(caption);
    }

    private void initButtons() {
        exit = new JLabel();
        this.add(exit);
        this.initLabel(exit, exitX, exitY, exitW, exitH, "EXIT");
        exitColor = baseColor;
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameOver.highScoreEnd();
                System.exit(0);
            }
        });
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitColor = hoverColor;
                repaint();
            }
        });
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
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameOver.highScoreEnd();
                new Main(true);
            }
        });
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuColor = hoverColor;
                repaint();
            }
        });
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menuColor = baseColor;
                repaint();
            }
        });
    }

    public boolean isEnded() {
        return ended;
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

    private void mouseClick(JLabel label){
        char auxLetter = label.getText().charAt(0);
        auxLetter++;
        if(auxLetter > 'Z'){
            auxLetter = 'A';
        }
        setLetter(label, auxLetter+"", letterW*multiplier, letterH*multiplier);
    }

    private void mouseClickR(JLabel label){
        char auxLetter = label.getText().charAt(0);
        auxLetter--;
        if(auxLetter < 'A'){
            auxLetter = 'Z';
        }
        setLetter(label, auxLetter+"", letterW*multiplier, letterH*multiplier);
    }
    
    /** Obtiene un icono y lo escala para colocarlo */
    private void getIcon(JLabel label, String imagePath, int width, int height) {
        ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
        Image img = image.getImage();
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImage));
    }

    private void setLetter(JLabel label, String letter, int w, int h){
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

    public String getName() {
        return letter1.getText() + letter2.getText() + letter3.getText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintLetter(g, letter1, letter1Color);
        paintLetter(g, letter2, letter2Color);
        paintLetter(g, letter3, letter3Color);
        int x = exitX * multiplier;
        int y = exitY * multiplier;
        int width = exitW * multiplier;
        int height = exitH * multiplier;
        g.setColor(exitColor);
        g.fillRect(x, y, width, height);

        x = menuX * multiplier;
        y = menuY * multiplier;
        width = menuW * multiplier;
        height = menuH * multiplier;
        g.setColor(menuColor);
        g.fillRect(x, y, width, height);

        x = capX  * multiplier;
        y = capY  * multiplier;
        width = capW  * multiplier;
        height = capH  * multiplier;
        g.setColor(new Color(255, 255, 255, 200));
        g.fillRect(x, y, width, height);
    }

    private void paintLetter(Graphics g, JLabel label, Color color) {
        g.setColor(color);
        int x = label.getBounds().x;
        int y = label.getBounds().y;
        int width = label.getBounds().width;
        int height = label.getBounds().height;
        g.fillRect(x, y, width, height);
        
    }
}