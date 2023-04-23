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
        private static final Color darkColor = new Color(35, 144, 27);
        private static final Color lightColor = new Color(54, 185, 44);

        public Sshape() {
                super(block, blockRotations, darkColor, lightColor);
        }
}