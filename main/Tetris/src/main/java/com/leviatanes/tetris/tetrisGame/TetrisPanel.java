package com.leviatanes.tetris.tetrisGame;

import com.leviatanes.tetris.tetrisGame.game.GameArea;
import com.leviatanes.tetris.tetrisGame.game.GameControls;
import com.leviatanes.tetris.tetrisGame.game.GameThread;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.HoldPanel;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.NextPanel;
import com.leviatanes.tetris.tetrisGame.game.sidePanels.StatsPanel;

public class TetrisPanel extends javax.swing.JPanel {
        /** lleva el control del juego */
        private GameArea gameArea;
        /** lleva el control del panel de la figura siguiente */
        private NextPanel nextShape;
        /** lleva el control del panel de estadisticas */
        private StatsPanel statsPanel;
        /** lleva el control de la figura guardada */
        private HoldPanel holdShape;
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
        // offset para el panel de pieza guardada
        private final int stashShapeYoffset = 44;
        private final int stashShapeXoffset = 65;
        private final int stashShapeWidth = 20;
        private final int stashShapeHeight = 20;
        // offset para el panel de estadisticas
        private final int statsHolderYoffset = 12;
        private final int statsHolderXoffset = 3;
        private final int statsHolderWidth = 24;
        private final int statsHolderHeight = 56;

        public TetrisPanel(int width, int height, int multiplier) {
                // initComponents();
                // inicializar componentes del juego
                this.initGameComponents(width, height, multiplier);

        }

        private void initGameComponents(int width, int height, int multiplier) {
                this.setLayout(null);
                this.setBounds(0, 0, width, height);
                this.setSize(width, height);

                this.nextShape = new NextPanel(multiplier);
                nextShape.setBounds(nextShapeXoffset * multiplier, nextShapeYoffset * multiplier,
                                nextShapeWidth * multiplier, nextShapeHeight * multiplier);
                nextShape.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                this.add(nextShape);

                this.statsPanel = new StatsPanel(multiplier);
                statsPanel.setBounds(statsHolderXoffset * multiplier, statsHolderYoffset * multiplier,
                                statsHolderWidth * multiplier, statsHolderHeight * multiplier);

                this.add(statsPanel);

                this.holdShape = new HoldPanel(multiplier);
                holdShape.setBounds(stashShapeXoffset * multiplier, stashShapeYoffset * multiplier,
                                stashShapeWidth * multiplier, stashShapeHeight * multiplier);
                this.add(holdShape);

                final int xGA = gameHolderXoffset * multiplier;
                final int yGA = gameHolderYoffset * multiplier;
                final int widthGA = gameHolderWidth * multiplier;
                final int heightGA = gameHolderHeight * multiplier;
                this.gameArea = new GameArea(xGA, yGA, widthGA, heightGA, nextShape, holdShape);
                this.add(gameArea);

                this.gameThread = new GameThread(this.gameArea, statsPanel);

                this.gameControls = new GameControls(this.gameArea, this.gameThread, this.holdShape);

                this.statsPanel.setVisible(true);
                this.setVisible(true);
                this.gameThread.start();
        }

        public GameControls getGameControls() {
                return this.gameControls;
        }
}