package com.leviatanes.tetris.tetrisGame.tetrisBlocks.tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;

public class Sshape extends TetrisBlock {
        private static final int[][] block = {
                        { 0, 1, 1 },
                        { 1, 1, 0 }
        };
        private static final int[][][] blockRotations = new int[][][] {
                        {
                                        { 0, 1, 1 },
                                        { 1, 1, 0 }
                        },
                        {
                                        { 1, 0 },
                                        { 1, 1 },
                                        { 0, 1 }
                        },
                        {
                                        { 0, 1, 1 },
                                        { 1, 1, 0 }
                        },
                        {
                                        { 1, 0 },
                                        { 1, 1 },
                                        { 0, 1 }
                        }
        };
        private static final Color darkColor = new Color(35, 144, 27);
        private static final Color lightColor = new Color(54, 185, 44);

        public Sshape() {
                super(block, blockRotations, 'S', darkColor, lightColor);
        }

        @Override
        public void rotate() {
                super.rotate();
                switch (super.getCurrentRotation()) {
                        case 0:
                                break;
                        case 1:
                                super.setX(super.getX() + 1);
                                break;
                        case 2:
                                super.setX(super.getX() - 1);
                                super.setY(super.getY() + 1);
                                break;
                        case 3:
                                super.setY(super.getY() - 1);
                                break;
                }

        }

        @Override
        public void rotateBack() {
                super.rotateBack();
                switch (super.getCurrentRotation()) {
                        case 0:
                                super.setX(super.getX() - 1);
                                break;
                        case 1:
                                super.setX(super.getX() + 1);
                                super.setY(super.getY() - 1);
                                break;
                        case 2:
                                super.setY(super.getY() + 1);
                                break;
                        case 3:
                                break;
                }
        }
}