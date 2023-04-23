package com.leviatanes.tetris.TetrisGame.Tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.TetrisGame.TetrisBlock;

public class Jshape extends TetrisBlock {
        private static final int[][] block = {
                        { 1, 0, 0 },
                        { 1, 1, 1 }
        };
        private static final int[][][] blockRotations = new int[][][] {
                        {
                                        { 1, 0, 0 },
                                        { 1, 1, 1 }
                        },
                        {
                                        { 1, 1 },
                                        { 1, 0 },
                                        { 1, 0 }
                        },
                        {
                                        { 1, 1, 1 },
                                        { 0, 0, 1 }
                        },
                        {
                                        { 0, 1 },
                                        { 0, 1 },
                                        { 1, 1 }
                        }
        };
        private static final Color darkColor = new Color(0, 0, 164);
        private static final Color lightColor = new Color(45, 85, 194);

        public Jshape() {
                super(block, blockRotations, darkColor, lightColor);
        }
}