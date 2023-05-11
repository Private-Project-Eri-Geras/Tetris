package com.leviatanes.tetris;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class SoundsPlayer {
    /** clip de musica principal */
    private static Clip mainMusic = null;
    /** control de ganancia de la musica */
    private static FloatControl gainControl = null;
    /** bandera de musica silenciada */
    private static boolean muted = false;
    /** ganancia anterior */
    private static float previousGain = 0f;
    /** private static minimum */
    private static float minimum = 0f;
    /** path de la carpeta de sonidos */
    private static String soundsPath = "/com/leviatanes/tetris/tetrisGame/game/music/";
    private static List<Clip> soundClips = new ArrayList<>();
    private static Map<String, AudioInputStream> soundMap = new HashMap<>();
    private static List<Thread> hilos = new ArrayList<>();
    private static AudioInputStream soundStream = null;
    private static Clip clip = null;
    private static Thread hilo = null;
    private static int clipIndex = 0;

    static {
        // Inicializamos la lista de clips con 20 elementos
        for (int i = 0; i < 26; i++) {
            try {
                Clip clip = AudioSystem.getClip();
                soundClips.add(clip);
                hilos.add(new Thread());
            } catch (Exception e) {
                System.out.println("Error al crear el clip: " + e.getMessage());
            }
        }
        String asound = null;
        try {
            for (String sound : Arrays.asList("allClear.wav", "fall.wav", "hardDrop.wav", "highestScore.wav",
                    "highScore.wav", "hold.wav", "levelUp.wav", "move.wav", "ok.wav", "pause.wav", "results.wav",
                    "rotate.wav", "single.wav", "softDrop.wav", "tetris.wav", "triple.wav", "Tspin.wav")) {
                asound = sound;
                URL audioSrc = SoundsPlayer.class.getResource(soundsPath + sound);
                AudioInputStream soundStream = AudioSystem.getAudioInputStream(audioSrc);
                soundStream.mark(Integer.MAX_VALUE); // Marcar el stream para poder resetearlo más tarde
                soundMap.put(sound, soundStream);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el sonido " + asound + ": " + e.getMessage());
        }
    }

    private static void playSound(String sound) {
        clipIndex = (clipIndex + 1) % soundClips.size();
        if (soundClips.get(clipIndex).isActive()) {
            soundClips.get(clipIndex).close();
            return;
        }
        soundClips.get(clipIndex).close();
        clip = soundClips.get(clipIndex);
        clip.close();
        soundStream = soundMap.get(sound);
        hilo = hilos.get(clipIndex);
        if (hilo.isAlive()) {
            hilo.interrupt();
            clip.close();
        }
        hilo = new Thread(() -> {
            if (soundStream != null) {
                try {
                    soundStream.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    clip.open(soundStream);
                    clip.start();
                } catch (LineUnavailableException | IOException e) {
                    System.out.println("Error al cargar el clip " + sound + ": " + e.getMessage());
                }
            }

        });
        hilo.start();
    }

    public static void playGameMusic() {
        try {
            // carga el archivo de audio
            String path = soundsPath + "mainTheme.wav";
            InputStream audioSrc = SoundsPlayer.class.getResourceAsStream(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);

            // crea el clip de audio
            Clip mainMusic = AudioSystem.getClip();
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
        mainMusic.close();
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

    /** reproduce el sonido de mover */
    public static void playMove() {
        playSound("move.wav");
    }

    /** reproduce el sonido de mover para abajo */
    public static void playSoftDrop() {
        playSound("softDrop.wav");
    }

    /** reproduce el sonido de girar */
    public static void playRotate() {
        playSound("rotate.wav");
    }

    /** reproduce el sonido de HardDrop */
    public static void playHardDrop() {
        playSound("hardDrop.wav");
    }

    /** single */
    public static void playSingle() {
        playSound("single.wav");
    }

    /** double */
    public static void playDouble() {
        // playSound("double.wav");
    }

    /** triple */
    public static void playTriple() {
        playSound("triple.wav");
    }

    /** tetris */
    public static void playTetris() {
        playSound("tetris.wav");
    }

    /** tspin */
    public static void playTspin() {
        playSound("Tspin.wav");
    }

    /** Pausa */
    public static void playPause() {
        playSound("pause.wav");
    }

    /** Settle */
    public static void playFall() {
        playSound("fall.wav");
    }

    /** Hold */
    public static void playHold() {
        playSound("hold.wav");
    }

    /** Leveling up */
    public static void playLevelUp() {
        playSound("levelUp.wav");
    }

    /** all clear */
    public static void playAllClear() {
        playSound("allClear.wav");
    }

}
