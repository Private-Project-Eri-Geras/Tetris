package com.leviatanes.tetris;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.BufferedInputStream;
import com.leviatanes.tetris.soundPlayer.myAudioClip;

public class SoundsPlayer {

    private static int clipIdCounter = 0;

    private static Map<String, myAudioClip> clips = new HashMap<>();

    private static List<myAudioClip> playingClips = new ArrayList<>();

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    private static float minimum = 0f;
    private static float previousGain = 0f;
    private static Clip mainMusic = null;
    private static boolean muted = false;
    private static FloatControl gainControl = null;

    private static final String soundPath = "/com/leviatanes/tetris/tetrisGame/game/music/";

    static {
        loadClip("allClear.wav");
        loadClip("fall.wav");
        loadClip("hardDrop.wav");
        loadClip("highestScore.wav");
        loadClip("highScore.wav");
        loadClip("hold.wav");
        loadClip("levelUp.wav");
        loadClip("move.wav");
        loadClip("ok.wav");
        loadClip("pause.wav");
        loadClip("results.wav");
        loadClip("rotate.wav");
        loadClip("single.wav");
        loadClip("softDrop.wav");
        loadClip("tetris.wav");
        loadClip("triple.wav");
        loadClip("Tspin.wav");
    }

    private static void loadClip(String sound) {
        try {
            // Obtener el archivo de sonido
            URL soundURL = SoundsPlayer.class.getResource(soundPath + sound);
            if (soundURL == null) {
                throw new RuntimeException("No se pudo encontrar el archivo de sonido: " + sound);
            }

            // Cargar el clip de sonido
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new BufferedInputStream(soundURL.openStream()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Crear el objeto AudioClip
            int id = clipIdCounter++;
            myAudioClip audioClip = new myAudioClip(clip, id);

            // Agregar el objeto AudioClip al mapa de clips
            clips.put(sound, audioClip);

        } catch (Exception e) {
            throw new RuntimeException("No se pudo cargar el archivo de sonido: " + sound, e);
        }
    }

    public static void playAllClear() {
        playSound("allClear.wav");
    }

    public static void playFall() {
        playSound("fall.wav");
    }

    public static void playHardDrop() {
        playSound("hardDrop.wav");
    }

    public static void playHighestScore() {
        playSound("highestScore.wav");
    }

    public static void playHighScore() {
        playSound("highScore.wav");
    }

    public static void playHold() {
        playSound("hold.wav");
    }

    public static void playLevelUp() {
        playSound("levelUp.wav");
    }

    public static void playMove() {
        playSound("move.wav");
    }

    public static void playOk() {
        playSound("ok.wav");
    }

    public static void playPause() {
        playSound("pause.wav");
    }

    public static void playResults() {
        playSound("results.wav");
    }

    public static void playRotate() {
        playSound("rotate.wav");
    }

    public static void playSingle() {
        playSound("single.wav");
    }

    public static void playSoftDrop() {
        playSound("softDrop.wav");
    }

    public static void playTetris() {
        playSound("tetris.wav");
    }

    public static void playTriple() {
        playSound("triple.wav");
    }

    public static void playTspin() {
        playSound("Tspin.wav");
    }

    private static void playSound(String clipName) {
        myAudioClip audioClip = clips.get(clipName);
        if (audioClip == null) {
            return;
        }
        // create new thread to play clip

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String path = soundPath + clipName;
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem
                            .getAudioInputStream(SoundsPlayer.class.getResourceAsStream(path)));
                    clip.start();

                    // add clip to list of playing clips
                    synchronized (playingClips) {
                        playingClips.add(audioClip);
                    }

                    // add listener to remove clip from list when it finishes playing
                    clip.addLineListener(new LineListener() {
                        @Override
                        public void update(LineEvent event) {
                            if (event.getType() == LineEvent.Type.STOP) {
                                clip.close();
                                synchronized (playingClips) {
                                    playingClips.remove(audioClip);
                                }
                            }
                        }
                    });

                } catch (IOException | javax.sound.sampled.UnsupportedAudioFileException
                        | javax.sound.sampled.LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void playGameMusic() {
        try {
            // carga el archivo de audio
            String path = soundPath + "mainTheme.wav";
            InputStream audioSrc = SoundsPlayer.class.getResourceAsStream(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);

            // crea el clip de audio
            mainMusic = AudioSystem.getClip();
            mainMusic.open(audioInputStream);

            // control de ganancia
            gainControl = (FloatControl) mainMusic.getControl(FloatControl.Type.MASTER_GAIN);
            minimum = gainControl.getMinimum();

            // configura el loop del clip de audio
            mainMusic.setLoopPoints(0, -1); // -1 indica que se repita indefinidamente
            mainMusic.loop(Clip.LOOP_CONTINUOUSLY);
            // establece el valor inicial de ganancia
            setGain(0.78f);
            // inicia la reproducción del clip de audio
            mainMusic.start();
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
    public static void toggleMuteMain() {

        if (muted) {
            // si el audio estaba muteado, restaura el valor de ganancia previo
            gainControl.setValue(previousGain);
        } else {
            // si el audio estaba sonando, guarda el valor de ganancia actual y lo pone en
            // mute
            previousGain = gainControl.getValue();
            gainControl.setValue(minimum);
        }
        muted = !muted;
    }

    /** Detiene la musica */
    public static void stopMain() {
        if (mainMusic == null)
            return;
        mainMusic.stop();
        mainMusic = null;
    }

    /** reproduce la musica de nuevo */
    public static void resumeMain() {
        if (mainMusic == null)
            return;
        mainMusic.start();
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

}
