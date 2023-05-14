package com.leviatanes.tetris.tetrisGame.game.gameOver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * [FILE READER]
 * Lee los puntajes de un archivo de texto
 * permite reemplazar un puntaje por otro
 * y ordenar los puntuajes en caso de que
 * haya sido modificados externamente
 * 
 * @author Eriarer (Abraham)
 * 
 * @see Score
 */
public class ScoreReader {
    private static final int MAX_SCORES = 10;
    private Score[] scores;

    public ScoreReader() {
        String fileName = "/com/leviatanes/tetris/tetrisGame/game/gameOver/highScores.txt";
        File file = new File(ScoreReader.class.getResource(fileName).getFile());
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee los puntajes almacenados en el archivo de puntajes y los devuelve como un
     * array de objetos Score.
     * 
     * @return Un array de objetos Score con los puntajes leídos del archivo, o null
     *         si no se encontraron puntajes.
     */
    public Score[] readScores() {
        // Se especifica la ruta del archivo de puntajes.
        String fileName = "/com/leviatanes/tetris/tetrisGame/game/gameOver/highScores.txt";
        // Se crea un objeto File para el archivo de puntajes.
        File file = new File(ScoreReader.class.getResource(fileName).getFile());
        // Se crea un objeto Scanner para leer el archivo de puntajes.
        Scanner in = null;
        boolean foundScores = false;
        try {
            in = new Scanner(file);
            // Se crea un array de Strings para almacenar las líneas del archivo.
            String[] line = new String[MAX_SCORES];
            for (int i = 0; i < MAX_SCORES; i++) {
                line[i] = "";
            }
            // Se lee el archivo línea por línea y se almacena cada línea en el array.
            int i = 0;
            for (i = 0; i < MAX_SCORES && in.hasNext(); i++) {
                line[i] = in.nextLine();
            }
            // Se busca la primera línea vacía en el array para determinar cuántos puntajes
            // hay almacenados.
            for (i = 0; i < MAX_SCORES; i++) {
                if (line[i].equals("")) {
                    break;
                } else {
                    foundScores = true;
                }
            }
            // Si no se encontraron puntajes, se devuelve un objeto nulo.
            if (!foundScores) {
                return null;
            }
            // Se crea un nuevo array de objetos Score con el tamaño determinado en el paso
            // anterior.
            scores = new Score[i];
            // Se convierte cada línea del archivo en un objeto Score y se almacena en el
            // array de puntajes.
            for (i = 0; i < scores.length; i++) {
                String[] parts = line[i].split(" ");// El separador de campos es " "
                scores[i] = new Score(parts[0], Integer.parseInt(parts[1]));// parts= (nombre, score)
            }
        } catch (IOException e) {
            // Se maneja cualquier error de IO que pueda ocurrir al leer el archivo de
            // puntajes.
            System.out.println("Error al crear el archivo: " + e.getMessage());
        } finally {
            // Se cierra el objeto Scanner.
            if (in != null) {
                in.close();
            }
        }
        // Se devuelve el array de objetos Score con los puntajes leídos del archivo.
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
        String[][] scoresStrings;
        this.scores = readScores();
        if (scores == null) {
            scoresStrings = new String[1][2];
            scoresStrings[0][0] = score.getName();
            scoresStrings[0][1] = Integer.toString(score.getScore());
        } else {

            scoresStrings = new String[this.scores.length][2];
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
        }
        return scoresStrings;
    }

    private void writeScore(String[][] scores) {
        String fileName = "highScores.txt";
        System.out.println(ScoreReader.class.getResource(fileName).getFile());
        try {
            FileWriter writer = new FileWriter(ScoreReader.class.getResource(fileName).getFile());
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

    // gett de scores
    public Score[] getScores() {
        if (scores == null) {
            scores = readScores();
        }
        return scores;
    }
}
