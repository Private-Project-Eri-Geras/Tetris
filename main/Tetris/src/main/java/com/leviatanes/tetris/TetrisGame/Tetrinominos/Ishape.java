package com.leviatanes.tetris.TetrisGame.Tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.TetrisGame.TetrisBlock;

public class Ishape extends TetrisBlock {
        public static final int[][] block = {
                        { 1, 1, 1, 1 }
        };
        public static final int[][][] blockRotations = new int[][][] {
                        {
                                        { 1, 1, 1, 1 }
                        },
                        {
                                        { 1 },
                                        { 1 },
                                        { 1 },
                                        { 1 }
                        },
                        {
                                        { 1, 1, 1, 1 }
                        },
                        {
                                        { 1 },
                                        { 1 },
                                        { 1 },
                                        { 1 }
                        }
        };
        private static final Color darkColor = new Color(18, 124, 164);
        private static final Color lightColor = new Color(33, 97, 140);
        private static final Color borderColor = new Color(21, 67, 96);

        public Ishape() {
                super(block, blockRotations, darkColor, lightColor, borderColor);
        }

        @Override
        /**
         * Spawnea el bloque enmedio del tablero
         * y lo coloca en la posicion inicial correspondiente
         * 
         * @param colums numero de columnas del tablero
         */
        public void spawn(int colums) {
                super.setCurentRotation(0);
                super.setBlock(block);
                super.setX((colums - super.getWidth()) / 2);
                super.setY(1);
        }

        @Override
        public void rotate() {
                super.rotate();
                if (super.getCurrentRotation() == 1 || super.getCurrentRotation() == 3) {
                        super.setX(super.getX() + 1);
                        super.setY(super.getY() - 1);
                } else {
                        super.setX(super.getX() - 1);
                        super.setY(super.getY() + 1);
                }
        }

        @Override
        public void rotateBack() {
                super.rotateBack();
                if (super.getCurrentRotation() == 1 || super.getCurrentRotation() == 3) {
                        super.setX(super.getX() + 1);
                        super.setY(super.getY() - 1);
                } else {
                        super.setX(super.getX() - 1);
                        super.setY(super.getY() + 1);
                }
        }
}