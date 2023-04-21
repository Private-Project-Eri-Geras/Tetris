package com.leviatanes.tetris.TetrisGame.Tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.TetrisGame.TetrisBlock;

public class Lshape extends TetrisBlock {
    private static final int[][] block = {
            { 0, 0, 1 },
            { 1, 1, 1 }
    };
    private static final int[][][] blockRotations = new int[][][] {
            {
                    { 0, 0, 1 },
                    { 1, 1, 1 }
            },
            {
                    { 1, 0 },
                    { 1, 0 },
                    { 1, 1 }
            },
            {
                    { 1, 1, 1 },
                    { 1, 0, 0 }
            },
            {
                    { 1, 1 },
                    { 0, 1 },
                    { 0, 1 }
            }
    };
    private static final Color darkColor = new Color(186, 74, 0);
    private static final Color lightColor = new Color(243, 156, 18);
    private static final Color borderColor = new Color(110, 44, 0);

    public Lshape() {
        super(block, blockRotations, darkColor, lightColor, borderColor);
    }
}
