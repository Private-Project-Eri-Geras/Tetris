package com.leviatanes.tetris.tetrisGame.game;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leviatanes.menus.SettingsReader;

public class PausedGame extends JPanel {
    private static final int BASE_WIDT = 30;
    private static final int BASE_HEIG = 72;

    private int multiplier;
    private JLabel pausedLabel;
    private JButton resumeButton;
    private JButton restartButton;
    private JButton mainMenuButton;
    private JButton exitButton;
    private GameArea gameArea;

    public PausedGame(GameArea gameArea) {
        setLayout(null);
        multiplier = SettingsReader.getMultiplier();
        setBounds(0, 0, BASE_WIDT * multiplier, BASE_HEIG * multiplier);
        this.setOpaque(false);
        this.gameArea = gameArea;
        initComponents();
        revalidate();
        repaint();
        setVisible(true);
    }

    private void initComponents() {
        Color base = new Color(200, 200, 200);
        Color hover = new Color(255, 255, 255);
        // ==================== pausedLabel ====================
        pausedLabel = new JLabel();

        int x = 0 * multiplier;
        int y = 3 * multiplier;
        int width = 30 * multiplier;
        int height = 10 * multiplier;
        pausedLabel.setBounds(x, y, width, height);
        this.setFont(pausedLabel, "PAUSED");
        pausedLabel.setVisible(true);
        pausedLabel.setText("PAUSED");
        pausedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pausedLabel.setOpaque(true);

        pausedLabel.setForeground(Color.white);
        pausedLabel.setBackground(new Color(0, 0, 0, 150));

        this.add(pausedLabel);

        // ==================== resumeButton ====================
        resumeButton = new JButton();
        resumeButton.setBounds(0, 15 * multiplier, 30 * multiplier, 10 * multiplier);
        setBtnFont(resumeButton, "RESUME", 20 * multiplier, 10 * multiplier);
        // deshabilitar el focus totalmente
        resumeButton.setFocusable(false);
        resumeButton.setFocusCycleRoot(false);
        resumeButton.setContentAreaFilled(false);
        resumeButton.setBorderPainted(false);
        resumeButton.setFocusPainted(false);
        resumeButton.setOpaque(true);
        resumeButton.setBackground(base);

        resumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameArea.togglePause();
            }
        });
        resumeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resumeButton.setBackground(hover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                resumeButton.setBackground(base);
            }
        });

        // ==================== restartButton ====================
        restartButton = new JButton();
        restartButton.setBounds(0, 29 * multiplier, 30 * multiplier, 10 * multiplier);
        setBtnFont(restartButton, "RESTART", 20 * multiplier, 10 * multiplier);
        // quitar el focus
        restartButton.setFocusable(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setBorderPainted(false);
        restartButton.setFocusPainted(false);
        restartButton.setOpaque(true);
        restartButton.setBackground(base);

        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameArea.restart();
            }
        });
        restartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                restartButton.setBackground(hover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                restartButton.setBackground(base);
            }
        });

        // ==================== mainMenuButton ====================
        mainMenuButton = new JButton();
        mainMenuButton.setBounds(0, 43 * multiplier, 30 * multiplier, 10 * multiplier);
        setBtnFont(mainMenuButton, "MAIN MENU", 20 * multiplier, 10 * multiplier);
        mainMenuButton.setFocusable(false);
        mainMenuButton.setContentAreaFilled(false);
        mainMenuButton.setBorderPainted(false);
        mainMenuButton.setFocusPainted(false);
        mainMenuButton.setOpaque(true);
        mainMenuButton.setBackground(base);

        mainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameArea.goToMainMenu();
            }
        });
        mainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuButton.setBackground(hover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuButton.setBackground(base);
            }
        });

        // ==================== exitButton ====================
        exitButton = new JButton();
        exitButton.setBounds(0, 57 * multiplier, 30 * multiplier, 10 * multiplier);
        setBtnFont(exitButton, "EXIT", 20 * multiplier, 10 * multiplier);
        exitButton.setFocusable(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(true);
        exitButton.setBackground(base);

        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setOpaque(true);
                exitButton.setBackground(hover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setOpaque(true);
                exitButton.setBackground(base);
            }
        });

        this.add(resumeButton);
        this.add(restartButton);
        this.add(mainMenuButton);
        this.add(exitButton);

    }

    private void setFont(JLabel label, String text) {
        label.setText(text);
        for (int i = 0;; i++) {
            label.setFont(new java.awt.Font("Impact", 0, i));
            if (label.getFontMetrics(label.getFont()).stringWidth(text) > this.getWidth() - 10 * multiplier) {
                label.setFont(new java.awt.Font("Impact", 0, i - 1));
                break;
            }
            if (label.getFontMetrics(label.getFont()).getHeight() > this.getHeight() - 10 * multiplier) {
                label.setFont(new java.awt.Font("Impact", 0, i - 1));
                break;
            }
        }
    }

    private void setBtnFont(JButton button, String text, int w, int h) {
        button.setText(text);
        for (int i = 0;; i++) {
            button.setFont(new java.awt.Font("Impact", 0, i));
            if (button.getFontMetrics(button.getFont()).stringWidth(text) > w) {
                button.setFont(new java.awt.Font("Impact", 0, i - 1));
                break;
            }
            if (button.getFontMetrics(button.getFont()).getHeight() > h) {
                button.setFont(new java.awt.Font("Impact", 0, i - 1));
                break;
            }
        }
    }

    // sobre escribir el graphics
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0));
    }
}
