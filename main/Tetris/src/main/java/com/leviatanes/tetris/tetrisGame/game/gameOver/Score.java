package com.leviatanes.tetris.tetrisGame.game.gameOver;

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
