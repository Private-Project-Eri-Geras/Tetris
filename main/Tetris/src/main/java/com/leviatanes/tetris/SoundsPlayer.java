package com.leviatanes.tetris;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundsPlayer {
    /** clip de musica principal */
    private static Clip clip = null;
    /** control de ganancia de la musica */
    private static FloatControl gainControl = null;
    /** bandera de musica silenciada */
    private static boolean muted = false;
    /** ganancia anterior */
    private static float previousGain = 0f;

    public static void playGameMusic() {
        try {
            // carga el archivo de audio
            String path = "/com/leviatanes/tetris/tetrisGame/game/music/mainTheme.wav";
            InputStream audioSrc = SoundsPlayer.class.getResourceAsStream(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);

            // crea el clip de audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // control de ganancia
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            // configura el loop del clip de audio
            clip.setLoopPoints(0, -1); // -1 indica que se repita indefinidamente
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            // establece el valor inicial de ganancia
            setGain(0.88f);

            // inicia la reproducción del clip de audio
            clip.start();

        } catch (Exception e) {
            System.out.println("Error al reproducir el archivo de audio: " + e.getMessage());
        }
    }

    /**
     * set de la ganancia
     * entre 0 y 1
     * 0 es silenciado
     * 1 es el maximo
     * 
     * @param gain
     */
    public static void setGain(float gain) {
        if (gainControl != null) {
            // asegurarse de que la ganancia no sea menor que 0 o mayor que 1
            gain = Math.max(0, Math.min(1, gain));
            float finalGain = gainControl.getMinimum() + (gainControl.getMaximum() - gainControl.getMinimum()) * gain;
            gainControl.setValue(finalGain);
        }
    }

    /**
     * Retorna la ganancia actual
     * entre 0 y 1
     * 0 es silenciado
     * 1 es el maximo
     * 
     * @return float
     */
    public static float getGain() {
        if (gainControl != null) {
            float maxGain = gainControl.getMaximum();
            float minGain = gainControl.getMinimum();
            float range = maxGain - minGain;
            float currentGain = gainControl.getValue();
            return (currentGain - minGain) / range;
        } else {
            return 0f;
        }
    }

    /**
     * Activa o desactiva la musica
     */
    public static void toggleMute() {
        if (muted) {
            // si el audio estaba muteado, restaura el valor de ganancia previo
            gainControl.setValue(previousGain);
            muted = false;
        } else {
            // si el audio estaba sonando, guarda el valor de ganancia actual y lo pone en
            // mute
            previousGain = gainControl.getValue();
            gainControl.setValue(0.0f);
            muted = true;
        }
    }

    /**
     * Detiene la musica
     */
    public static void stopMain() {
        if (clip != null) {
            clip.stop();
        }
    }

    /**
     * baja el volumen de la musica lentamente
     * hasta dejarla en 0
     */
    public static void fadeOutMain() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                float gain = getGain();
                while (gain > 0) {
                    gain -= 0.01f;
                    setGain(gain);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopMain();
            }
        }).start();
    }

    /**
     * 
     */
}
