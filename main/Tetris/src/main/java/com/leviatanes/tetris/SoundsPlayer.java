package com.leviatanes.tetris;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.BufferedInputStream;

/**
 * Clase que se encarga de reproducir los sonidos del juego.
 * Busca la optimizacion en tiempo de ejecucion, por lo que
 * los sonidos se cargan en memoria al inicio del programa
 * y se reproducen desde ahi.
 * 
 * @author Eriarer (Abraham)
 */
public class SoundsPlayer {

    private static Map<String, SfxHandeler> clips = new HashMap<>();

    /** slider de la musica */
    private static float mymusicVol = 0f;
    /** volumen de la musica */
    private static float musicVol = 0f;
    /** slider del sfx */
    private static float mysfxVol = 0f;
    /** volumen del sfx */
    private static float sfxVol = 0f;
    /** constante de la ruta de los audios */
    private static final String soundPath = "/com/leviatanes/music/";

    /** constructor */
    private static boolean construido = false;
    /* Mute */
    private static boolean muted = false;
    /* Is main playing */
    private static boolean mainPlaying = false;
    /* Is menu playing */
    private static boolean menuPlaying = true;
    /* ganancia fadeOut */
    private static float gain = 0f;
    /** garbage colector */
    private static Thread garbage = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.gc();
            }
        }
    });

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
        loadClip("mainTheme.wav");
        stopMain();
        clips.get("mainTheme.wav").loop();
        stopMain();
        loadClip("menu.wav");
        stopMusic();
        clips.get("menu.wav").loop();
        stopMusic();

        // pendiente
        setMysfxVol(0.5f);
        setMymusicVol(0.3f);
        stopMusic();
        construido = true;
        garbage.start();
    }

    private static void loadClip(String sound) {
        try {
            SfxHandeler soundEffect = null;
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
            soundEffect = new SfxHandeler(clip, 1, audioInputStream);
            // Agregar el objeto AudioClip al mapa de clips
            clips.put(sound, soundEffect);

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

    /**
     * Asigna el volumen de la musica principal.
     * 
     * @pparam vloment 0f - 1f
     */
    public static void setMysfxVol(float volume) {
        mysfxVol = volume;
        sfxVol = (float) Math.log10(1 + 9 * volume);
        sfxVol = (float) Math.log10(1 + 9 * sfxVol);
        String name = "";
        for (int i = 0; i < clips.size(); i++) {
            name = clips.keySet().toArray()[i].toString();
            if (name == "mainTheme.wav" || name == "menu.wav")
                continue;
            clips.get(name).setVolume(sfxVol);
        }
        // se hace la conversion
        // se reproduce un sonido para probar el volumen
        if (construido)
            playOk();
    }

    /** retorna el volumen */
    public static float getFSfxVolume() {
        return mysfxVol;
    }

    /** retorna el volumen */
    public static int getISfcVolume() {
        return (int) (mysfxVol * 100);
    }

    private static void playSound(String clipName) {
        if (muted)
            return;
        clips.get(clipName).play(soundPath + clipName);
    }

    public static void playGameMusic() {
        gain = 0f;
        if (muted)
            return;
        if (clips.get("mainTheme.wav").isRunning())
            return;
        clips.get("mainTheme.wav").setVolume(musicVol);
        while (true) {
            clips.get("mainTheme.wav").play();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (clips.get("mainTheme.wav").isRunning())
                break;
        }
        mainPlaying = true;
        menuPlaying = false;
    }

    public static void playMenuMusic() {
        if (muted)
            return;
        if (clips.get("menu.wav").isRunning())
            return;
        clips.get("mainTheme.wav").setVolume(musicVol);
        clips.get("menu.wav").play();
        menuPlaying = true;
        mainPlaying = false;
    }

    public static boolean isMainMusicPlaying() {
        return clips.get("mainTheme.wav").isRunning();
    }

    public static boolean isMenuMusicPlaying() {
        return clips.get("menu.wav").isRunning();
    }

    public static void stopMusic() {
        clips.get("mainTheme.wav").stop();
        clips.get("menu.wav").stop();
    }

    /**
     * set de la ganancia
     * entre 0 y 1
     * 0 es silenciado
     * 1 es el maximo
     * 
     * @param gain
     */
    public static void setMymusicVol(float vol) {
        mymusicVol = vol;
        musicVol = (float) Math.log10(1 + 9 * vol);
        musicVol = (float) Math.log10(1 + 9 * musicVol);
        clips.get("mainTheme.wav").setVolume(musicVol);
        clips.get("menu.wav").setVolume(musicVol);
    }

    /** retorna el volumen */
    public static float getFMusicVolume() {
        return mymusicVol;
    }

    /** retrona el volumen */
    public static int getIMusicVolume() {
        return (int) (mymusicVol * 100);
    }

    /** Detiene la musica */
    public static void stopMain() {
        clips.get("mainTheme.wav").stop();
    }

    /** reproduce la musica de nuevo */
    public static void resumeMain() {
        while (true) {
            playGameMusic();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isMainMusicPlaying())
                break;
        }
    }

    /* activa o desactiva TODOS los sonidos */
    public static void toggleMute() {
        if (muted) {
            muted = !muted;
            reumeMusic();
        } else {
            muted = !muted;
            stopMusic();
        }
    }

    /* resumir la cancion que se estaba tocando */
    private static void reumeMusic() {
        if (mainPlaying)
            playGameMusic();
        else if (menuPlaying)
            playMenuMusic();
    }

    /**
     * baja el volumen de la musica lentamente
     * hasta dejarla en 0
     */
    public static void fadeOutMain() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                gain = clips.get("mainTheme.wav").getVolume();
                while (gain > 0.43f) {
                    gain -= 0.005f;
                    clips.get("mainTheme.wav").setVolume(gain);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                clips.get("mainTheme.wav").stop();
            }
        }).start();
    }

}