package com.leviatanes.tetris.tetrisGame.tetrisBlocks.tetrinominos;

import java.awt.Color;

import com.leviatanes.tetris.tetrisGame.tetrisBlocks.TetrisBlock;

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
    private static final Color darkColor = new Color(183, 149, 11);
    private static final Color lightColor = new Color(241, 196, 15);

    public Oshape() {
        super(block, blockRotations, 'O', darkColor, lightColor);
    }
}