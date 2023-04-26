package com.leviatanes.tetris.tetrisGame;

import com.leviatanes.tetris.tetrisGame.game.GameArea;
import com.leviatanes.tetris.tetrisGame.game.GameControls;
import com.leviatanes.tetris.tetrisGame.game.GameThread;
import com.leviatanes.tetris.tetrisGame.game.StashShape.StashShape;
import com.leviatanes.tetris.tetrisGame.game.nextShape.NextShapePanel;
import com.leviatanes.tetris.tetrisGame.game.statsPanel.StatsPanel;

public class TetrisPanel extends javax.swing.JPanel {
        /** lleva el control del juego */
        private GameArea gameArea;
        /** lleva el control del panel de la figura siguiente */
        private NextShapePanel nextShape;
        /** lleva el control del panel de estadisticas */
        private StatsPanel statsHolder;
        /** lleva el control de la figura guardada */
        private StashShape stashShape;
        /** lleva el control de los controles */
        private GameControls gameControls;
        /** lleva el control del hilo del juego */
        private GameThread gameThread;

        // constantes para calculos de offset

        // offset para el panel del juego
        private final int gameHolderYoffset = 4;
        private final int gameHolderXoffset = 30;
        private final int gameHolderWidth = 30;
        private final int gameHolderHeight = 72;
        // offset para el panel de pieza siguiente
        private final int nextShapeYoffset = 12;
        private final int nextShapeXoffset = 65;
        private final int nextShapeWidth = 20;
        private final int nextShapeHeight = 20;

        // offset para el panel de estadisticas
        private final int statsHolderYoffset = 12;
        private final int statsHolderXoffset = 3;
        private final int statsHolderWidth = 24;
        private final int statsHolderHeight = 56;

        public TetrisPanel(int width, int height, int multiplier) {
                initComponents();
                // inicializar componentes del juego
                this.initGameComponents(width, height, multiplier);

        }

        private void initGameComponents(int width, int height, int multiplier) {
                this.setSize(width, height);

                this.gameArea = new GameArea(gameHolderXoffset * multiplier, gameHolderYoffset * multiplier,
                                gameHolderWidth * multiplier, gameHolderHeight * multiplier, 10);
                this.add(gameArea);

                this.nextShape = new NextShapePanel(multiplier);
                nextShape.setBounds(nextShapeXoffset * multiplier, nextShapeYoffset * multiplier,
                                nextShapeWidth * multiplier, nextShapeHeight * multiplier);
                nextShape.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                this.add(nextShape);

                this.statsHolder = new StatsPanel(multiplier);
                statsHolder.setBounds(statsHolderXoffset * multiplier, statsHolderYoffset * multiplier,
                                statsHolderWidth * multiplier, statsHolderHeight * multiplier);

                this.add(statsHolder);

                this.stashShape = new StashShape();

                this.add(stashShape);

                this.gameThread = new GameThread(this.gameArea, statsHolder, this.nextShape);

                this.gameControls = new GameControls(this.gameArea, this.gameThread, this.nextShape);

                this.statsHolder.setVisible(true);
                this.setVisible(true);
                this.gameThread.start();
        }

        public GameControls getGameControls() {
                return this.gameControls;
        }

        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 720, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 640, Short.MAX_VALUE));
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        // End of variables declaration//GEN-END:variables
}
