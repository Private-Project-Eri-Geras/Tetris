package com.leviatanes.tetris.TetrisGame.Tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.TetrisGame.TetrisBlock;

public class Oshape extends TetrisBlock {
        private static final int[][] block = {
                        { 1, 1 },
                        { 1, 1 }
        };
        private static final int[][][] blockRotations = new int[][][] {
                        {
                                        { 1, 1 },
                                        { 1, 1 }
                        },
                        {
                                        { 1, 1 },
                                        { 1, 1 }
                        },
                        {
                                        { 1, 1 },
                                        { 1, 1 }
                        },
                        {
                                        { 1, 1 },
                                        { 1, 1 }
                        }
        };
        private static final Color darkColor = new Color(212, 172, 13);
        private static final Color lightColor = new Color(244, 208, 63);
        private static final Color borderColor = new Color(156, 100, 12);

        public Oshape() {
                super(block, blockRotations, darkColor, lightColor, borderColor);
        }
}