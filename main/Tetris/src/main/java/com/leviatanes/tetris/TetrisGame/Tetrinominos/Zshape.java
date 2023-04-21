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
        private static final Color darkColor = new Color(172, 22, 22);
        private static final Color lightColor = new Color(234, 21, 3);
        private static final Color borderColor = new Color(147, 19, 19);

        public Zshape() {
                super(block, blockRotations, darkColor, lightColor, borderColor);
        }
}