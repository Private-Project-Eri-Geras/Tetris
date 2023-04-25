package com.leviatanes.tetris.tetrisGame.game.statsPanel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.leviatanes.tetris.tetrisGame.game.GameArea;

public class StatsPanel extends javax.swing.JPanel {
    private GameArea gameArea;

    private JLabel scoreLabel;
    private int score = 0;

    // offset para el scoreLaber
    private int scoreLabelOffsetX = 3;
    private int scoreLabelOffsetY = 5;
    private int scoreLabelWidth = 18;
    private int scoreLabelHeight = 6;

    public StatsPanel(int multiplier) {
        initComponents();
        initPanels(multiplier);
        repaint();
        this.setVisible(true);

    }

    // =========[GETTERS]==========
    public int getScore() {
        return score;
    }

    // =========[SETTERS]==========
    public void setScore(int score) {
        this.score = score;
    }

    public void setGameArea(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    private void initPanels(int multiplier) {
        // inicializar el panel de score con offset relativo a las coordenadas de
        // StatsPanel
        this.scoreLabel = new JLabel();
        int sLblX = this.scoreLabelOffsetX * multiplier;
        int sLblY = this.scoreLabelOffsetY * multiplier;
        int sLblW = this.scoreLabelWidth * multiplier;
        int sLblH = this.scoreLabelHeight * multiplier;
        this.scoreLabel.setBounds(sLblX, sLblY, sLblW, sLblH);
        String scoreImagePath = "src/main/java/com/leviatanes/tetris/tetrisGame/game/statsPanel/images/Score.jpg";
        this.scoreLabel.setIcon(new ImageIcon(
                new ImageIcon(getClass().getResource(scoreImagePath)).getImage().getScaledInstance(sLblW, sLblH,
                        Image.SCALE_SMOOTH)));
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
