package com.leviatanes.menus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;

public class SettingsReader {
    // Constants screen res
    private static final int BASE_WIDTH = 90;
    private static final int BASE_HEIGHT = 80;
    private static final int MATRIX_ROWS = 28;
    private static final int MATRIX_COLUMNS = 2;
    // Get screen resolution
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int screenHeight = (int) screenSize.getHeight();

    // Create matrix with 28 different resolutions
    public static int[][] resolution;

    // variables
    private static int multiplier = 1;
    private static int musicv = 0;
    private static int sfxv = 0;
    private static int rotate = 0;
    private static int counterRotate = 0;
    private static int left = 0;
    private static int rigth = 0;
    private static int down = 0;
    private static int softDrop = 0;
    private static int hardDrop = 0;
    private static int pause = 0;
    private static int hold = 0;
    private static int mute = 0;

    static {
        String fileName = "/com/leviatanes/menus/Settings.txt";
        File file = new File(SettingsReader.class.getResource(fileName).getFile());
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                switch (parts[0]) {
                    case "multiplier":
                        multiplier = Integer.parseInt(parts[1]);
                        break;
                    case "musicv":
                        musicv = Integer.parseInt(parts[1]);
                        break;
                    case "sfxv":
                        sfxv = Integer.parseInt(parts[1]);
                        break;
                    case "rotate":
                        rotate = Integer.parseInt(parts[1]);
                        break;
                    case "counterRotate":
                        counterRotate = Integer.parseInt(parts[1]);
                        break;
                    case "left":
                        left = Integer.parseInt(parts[1]);
                        break;
                    case "rigth":
                        rigth = Integer.parseInt(parts[1]);
                        break;
                    case "down":
                        down = Integer.parseInt(parts[1]);
                        break;
                    case "softDrop":
                        softDrop = Integer.parseInt(parts[1]);
                        break;
                    case "hardDrop":
                        hardDrop = Integer.parseInt(parts[1]);
                        break;
                    case "pause":
                        pause = Integer.parseInt(parts[1]);
                        break;
                    case "hold":
                        hold = Integer.parseInt(parts[1]);
                        break;
                    case "mute":
                        mute = Integer.parseInt(parts[1]);
                        break;
                }
                i++;
            }
            bufferedReader.close();
            fileReader.close();
            if (i == 0) {
                defaultSettings();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuracion");
            e.printStackTrace();
        }
    }

    public static void defaultSettings() {
        String fileName = "/com/leviatanes/menus/Settings.txt";
        File file = new File(SettingsReader.class.getResource(fileName).getFile());
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de configuracion");
            e.printStackTrace();
        } finally {
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                getMaxResolution();
                bufferedWriter.write("multiplier " + multiplier + "\n");
                bufferedWriter.write("musicv 100\n");
                musicv = 100;
                bufferedWriter.write("sfxv 100\n");
                sfxv = 100;
                bufferedWriter.write("rotate 87\n");
                rotate = 87;
                bufferedWriter.write("counterRotate 69\n");
                counterRotate = 69;
                bufferedWriter.write("left 65\n");
                left = 65;
                bufferedWriter.write("rigth 68\n");
                rigth = 68;
                bufferedWriter.write("down 83\n");
                down = 83;
                bufferedWriter.write("softDrop 16\n");
                softDrop = 16;
                bufferedWriter.write("hardDrop 32\n");
                hardDrop = 32;
                bufferedWriter.write("pause 80\n");
                pause = 80;
                bufferedWriter.write("hold 17\n");
                hold = 17;
                bufferedWriter.write("mute 77\n");
                mute = 77;
                // mandatorio
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo de configuracion");
                e.printStackTrace();
            }
        }

    }

    private static void getMaxResolution() {
        resolution = new int[MATRIX_ROWS][MATRIX_COLUMNS];
        resolution[0][0] = BASE_WIDTH;
        resolution[0][1] = BASE_HEIGHT;
        for (multiplier = 1; multiplier < MATRIX_ROWS && resolution[multiplier - 1][1] < screenHeight; multiplier++) {
            resolution[multiplier][0] = resolution[0][0] * (multiplier + 1);
            resolution[multiplier][1] = resolution[0][1] * (multiplier + 1);
        }
        multiplier -= 2;
    }

    public static int getMultiplier() {
        return multiplier;
    }

    public static float getMusicv() {
        return ((float) musicv) / 100;
    }

    public static float getSfxv() {
        return ((float) sfxv) / 100;
    }

    public static int getRotate() {
        return rotate;
    }

    public static int getCounterRotate() {
        return counterRotate;
    }

    public static int getLeft() {
        return left;
    }

    public static int getRigth() {
        return rigth;
    }

    public static int getDown() {
        return down;
    }

    public static int getSoftDrop() {
        return softDrop;
    }

    public static int getHardDrop() {
        return hardDrop;
    }

    public static int getPause() {
        return pause;
    }

    public static int getHold() {
        return hold;
    }

    public static int getMute() {
        return mute;
    }

    public static void setMultiplier(int multiplier) {
        SettingsReader.multiplier = multiplier;
        searchReplace("multiplier", multiplier);
    }

    public static void setMusicv(int musicv) {
        SettingsReader.musicv = musicv;
        searchReplace("musicv", musicv);
    }

    public static void setSfxv(int sfxv) {
        SettingsReader.sfxv = sfxv;
        searchReplace("sfxv", sfxv);
    }

    public static void setRotate(int rotate) {
        SettingsReader.rotate = rotate;
        searchReplace("rotate", rotate);
    }

    public static void setCounterRotate(int counterRotate) {
        SettingsReader.counterRotate = counterRotate;
        searchReplace("counterRotate", counterRotate);
    }

    public static void setLeft(int left) {
        SettingsReader.left = left;
        searchReplace("left", left);
    }

    public static void setRigth(int rigth) {
        SettingsReader.rigth = rigth;
        searchReplace("rigth", rigth);
    }

    public static void setDown(int down) {
        SettingsReader.down = down;
        searchReplace("down", down);
    }

    public static void setSoftDrop(int softDrop) {
        SettingsReader.softDrop = softDrop;
        searchReplace("softDrop", softDrop);
    }

    public static void setHardDrop(int hardDrop) {
        SettingsReader.hardDrop = hardDrop;
        searchReplace("hardDrop", hardDrop);
    }

    public static void setPause(int pause) {
        SettingsReader.pause = pause;
        searchReplace("pause", pause);
    }

    public static void setHold(int hold) {
        SettingsReader.hold = hold;
        searchReplace("hold", hold);
    }

    public static void setMute(int mute) {
        SettingsReader.mute = mute;
        searchReplace("mute", mute);
    }

    private static void searchReplace(String search, int value) {
        String fileName = "/com/leviatanes/menus/Settings.txt";
        File file = new File(SettingsReader.class.getResource(fileName).getFile());
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(search)) {
                    lines.set(i, search + " " + value);
                }
            }
            bufferedReader.close();
            fileReader.close();

            try {
                file.delete();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de configuracion");
                e.printStackTrace();
            } finally {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (String string : lines) {
                    bufferedWriter.write(string + "\n");
                }
                bufferedWriter.close();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}