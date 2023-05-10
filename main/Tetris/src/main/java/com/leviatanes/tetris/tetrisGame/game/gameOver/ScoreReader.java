package com.leviatanes.tetris.tetrisGame.game.gameOver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreReader {
    private static final int MAX_SCORES = 10;
    private Score[] scores;

    public ScoreReader() {
        String fileName = "main/Tetris/src/main/java/com/leviatanes/tetris/tetrisGame/game/gameOver/highScores.txt";
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + fileName;
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        try {
            Scanner in = new Scanner(file);
            FileWriter writer = new FileWriter(file, true);
            in.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo writer");
        }

    }

    public Score[] readScores() {
        String fileName = "main/Tetris/src/main/java/com/leviatanes/tetris/tetrisGame/game/gameOver/highScores.txt";
        String currentDir = System.getProperty("user.dir");
        for (int i = 0; i < currentDir.length(); i++) {
            if (currentDir.charAt(i) == 92) {
                currentDir = currentDir.substring(0, i) + "/" + currentDir.substring(i + 1);
            }
        }
        String filePath = currentDir + "/" + fileName;
        File file = new File(filePath);
        Scanner in = null;
        try {
            file.createNewFile();
            in = new Scanner(file);
            String[] line = new String[MAX_SCORES];
            for (int i = 0; i < MAX_SCORES; i++) {
                line[i] = "";
            }
            int i = 0;
            for (i = 0; i < MAX_SCORES && in.hasNext(); i++) {
                line[i] = in.nextLine();
            }
            for (i = 0; i < MAX_SCORES; i++) {
                if (line[i].equals("")) {
                    break;
                }
            }
            scores = new Score[i];
            for (i = 0; i < scores.length; i++) {
                String[] parts = line[i].split(" ");
                scores[i] = new Score(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
        in.close();
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
        // busca el menor dato en this.scores
        // y lo remplaza conel score dado
        this.scores = readScores();

        String[][] scoresStrings = new String[this.scores.length][2];
        for (int i = 0; i < this.scores.length; i++) {
            scoresStrings[i][0] = this.scores[i].getName();
            scoresStrings[i][1] = Integer.toString(this.scores[i].getScore());
        }
        scoresStrings = sort(scoresStrings);
        if (scoresStrings.length == 0) {
            scoresStrings = new String[1][2];
            scoresStrings[0][0] = score.getName();
            scoresStrings[0][1] = Integer.toString(score.getScore());
        } else {
            if (scoresStrings.length < MAX_SCORES) {
                String[][] tmpString = scoresStrings;
                scoresStrings = new String[scoresStrings.length + 1][2];
                for (int i = 0; i < tmpString.length; i++) {
                    scoresStrings[i][0] = tmpString[i][0];
                    scoresStrings[i][1] = tmpString[i][1];
                }
            }
            scoresStrings[scoresStrings.length - 1][0] = score.getName();
            scoresStrings[scoresStrings.length - 1][1] = Integer.toString(score.getScore());
        }
        this.scores = new Score[scoresStrings.length];
        scoresStrings = sort(scoresStrings);
        for (int i = 0; i < this.scores.length; i++) {
            this.scores[i] = new Score(scoresStrings[i][0], Integer.parseInt(scoresStrings[i][1]));
        }
        return scoresStrings;
    }

    private void writeScore(String[][] scores) {
        String fileName = "main/Tetris/src/main/java/com/leviatanes/tetris/tetrisGame/game/gameOver/highScores.txt";
        String currentDir = System.getProperty("user.dir");
        for (int i = 0; i < currentDir.length(); i++) {
            if (currentDir.charAt(i) == 92) {
                currentDir = currentDir.substring(0, i) + "/" + currentDir.substring(i + 1);
            }
        }
        String filePath = currentDir + "/" + fileName;
        try {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(writer);
            String linea = scores[0][0] + " " + scores[0][1];
            bw.write(linea);
            for (int i = 1; i < scores.length; i++) {
                linea = scores[i][0] + " " + scores[i][1];
                bw.newLine();
                bw.write(linea);
            }
            bw.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String[][] sort(String[][] scores) {
        try {
            for (int i = 0; i < scores.length; i++) {
                for (int j = i + 1; j < scores.length; j++) {
                    if (Integer.parseInt(scores[i][1]) < Integer.parseInt(scores[j][1])) {
                        String[] temp = scores[i];
                        scores[i] = scores[j];
                        scores[j] = temp;
                    }
                }
            }
        } catch (NullPointerException | NumberFormatException e) {

        }
        return scores;
    }

}
