package com.leviatanes.menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private JLabel left;
    private JButton leftBtn;
    private JLabel right;
    private JButton rightBtn;
    private JLabel down;
    private JButton downBtn;
    private JLabel hold;
    private JButton holdBtn;
    private JLabel softDrop;
    private JButton softDropBtn;
    private JLabel hardDrop;
    private JButton hardDropBtn;
    private JLabel rotate;
    private JButton rotateBtn;
    private JLabel rotateLeft;
    private JButton rotateLeftBtn;
    private JLabel pause;
    private JButton pauseBtn;
    private JLabel mute;
    private JButton muteBtn;

    private JButton resetButton;

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

    public int keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(keyCode));
        return keyCode;
    }

    public void initComponents(int[][] resolution) {
        initSettings(resolution);
        initControls();

        resetButton = new JButton("DEFAULT");
        resetButton.setBounds(70 * multiplier, 68 * multiplier, 18 * multiplier, 6 * multiplier);
        resetButton.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsReader.defaultSettings();
                main.setResize(isToggleMenus);
            }
        });
        this.add(resetButton);
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
        resComboBox.setFocusable(false);
        resComboBox.setFocusTraversalKeysEnabled(false);
        resComboBox.setFocusCycleRoot(false);
        resComboBox.setRequestFocusEnabled(false);
        this.add(resComboBox);

        for (int i = 0; i < resolution.length; i++) {
            if (resolution[i][0] == 0)
                break;
            resComboBox.addItem(resolution[i][0] + "x" + resolution[i][1]);
        }

        resComboBox.setSelectedIndex(SettingsReader.getMultiplier() - 1);

        resComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsReader.setMultiplier(resComboBox.getSelectedIndex() + 1);
                main.setResize(false);
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
        controlsButton.setBounds(27 * multiplier, 56 * multiplier, 36 * multiplier, 6 * multiplier);
        controlsButton.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        controlsButton.setHorizontalAlignment(SwingConstants.CENTER);
        controlsButton.setFocusable(false);
        controlsButton.setFocusPainted(false);
        controlsButton.setRequestFocusEnabled(false);
        controlsButton.setRolloverEnabled(false);

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
        backButton.setBounds(27 * multiplier, 68 * multiplier, 36 * multiplier, 6 * multiplier);
        backButton.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setFocusable(false);
        backButton.setFocusPainted(false);
        backButton.setRequestFocusEnabled(false);
        backButton.setRolloverEnabled(false);

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
        controlsbackButton.setBounds(27 * multiplier, 68 * multiplier, 36 * multiplier, 6 * multiplier);
        controlsbackButton.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        controlsbackButton.setHorizontalAlignment(SwingConstants.CENTER);
        controlsbackButton.setFocusable(false);
        controlsbackButton.setFocusPainted(false);
        controlsbackButton.setRequestFocusEnabled(false);
        controlsbackButton.setRolloverEnabled(false);

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

        // ============ [LEFT] ============ //
        left = new JLabel("LEFT");
        left.setBounds(20 * multiplier, 17 * multiplier, 30 * multiplier, 4 * multiplier);
        left.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        left.setHorizontalAlignment(SwingConstants.LEFT);

        leftBtn = new JButton((char) SettingsReader.getLeft() + "");
        leftBtn.setBounds(52 * multiplier, 17 * multiplier, 30 * multiplier, 4 * multiplier);
        leftBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        leftBtn.setHorizontalAlignment(SwingConstants.CENTER);
        leftBtn.setFocusable(false);
        leftBtn.setFocusPainted(false);
        leftBtn.setRequestFocusEnabled(false);
        leftBtn.setRolloverEnabled(false);
        leftBtn.setBackground(new Color(200, 200, 200));

        leftBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyPressed(null);

            }
        });

        leftBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [RIGTH] ============ //
        right = new JLabel("RIGTH");
        right.setBounds(20 * multiplier, 22 * multiplier, 30 * multiplier, 4 * multiplier);
        right.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        right.setHorizontalAlignment(SwingConstants.LEFT);

        rightBtn = new JButton((char) SettingsReader.getRigth() + "");
        rightBtn.setBounds(52 * multiplier, 22 * multiplier, 30 * multiplier, 4 * multiplier);
        rightBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        rightBtn.setHorizontalAlignment(SwingConstants.CENTER);
        rightBtn.setFocusPainted(false);
        rightBtn.setRequestFocusEnabled(false);
        rightBtn.setRolloverEnabled(false);
        rightBtn.setBackground(new Color(200, 200, 200));

        rightBtn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key Pressed: " + e.getKeyChar() + " " + e.getKeyCode());
                int keyCode = e.getKeyCode();
                int option = JOptionPane.showConfirmDialog(null, "Do you want to change the key to " + keyCode + " ?", "Change key",
                        JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    SettingsReader.setRigth(keyCode);
                    rightBtn.setText((char) keyCode + "");
                }
                rightBtn.setFocusable(false);
            }
        });

        rightBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightBtn.setFocusable(true);
                rightBtn.requestFocus();
            }
        });

        rightBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rightBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [DOWN] ============ //
        down = new JLabel("DOWN");
        down.setBounds(20 * multiplier, 27 * multiplier, 30 * multiplier, 4 * multiplier);
        down.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        down.setHorizontalAlignment(SwingConstants.LEFT);

        downBtn = new JButton((char) SettingsReader.getDown() + "");
        downBtn.setBounds(52 * multiplier, 27 * multiplier, 30 * multiplier, 4 * multiplier);
        downBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        downBtn.setHorizontalAlignment(SwingConstants.CENTER);
        downBtn.setFocusable(false);
        downBtn.setFocusPainted(false);
        downBtn.setRequestFocusEnabled(false);
        downBtn.setRolloverEnabled(false);
        downBtn.setBackground(new Color(200, 200, 200));

        downBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Change key down
            }
        });

        downBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                downBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                downBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [HOLD] ============ //
        hold = new JLabel("HOLD");
        hold.setBounds(20 * multiplier, 32 * multiplier, 30 * multiplier, 4 * multiplier);
        hold.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        hold.setHorizontalAlignment(SwingConstants.LEFT);

        holdBtn = new JButton((char) SettingsReader.getHold() + "");
        holdBtn.setBounds(52 * multiplier, 32 * multiplier, 30 * multiplier, 4 * multiplier);
        holdBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        holdBtn.setHorizontalAlignment(SwingConstants.CENTER);
        holdBtn.setFocusable(false);
        holdBtn.setFocusPainted(false);
        holdBtn.setRequestFocusEnabled(false);
        holdBtn.setRolloverEnabled(false);
        holdBtn.setBackground(new Color(200, 200, 200));

        holdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Change key hold
            }
        });

        holdBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                holdBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                holdBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [SOFT DROP] ============ //
        softDrop = new JLabel("SOFT DROP");
        softDrop.setBounds(20 * multiplier, 37 * multiplier, 30 * multiplier, 4 * multiplier);
        softDrop.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        softDrop.setHorizontalAlignment(SwingConstants.LEFT);

        softDropBtn = new JButton((char) SettingsReader.getSoftDrop() + "");
        softDropBtn.setBounds(52 * multiplier, 37 * multiplier, 30 * multiplier, 4 * multiplier);
        softDropBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        softDropBtn.setHorizontalAlignment(SwingConstants.CENTER);
        softDropBtn.setFocusable(false);
        softDropBtn.setFocusPainted(false);
        softDropBtn.setRequestFocusEnabled(false);
        softDropBtn.setRolloverEnabled(false);
        softDropBtn.setBackground(new Color(200, 200, 200));

        softDropBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Change key soft drop
            }
        });

        softDropBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                softDropBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                softDropBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [HARD DROP] ============ //
        hardDrop = new JLabel("HARD DROP");
        hardDrop.setBounds(20 * multiplier, 42 * multiplier, 30 * multiplier, 4 * multiplier);
        hardDrop.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        hardDrop.setHorizontalAlignment(SwingConstants.LEFT);

        hardDropBtn = new JButton((char) SettingsReader.getHardDrop() + "");
        hardDropBtn.setBounds(52 * multiplier, 42 * multiplier, 30 * multiplier, 4 * multiplier);
        hardDropBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        hardDropBtn.setHorizontalAlignment(SwingConstants.CENTER);
        hardDropBtn.setFocusable(false);
        hardDropBtn.setFocusPainted(false);
        hardDropBtn.setRequestFocusEnabled(false);
        hardDropBtn.setRolloverEnabled(false);
        hardDropBtn.setBackground(new Color(200, 200, 200));

        hardDropBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Change key hard drop
            }
        });

        hardDropBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hardDropBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hardDropBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [ROTATE] ============ //
        rotate = new JLabel("ROTATE RIGHT");
        rotate.setBounds(20 * multiplier, 47 * multiplier, 30 * multiplier, 4 * multiplier);
        rotate.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        rotate.setHorizontalAlignment(SwingConstants.LEFT);

        rotateBtn = new JButton((char) SettingsReader.getRotate() + "");
        rotateBtn.setBounds(52 * multiplier, 47 * multiplier, 30 * multiplier, 4 * multiplier);
        rotateBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        rotateBtn.setHorizontalAlignment(SwingConstants.CENTER);
        rotateBtn.setFocusable(false);
        rotateBtn.setFocusPainted(false);
        rotateBtn.setRequestFocusEnabled(false);
        rotateBtn.setRolloverEnabled(false);
        rotateBtn.setBackground(new Color(200, 200, 200));

        rotateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Change key rotate
            }
        });

        rotateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rotateBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rotateBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [ROTATE LEFT] ============ //
        rotateLeft = new JLabel("ROTATE LEFT");
        rotateLeft.setBounds(20 * multiplier, 52 * multiplier, 30 * multiplier, 4 * multiplier);
        rotateLeft.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        rotateLeft.setHorizontalAlignment(SwingConstants.LEFT);

        rotateLeftBtn = new JButton((char) SettingsReader.getCounterRotate() + "");
        rotateLeftBtn.setBounds(52 * multiplier, 52 * multiplier, 30 * multiplier, 4 * multiplier);
        rotateLeftBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        rotateLeftBtn.setHorizontalAlignment(SwingConstants.CENTER);
        rotateLeftBtn.setFocusable(false);
        rotateLeftBtn.setFocusPainted(false);
        rotateLeftBtn.setRequestFocusEnabled(false);
        rotateLeftBtn.setRolloverEnabled(false);
        rotateLeftBtn.setBackground(new Color(200, 200, 200));

        rotateLeftBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Change key rotate left
            }
        });

        rotateLeftBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rotateLeftBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rotateLeftBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [PAUSE] ============ //
        pause = new JLabel("PAUSE");
        pause.setBounds(20 * multiplier, 57 * multiplier, 30 * multiplier, 4 * multiplier);
        pause.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        pause.setHorizontalAlignment(SwingConstants.LEFT);

        pauseBtn = new JButton((char) SettingsReader.getPause() + "");
        pauseBtn.setBounds(52 * multiplier, 57 * multiplier, 30 * multiplier, 4 * multiplier);
        pauseBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        pauseBtn.setHorizontalAlignment(SwingConstants.CENTER);
        pauseBtn.setFocusable(false);
        pauseBtn.setFocusPainted(false);
        pauseBtn.setRequestFocusEnabled(false);
        pauseBtn.setRolloverEnabled(false);
        pauseBtn.setBackground(new Color(200, 200, 200));

        pauseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Change key pause
            }
        });

        pauseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pauseBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pauseBtn.setBackground(new Color(200, 200, 200));
            }
        });

        // ============ [MUTE] ============ //
        mute = new JLabel("MUTE");
        mute.setBounds(20 * multiplier, 62 * multiplier, 30 * multiplier, 4 * multiplier);
        mute.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        mute.setHorizontalAlignment(SwingConstants.LEFT);

        muteBtn = new JButton((char) SettingsReader.getMute() + "");
        muteBtn.setBounds(52 * multiplier, 62 * multiplier, 30 * multiplier, 4 * multiplier);
        muteBtn.setFont(new Font("Arial", Font.BOLD, 3 * multiplier));
        muteBtn.setHorizontalAlignment(SwingConstants.CENTER);
        muteBtn.setFocusable(false);
        muteBtn.setFocusPainted(false);
        muteBtn.setRequestFocusEnabled(false);
        muteBtn.setRolloverEnabled(false);
        muteBtn.setBackground(new Color(200, 200, 200));

        muteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Change key mute
            }
        });

        muteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                muteBtn.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                muteBtn.setBackground(new Color(200, 200, 200));
            }
        });

    }

    public void toggleMenus() {
        if (isToggleMenus) {
            remove(controlsTitleLabel);
            remove(controlsbackButton);
            remove(left);
            remove(leftBtn);
            remove(right);
            remove(rightBtn);
            remove(down);
            remove(downBtn);
            remove(hold);
            remove(holdBtn);
            remove(softDrop);
            remove(softDropBtn);
            remove(hardDrop);
            remove(hardDropBtn);
            remove(rotate);
            remove(rotateBtn);
            remove(rotateLeft);
            remove(rotateLeftBtn);
            remove(pause);
            remove(pauseBtn);
            remove(mute);
            remove(muteBtn);

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
            add(left);
            add(leftBtn);
            add(right);
            add(rightBtn);
            add(down);
            add(downBtn);
            add(hold);
            add(holdBtn);
            add(softDrop);
            add(softDropBtn);
            add(hardDrop);
            add(hardDropBtn);
            add(rotate);
            add(rotateBtn);
            add(rotateLeft);
            add(rotateLeftBtn);
            add(pause);
            add(pauseBtn);
            add(mute);
            add(muteBtn);

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
