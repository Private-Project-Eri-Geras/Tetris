package com.leviatanes.tetris.TetrisGame.Tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.TetrisGame.TetrisBlock;

public class Zshape extends TetrisBlock {
        private static final int[][] block = {
                        { 1, 1, 0 },
                        { 0, 1, 1 }
        };
        private static final int[][][] blockRotations = new int[][][] {
                        {
                                        { 1, 1, 0 },
                                        { 0, 1, 1 }
                        },
                        {
                                        { 0, 1 },
                                        { 1, 1 },
                                        { 1, 0 }
                        },
                        {
                                        { 1, 1, 0 },
                                        { 0, 1, 1 }
                        },
                        {
                                        { 0, 1 },
                                        { 1, 1 },
                                        { 1, 0 }
                        }
        };
        private static final Color darkColor = new Color(164, 10, 10);
        private static final Color lightColor = new Color(200, 63, 49);

        public Zshape() {
                super(block, blockRotations, 'Z', darkColor, lightColor);
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