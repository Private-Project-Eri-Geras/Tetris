package com.leviatanes.tetris.tetrisGame.game.gameOver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreReader {
    private static final int MAX_SCORES = 10;
    private File file;
    private Scanner in;
    private FileWriter writer;

    public ScoreReader() {
        String fileName = "main/Tetris/src/main/java/com/leviatanes/tetris/tetrisGame/game/gameOver/highScores.txt";
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + fileName;
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("El archivo ha sido creado exitosamente.");
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        try {
            this.in = new Scanner(file);
            this.writer = new FileWriter(file, true);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo writer");
        }

    }

    public void dispouse() {
        this.in.close();
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Score[] readScores() {
        Score[] scores = new Score[MAX_SCORES];
        String line;
        int i = 0;
        for (i = 0; i < MAX_SCORES && in.hasNext(); i++) {
            line = in.nextLine();
            String[] parts = line.split(" ");
            scores[i] = new Score(parts[0], Integer.parseInt(parts[1]));
        }
        for (int j = i + 1; j < MAX_SCORES; j++) {
            scores[j] = new Score();
        }
        return scores;
    }

    public void replaceScore(Score score) {
        // primerlo eliminar el score mas bajo
        // luego escribir el nuevo score
        String[][] scores = readAndReplace(score);
        scores = sort(scores);
        writeScore(scores);

    }

    private String[][] readAndReplace(Score score) {
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String scores[][] = new String[MAX_SCORES][2];
        int i = 0;
        while (in.hasNext() && i < MAX_SCORES) {
            line = in.nextLine();
            scores[i][0] = line.split(" ")[0];
            scores[i][1] = line.split(" ")[1];
            i++;
        }
        scores[9][0] = score.getName();
        scores[9][1] = Integer.toString(score.getScore());
        return scores;
    }

    private void writeScore(String[][] scores) {
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < MAX_SCORES; i++) {
            try {
                writer.write(scores[i][0] + " " + scores[i][1] + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String[][] sort(String[][] scores) {
        for (int i = 0; i < MAX_SCORES; i++) {
            for (int j = i + 1; j < MAX_SCORES; j++) {
                if (Integer.parseInt(scores[i][1]) < Integer.parseInt(scores[j][1])) {
                    String[] temp = scores[i];
                    scores[i] = scores[j];
                    scores[j] = temp;
                }
            }
        }
        return scores;
    }

}
