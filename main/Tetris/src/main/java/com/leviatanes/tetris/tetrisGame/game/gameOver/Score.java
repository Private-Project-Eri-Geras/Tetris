package com.leviatanes.tetris.tetrisGame.game.gameOver;

/**
 * [STRUCT SCORE]
 * Lleva el nombre y el puntaje de un jugador
 * 
 * @author Leonardo
 * @author Eriarer (Abraham)
 */
public class Score {
    private int score;
    private String name;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Score() {
        this.name = "";
        this.score = -1;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

}
