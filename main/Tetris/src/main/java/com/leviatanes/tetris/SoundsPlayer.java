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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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

    private static Map<String, Clip> clips = new HashMap<>();

    private static List<Clip> playingClips = new ArrayList<>();

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    /** True si esta ensordecido */
    private static boolean mainMuted = true;
    /** clip de musica principal */
    private static Clip mainMusic = null;
    /** control de ganancia para la musica principal */
    private static FloatControl mainControl = null;
    /** clip de musica del menu */
    private static Clip menuMusic = null;
    /** control de ganancia para la musica del menu */
    private static FloatControl menuControl = null;
    /** ganancia de la musica */
    private static float musicVol = 0f;

    /** clip de sonido */
    private static Clip sfx = null;
    /** control de ganancia para el sonido */
    private static float sfxVol = 0f;
    /** control de ganancia para el sonido */
    private static FloatControl sfxControl = null;
    /** constante de la ruta de los audios */
    private static final String soundPath = "/com/leviatanes/music/";

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

        try {
            InputStream audioSrc = SoundsPlayer.class.getResourceAsStream(soundPath + "mainTheme.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
			// crea el clip de audio
			mainMusic = AudioSystem.getClip();
			mainMusic.open(audioInputStream);
            mainMusic.loop(Clip.LOOP_CONTINUOUSLY);
            mainControl = (FloatControl) mainMusic.getControl(FloatControl.Type.MASTER_GAIN);
            mainMusic.setFramePosition(0);

            audioSrc = SoundsPlayer.class.getResourceAsStream(soundPath + "menu.wav");
            audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
            // crea el clip de audio
            menuMusic = AudioSystem.getClip();
            menuMusic.open(audioInputStream);
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
            menuControl = (FloatControl) menuMusic.getControl(FloatControl.Type.MASTER_GAIN);
            menuMusic.setFramePosition(0);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

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
            sfx = AudioSystem.getClip();
            sfx.open(audioInputStream);
            sfxControl = (FloatControl) sfx.getControl(FloatControl.Type.MASTER_GAIN);
            sfxControl.setValue(sfxVol);
            sfx.setFramePosition(0);
            // Agregar el objeto AudioClip al mapa de clips
            clips.put(sound, sfx);

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
     * @pparam vloment 0f - 1f
     */
    public static void setSfxVol(float volume){
        // se asegura que este dentro de los valores deseados
        volume = Math.max(0, Math.min(1, volume));
        // se hace la conversion
        sfxVol = sfxControl.getMinimum() + (sfxControl.getMaximum() - sfxControl.getMinimum()) * volume;
        for(Clip clip : clips.values()){
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(sfxVol);
        }
        // se reproduce un sonido para probar el volumen
        playOk();
    }   

    private static void playSound(String clipName) {
        Clip audioClip = clips.get(clipName);
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
                    clip.open(AudioSystem.getAudioInputStream(SoundsPlayer.class.getResourceAsStream(path)));
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
        menuMusic.stop();
        mainMusic.stop();
        mainMusic.setFramePosition(0);
        try {
            // configura el loop del clip de audio
            mainMusic.setLoopPoints(0, -1); // -1 indica que se repita indefinidamente
            mainMusic.loop(Clip.LOOP_CONTINUOUSLY);
            // inicia la reproducción del clip de audio
            mainMusic.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir el archivo de audio: " + e.getMessage());
        }
    }

    public static void playMenuMusic(){
        mainMusic.stop();
        menuMusic.stop();
        menuMusic.setFramePosition(0);
        try {
            // configura el loop del clip de audio
            menuMusic.setLoopPoints(0, -1); // -1 indica que se repita indefinidamente
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
            // inicia la reproducción del clip de audio
            menuMusic.start();
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
    public static void setMusicVol(float gain) {
        musicVol = Math.max(0, Math.min(1, gain));
        musicVol = mainControl.getMinimum() + (mainControl.getMaximum() - mainControl.getMinimum()) * musicVol;
        setMusicGain();        
    }

    /**
     * set de la ganancia de la musica
     */
    public  static void setMusicGain() {
        if (mainControl != null) {
            // asegurarse de que la ganancia no sea menor que 0 o mayor que 1
            mainControl.setValue(musicVol);
        }
        if(menuControl != null){
            menuControl.setValue(musicVol);
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
        if (mainControl != null) {
            float maxGain = mainControl.getMaximum();
            float minGain = mainControl.getMinimum();
            float range = maxGain - minGain;
            float currentGain = mainControl.getValue();
            return (currentGain - minGain) / range;
        } else {
            return 0f;
        }
    }

    /**
     * Activa o desactiva la musica
     */
    public static void toggleMuteMain() {
        if (mainMusic == null)
            return;
        mainMuted = mainMusic.isRunning();
        if (mainMuted) {
            mainMusic.start();
        } else {
            mainMusic.stop();
        }
        mainMuted = !mainMuted;
    }

    private static float gain;

    /** Detiene la musica */
    public static void stopMain() {
        if (mainMusic == null)
            return;
        mainMusic.stop();
        mainMuted = true;
    }

    /** reproduce la musica de nuevo */
    public static void resumeMain() {
        if (mainMusic == null)
            return;
        mainMusic.start();
        mainMuted = false;
    }

    /** regresa el clip mainMusic */
    public static Clip getMainMusic() {
        return mainMusic;
    }

    /**
     * baja el volumen de la musica lentamente
     * hasta dejarla en 0
     */
    public static void fadeOutMain() {
        if (mainMusic == null)
            return;
        new Thread(new Runnable() {
            @Override
            public void run() {

                gain = getGain();
                while (gain > 0.2) {
                    gain -= 0.01f;
                    mainControl.setValue((mainControl.getMinimum() + (mainControl.getMaximum() - mainControl.getMinimum()) * gain));
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopMain();
            }
        }).start();
    }

}
