package com.leviatanes.tetris.TetrisGame.Tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.TetrisGame.TetrisBlock;

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
        private static final Color darkColor = new Color(38, 163, 32);
        private static final Color lightColor = new Color(48, 211, 40);
        private static final Color borderColor = new Color(29, 117, 25);

        public Sshape() {
                super(block, blockRotations, darkColor, lightColor, borderColor);
        }
}