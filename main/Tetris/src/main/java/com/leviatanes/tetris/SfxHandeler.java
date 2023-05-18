package com.leviatanes.tetris;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SfxHandeler {
    private FloatControl gainControl;
    private Clip clip;
    private float minimum;
    private float maximum;
    private float range;
    private float gain;
    private float volume;
    private AudioInputStream audioInputStream;
    private long duration;
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    private boolean looping;

    public SfxHandeler(Clip clip, float vol, AudioInputStream audioInputStream) {
        this.clip = clip;
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        minimum = gainControl.getMinimum();
        maximum = gainControl.getMaximum();
        range = maximum - minimum;
        gain = (range * vol) + minimum;
        volume = vol;
        this.audioInputStream = audioInputStream;
        // conseguir la duracion en milisegundos
        duration = (long) ((audioInputStream.getFrameLength() + 0.0) / audioInputStream.getFormat().getFrameRate()
                * 1000);

        gainControl.setValue(gain); // Mover esta línea aquí
        looping = false;
    }

    /** play absoluto (musica) */
    public void play() {
        clip.setFramePosition(0);
        if (looping) {
            clip.setLoopPoints(0, -1);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        clip.start();
    }

    @SuppressWarnings("unused")
    /** play por copia (simultaneo) */
    public void play(String path) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(getClass().getResource(path)));
                    FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    control.setValue(gain);

                    // Crear ligas débiles para el Clip y el FloatControl
                    WeakReference<Clip> clipWeakRef = new WeakReference<>(clip);
                    WeakReference<FloatControl> controlWeakRef = new WeakReference<>(control);

                    if (looping) {
                        clip.setLoopPoints(0, -1);
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    clip.addLineListener(new LineListener() {
                        @Override
                        public void update(javax.sound.sampled.LineEvent event) {
                            if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                                Clip clip = clipWeakRef.get();
                                if (clip != null) {
                                    clip.close();
                                }
                            }
                        }
                    });
                    clip.start();

                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stop() {
        clip.stop();
    }

    public void loop() {
        looping = true;
    }

    public void loop(int times) {
        clip.loop(times);
    }

    public void close() {
        clip.close();
    }

    public boolean isRunning() {
        return clip.isRunning();
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public Clip getClip() {
        return clip;
    }

    public void setGainControl(FloatControl gainControl) {
        this.gainControl = gainControl;
    }

    public FloatControl getGainControl() {
        return gainControl;
    }

    public float getMinimum() {
        return minimum;
    }

    public float getMaximum() {
        return maximum;
    }

    public float getRange() {
        return range;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        gain = (range * volume) + minimum;
        gainControl.setValue(gain);
    }

    public float getVolume() {
        return volume;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    public void addLineListener(LineListener listener) {
        clip.addLineListener(listener);
    }

    // todos generar solo todos los getters
    public long getDuration() {
        return duration;
    }

    public float getGain() {
        return this.gain;
    }
}