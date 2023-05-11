package com.leviatanes.tetris.soundPlayer;

import javax.sound.sampled.Clip;

public class myAudioClip {
    private Clip clip;
    private int id;

    public myAudioClip(Clip clip, int id) {
        this.clip = clip;
        this.id = id;
    }

    public Clip getClip() {
        return clip;
    }

    public int getId() {
        return id;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public void setId(int id) {
        this.id = id;
    }
}
