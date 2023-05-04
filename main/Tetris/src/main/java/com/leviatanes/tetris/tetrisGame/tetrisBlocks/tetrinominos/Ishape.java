package com.leviatanes.tetris.tetrisGame.tetrisBlocks.tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;

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
        private static final Color darkColor = new Color(0, 143, 150);
        private static final Color lightColor = new Color(40, 185, 192);

        public Ishape() {
                super(block, blockRotations, 'I', darkColor, lightColor);
        }

        @Override
        /**
         * Spawnea el bloque enmedio del tablero
         * y lo coloca en la posicion inicial correspondiente
         * 
         * @param colums numero de columnas del tablero
         */
        public void spawn(int colums) {
                super.setCurrentRotation(0);
                super.setBlock(block);
                super.setX((colums - super.getWidth()) / 2);
                super.setY(1);
        }

        @Override
        public void rotate() {
                super.rotate();
                switch (super.getCurrentRotation()) {
                        case 0:
                                super.setX(super.getX() - 1);
                                super.setY(super.getY() + 1);
                                break;
                        case 1:
                                super.setX(super.getX() + 2);
                                super.setY(super.getY() - 1);
                                break;
                        case 2:
                                super.setX(super.getX() - 2);
                                super.setY(super.getY() + 2);
                                break;
                        case 3:
                                super.setX(super.getX() + 1);
                                super.setY(super.getY() - 2);
                                break;
                }
        }

        @Override
        public void rotateBack() {
                super.rotateBack();
                switch (super.getCurrentRotation()) {
                        case 0:
                                super.setX(super.getX() - 2);
                                super.setY(super.getY() + 1);
                                break;
                        case 1:
                                super.setX(super.getX() + 2);
                                super.setY(super.getY() - 2);
                                break;
                        case 2:
                                super.setX(super.getX() - 1);
                                super.setY(super.getY() + 2);
                                break;
                        case 3:
                                super.setX(super.getX() + 1);
                                super.setY(super.getY() - 1);
                                break;
                }
        }
}