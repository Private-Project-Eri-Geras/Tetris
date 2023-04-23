package com.leviatanes.tetris.TetrisGame.Tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.TetrisGame.TetrisBlock;

public class Tshape extends TetrisBlock {
        private static final int[][] block = {
                        { 0, 1, 0 },
                        { 1, 1, 1 }
        };
        private static final int[][][] blockRotations = new int[][][] {
                        {
                                        { 0, 1, 0 },
                                        { 1, 1, 1 }
                        },
                        {
                                        { 1, 0 },
                                        { 1, 1 },
                                        { 1, 0 }
                        },
                        {
                                        { 1, 1, 1 },
                                        { 0, 1, 0 }
                        },
                        {
                                        { 0, 1 },
                                        { 1, 1 },
                                        { 0, 1 }
                        }
        };
        private static final Color darkColor = new Color(85, 31, 168);
        private static final Color lightColor = new Color(139, 78, 234);

        public Tshape() {
                super(block, blockRotations, darkColor, lightColor);
        }
}