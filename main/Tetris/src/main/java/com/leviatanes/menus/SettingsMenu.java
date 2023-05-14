package com.leviatanes.menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingsMenu extends JFrame {    
    // Background 
    // TODO: Add background

    // Resolution
    private JLabel resolutionLabel;
    private JComboBox<String> resolutionComboBox;

    // Music and volume
    private JLabel musicCheckLabel;
    private JCheckBox musicCheckBox;
    private JLabel musicVolumeLabel;
    private JSlider musicVolumeSlider;

    // Sound effects and volume
    private JLabel soundEffectsCheckLabel;
    private JCheckBox soundEffectsCheckBox;
    private JLabel soundEffectsVolumeLabel;
    private JSlider soundEffectsVolumeSlider;

    // Controls button
    private JButton controlsButton;

    // Back to main menu
    private JButton backButton;

    // Panels
    private JPanel panelResolution;
    private JPanel panelMusic;
    private JPanel panelSoundEffects;
    private JPanel panelControls;
    private JPanel panelBack;

    private void initComponents(int[][] resolution, int multiplier){
        // Window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Settings");

        // TODO: Remove later
        setSize(90*8, 80*8);
        setLocationRelativeTo(null);

        // PanelResolution
        panelResolution = new JPanel();
        panelResolution.setLayout(null);
        panelResolution.setPreferredSize(new Dimension(80*multiplier, 18*multiplier));
        panelResolution.setBounds(0, 0, 80*multiplier, 18*multiplier);

        resolutionLabel = new JLabel("Resolution");
        resolutionLabel.setBounds(2*multiplier, 2*multiplier, 38*multiplier, 14*multiplier);
        panelResolution.add(resolutionLabel);

        resolutionComboBox = new JComboBox<String>();
        String tempRes = "";
        for(int i = 0; i < multiplier; i++){
            tempRes = Integer.toString(resolution[i][0]) + " x " + Integer.toString(resolution[i][1]);
            resolutionComboBox.addItem(tempRes);
        }
        resolutionComboBox.setBounds(40*multiplier, 2*multiplier, 38*multiplier, 14*multiplier);
    }

    public SettingsMenu(int[][] resolution, int multiplier){
        initComponents(resolution, multiplier);
    }

    public static void main(String[] args) {
        int[][] resolution = {{640, 480}, {800, 600}, {1024, 768}, {1280, 720}, {1280, 1024}, {1366, 768}, {1600, 900}, {1920, 1080}};
        int multiplier = 8;
        SettingsMenu settingsMenu = new SettingsMenu(resolution, multiplier);
        settingsMenu.setVisible(true);
    }

}