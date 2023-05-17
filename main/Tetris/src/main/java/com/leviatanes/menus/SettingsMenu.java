package com.leviatanes.menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.leviatanes.tetris.Main;

import com.leviatanes.tetris.SoundsPlayer;

public class SettingsMenu extends JPanel {
    private Main main;

    // ============ [COMPONENTS] ============ //
    // {Main Menu}
    private JLabel titleLabel;
    private JLabel resLabel;
    private JComboBox<String> resComboBox;
    private JLabel musicVolumeLabel;
    private JSlider musicVolumeSlider;
    private JLabel soundEffectsVolumeLabel;
    private JSlider soundEffectsVolumeSlider;
    private JButton controlsButton;
    private JButton backButton;
    // {Controls Menu}
    private JLabel controlsTitleLabel;
    private JButton controlsbackButton;

    private int multiplier;
    private boolean isToggleMenus;

    public SettingsMenu(int whidth, int height, int[][] resolution, int multiplier, Main main) {
        this.setLayout(null);
        this.setBounds(0, 0, whidth, height);
        this.main = main;
        this.multiplier = multiplier;
        this.isToggleMenus = false;
        initComponents(resolution);
        this.revalidate();
        this.repaint();
        this.setVisible(true);

    }

    public void initComponents(int[][] resolution) {
        initSettings(resolution);
        initControls();
    }

    private void initSettings(int[][] resolution) {
        // ============ [TITLE LABEL] ============ //
        titleLabel = new JLabel("SETTINGS");
        titleLabel.setBounds(0, 6 * multiplier, 90 * multiplier, 10 * multiplier);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 5 * multiplier));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        // ============ [RESOLUTION] ============ //
        resLabel = new JLabel("RESOLUTION:");
        resLabel.setBounds(6 * multiplier, 18 * multiplier, 36 * multiplier, 6 * multiplier);
        resLabel.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        resLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(resLabel);

        resComboBox = new JComboBox<String>();
        resComboBox.setBounds(42 * multiplier, 18 * multiplier, 36 * multiplier, 6 * multiplier);
        resComboBox.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        this.add(resComboBox);

        for (int i = 0; i < resolution.length; i++) {
            if(resolution[i][0] == 0)
                break;
            resComboBox.addItem(resolution[i][0] + "x" + resolution[i][1]);
        }

        resComboBox.setSelectedIndex(SettingsReader.getMultiplier() - 1);

        resComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsReader.setMultiplier(resComboBox.getSelectedIndex() + 1);
                // TODO: cambiar la resolucion
                main.setResize();
            }
        });

        // ============ [MUSIC VOLUME] ============ //
        musicVolumeLabel = new JLabel("MUSIC VOLUME:");
        musicVolumeLabel.setBounds(6 * multiplier, 32 * multiplier, 36 * multiplier, 6 * multiplier);
        musicVolumeLabel.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        musicVolumeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(musicVolumeLabel);

        musicVolumeSlider = new JSlider();
        musicVolumeSlider.setBounds(42 * multiplier, 32 * multiplier, 36 * multiplier, 6 * multiplier);
        musicVolumeSlider.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        musicVolumeSlider.setMaximum(100);
        musicVolumeSlider.setMinimum(0);
        musicVolumeSlider.setValue(SoundsPlayer.getIMusicVolume());

        musicVolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!musicVolumeSlider.getValueIsAdjusting()) {
                    SoundsPlayer.setMymusicVol(((float) musicVolumeSlider.getValue()) / 100f);
                    SettingsReader.setMusicv(musicVolumeSlider.getValue());
                }
            }
        });

        this.add(musicVolumeSlider);

        // ============ [SOUND EFFECTS VOLUME] ============ //
        soundEffectsVolumeLabel = new JLabel("SFX VOLUME:");
        soundEffectsVolumeLabel.setBounds(6 * multiplier, 42 * multiplier, 36 * multiplier, 6 * multiplier);
        soundEffectsVolumeLabel.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        soundEffectsVolumeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(soundEffectsVolumeLabel);

        soundEffectsVolumeSlider = new JSlider();
        soundEffectsVolumeSlider.setBounds(42 * multiplier, 42 * multiplier, 36 * multiplier, 6 * multiplier);
        soundEffectsVolumeSlider.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        soundEffectsVolumeSlider.setMaximum(100);
        soundEffectsVolumeSlider.setMinimum(0);
        soundEffectsVolumeSlider.setValue(SoundsPlayer.getISfcVolume());

        soundEffectsVolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!soundEffectsVolumeSlider.getValueIsAdjusting()) {
                    SoundsPlayer.setMysfxVol(((float) soundEffectsVolumeSlider.getValue()) / 100f);
                    SettingsReader.setSfxv(soundEffectsVolumeSlider.getValue());
                }
            }
        });

        this.add(soundEffectsVolumeSlider);

        // ============ [CONTROLS BUTTON] ============ //
        controlsButton = new JButton("CONTROLS");
        controlsButton.setBounds(27 * multiplier, 52 * multiplier, 36 * multiplier, 6 * multiplier);
        controlsButton.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        controlsButton.setHorizontalAlignment(SwingConstants.CENTER);

        controlsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleMenus();
            }
        });

        controlsButton.setForeground(Color.BLACK);
        controlsButton.setBackground(new Color(200, 200, 200));

        controlsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                controlsButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                controlsButton.setBackground(new Color(200, 200, 200));
            }
        });

        this.add(controlsButton);

        // ============ [BACK BUTTON] ============ //
        backButton = new JButton("BACK");
        backButton.setBounds(27 * multiplier, 64 * multiplier, 36 * multiplier, 6 * multiplier);
        backButton.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.MenuInicio();
            }
        });

        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color(200, 200, 200));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(200, 200, 200));
            }
        });

        this.add(backButton);
    }

    private void initControls() {
        // ============ [CONTROLS TITLE LABEL] ============ //
        controlsTitleLabel = new JLabel("CONTROLS");
        controlsTitleLabel.setBounds(0, 6 * multiplier, 90 * multiplier, 10 * multiplier);
        controlsTitleLabel.setFont(new Font("Arial", Font.BOLD, 5 * multiplier));
        controlsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // ============ [CONTROLS BACK BUTTON] ============ //
        controlsbackButton = new JButton("BACK TO SETTINGS");
        controlsbackButton.setBounds(27 * multiplier, 64 * multiplier, 36 * multiplier, 6 * multiplier);
        controlsbackButton.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        controlsbackButton.setHorizontalAlignment(SwingConstants.CENTER);

        controlsbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleMenus();
            }
        });

        controlsbackButton.setForeground(Color.BLACK);
        controlsbackButton.setBackground(new Color(200, 200, 200));

        controlsbackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                controlsbackButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                controlsbackButton.setBackground(new Color(200, 200, 200));
            }
        });

    }

    private void toggleMenus() {
        if (isToggleMenus) {
            remove(controlsTitleLabel);
            remove(controlsbackButton);

            add(titleLabel);
            add(resLabel);
            add(resComboBox);
            add(musicVolumeLabel);
            add(musicVolumeSlider);
            add(soundEffectsVolumeLabel);
            add(soundEffectsVolumeSlider);
            add(controlsButton);
            add(backButton);
        } else {
            add(controlsTitleLabel);
            add(controlsbackButton);

            remove(titleLabel);
            remove(resLabel);
            remove(resComboBox);
            remove(musicVolumeLabel);
            remove(musicVolumeSlider);
            remove(soundEffectsVolumeLabel);
            remove(soundEffectsVolumeSlider);
            remove(controlsButton);
            remove(backButton);
        }
        isToggleMenus = !isToggleMenus;
        revalidate();
        repaint();
    }
}
