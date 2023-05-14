package com.leviatanes.menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingsMenu extends JFrame {
    // Background

    // Title
    private JLabel titleLabel;

    // Resolution
    private JLabel resolutionLabel;
    private JComboBox<String> resolutionComboBox;

    // Music volume
    private JLabel musicVolumeLabel;
    private JSlider musicVolumeSlider;

    // Sound effects volume
    private JLabel soundEffectsVolumeLabel;
    private JSlider soundEffectsVolumeSlider;

    // Controls button
    private JButton controlsButton;

    // Back to main menu
    private JButton backButton;

    // Panels
    private JPanel mainPanel;

    private JPanel panelResolution;
    private JPanel panelMusic;
    private JPanel panelSoundEffects;
    private JPanel panelControls;
    private JPanel panelBack;

    private int multiplier;

    private void initComponents(int[][] resolution, int multiplier) {
        // Multiplier
        this.multiplier = multiplier;

        // Window
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Settings");
        setSize(90 * multiplier, 80 * multiplier);
        setLocationRelativeTo(null);

        // ========[PANELES]========//
        // MainPanel
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel);

        // PanelResolution
        panelResolution = initPanel(6, 18, 78, 6);

        // PanelMusic
        panelMusic = initPanel(6, 32, 78, 6);

        // PanelSoundEffects
        panelSoundEffects = initPanel(6, 42, 78, 6);

        // PanelControls
        panelControls = initPanel(6, 54, 78, 6);

        // PanelBack
        panelBack = initPanel(6, 66, 78, 6);

        // ========[COMPONENTES]========//
        // Title
        titleLabel = initLabel(mainPanel, "Settings", 6, 6, 78, 6, 5, true);

        // Resolution
        resolutionLabel = initLabel(panelResolution, "Resolution:", 0, 0, 36, 6, 3, false);
        resolutionComboBox = initComboBox(panelResolution, 42, 0, 36, 6);

        for (int i = 0; i < resolution.length; i++) {
            resolutionComboBox.addItem(resolution[i][0] + "x" + resolution[i][1]);
        }
        resolutionComboBox.setSelectedIndex(7);

        // Music volume
        musicVolumeLabel = initLabel(panelMusic, "Music volume:", 0, 0, 36, 6, 3, false);
        musicVolumeSlider = initSlider(panelMusic, 42, 0, 36, 6);

        // Sound effects volume
        soundEffectsVolumeLabel = initLabel(panelSoundEffects, "Sound effects volume:", 0, 0, 36, 6, 3, false);
        soundEffectsVolumeSlider = initSlider(panelSoundEffects, 42, 0, 36, 6);

        // Controls button
        controlsButton = initButton(panelControls, "CONTROLS", 21, 0, 36, 6);

        // Back to main menu
        backButton = initButton(panelBack, "BACK", 21, 0, 36, 6);

        // Listeners

    }

    private JPanel initPanel(int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBounds(x * multiplier, y * multiplier, width * multiplier, height * multiplier);
        panel.setLayout(null);
        mainPanel.add(panel);
        return panel;
    }

    private JLabel initLabel(JPanel panel, String text, int x, int y, int w, int h, int fontSize, boolean isCenter) {
        JLabel label = new JLabel(text);
        label.setBounds(x * multiplier, y * multiplier, w * multiplier, h * multiplier);
        label.setFont(new Font("Arial", Font.BOLD, fontSize * multiplier));
        if (isCenter)
            label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        return label;
    }

    private <T> JComboBox<T> initComboBox(JPanel panel, int x, int y, int w, int h) {
        JComboBox<T> comboBox = new JComboBox<>();
        comboBox.setBounds(x * multiplier, y * multiplier, w * multiplier, h * multiplier);
        comboBox.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        panel.add(comboBox);
        return comboBox;
    }

    private JSlider initSlider(JPanel panel, int x, int y, int w, int h) {
        JSlider slider = new JSlider();
        slider.setBounds(x * multiplier, y * multiplier, w * multiplier, h * multiplier);
        slider.setMinimum(0);
        slider.setMaximum(100);
        panel.add(slider);
        return slider;
    }

    private JButton initButton(JPanel panle, String text, int x, int y, int w, int h) {
        JButton button = new JButton(text);
        button.setBounds(x * multiplier, y * multiplier, w * multiplier, h * multiplier);
        button.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        panle.add(button);
        return button;
    }

    public SettingsMenu(int[][] resolution, int multiplier) {
        initComponents(resolution, multiplier);
    }

    public static void main(String[] args) {
        int[][] resolution = { { 640, 480 }, { 800, 600 }, { 1024, 768 }, { 1280, 720 }, { 1280, 1024 }, { 1366, 768 },
                { 1600, 900 }, { 1920, 1080 } };
        int multiplier = 5;
        SettingsMenu settingsMenu = new SettingsMenu(resolution, multiplier);
        settingsMenu.setVisible(true);
    }

}