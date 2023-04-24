package com.leviatanes.tetris.WindowFit;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Window {
    public static void main(String[] args) {
        // Constants
        final int BASE_WIDTH = 90;
        final int BASE_HEIGHT = 80;
        final int MATRIX_ROWS = 28;
        final int MATRIX_COLUMNS = 2;

        //  Variables
        int width = 0;
        int height = 0;

        // Get screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int) screenSize.getHeight();
        
        // Create matrix with 28 different resolutions
        int[][] resolution = new int[MATRIX_ROWS][MATRIX_COLUMNS];
        resolution[0][0] = BASE_WIDTH;
        resolution[0][1] = BASE_HEIGHT;
        
        for (int i = 1; i < MATRIX_ROWS && resolution[i-1][1] < screenHeight; i++) {
            resolution[i][0] = resolution[0][0] * (i + 1);
            resolution[i][1] = resolution[0][1] * (i + 1);
            width = resolution[i-1][0];
            height = resolution[i-1][1];
        }

        JFrame jframe = new JFrame("JFrame Vacio");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(width, height);
        jframe.setVisible(true);
    }
}
