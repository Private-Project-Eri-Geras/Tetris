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
                super(block, blockRotations, darkColor, lightColor);
        }
}