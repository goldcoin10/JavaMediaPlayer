package com.github.goldcoin10;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


class Main implements Runnable {
    public static File filepath;
    ImageIcon playIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/playIcon.png")));
    ImageIcon pauseIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/pauseIcon.png")));
    private boolean audioPlaying = false;

    public void run() {
        renderGUI();
    }

    private void renderGUI() {
        JFrame frame = new JFrame("MediaPlayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        frame.setSize(320, 240);
        frame.setJMenuBar(renderMenuBar());
        frame.add(renderButtons());
        frame.setVisible(true);
    }
    private static JMenu fileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        openItem.addActionListener(e -> {
            JFileChooser chooseFile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "WAV Files", "wav");
            chooseFile.setFileFilter(filter);
            int returnVal = chooseFile.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                Audio.disposeData();
                filepath = chooseFile.getSelectedFile();
            }

        });
        return fileMenu;
    }

    private JMenuBar renderMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        return menuBar;
    }

    private JPanel renderButtons() {
        JPanel buttons = new JPanel();
        buttons.add(playPauseButton());
        return buttons;
    }
    private JButton playPauseButton() {
        JButton playPauseButton = new JButton(playIcon);
        playPauseButton.setPreferredSize(new Dimension(48, 48));
        playPauseButton.addActionListener(e -> {
            if (!audioPlaying && filepath != null) {
                playPauseButton.setIcon(pauseIcon);
                Audio.playAudio();
                audioPlaying = true;
            } else if(audioPlaying && filepath != null) {
                playPauseButton.setIcon(playIcon);
                Audio.pauseAudio();
                audioPlaying = false;
            }
            else {
                JOptionPane.showMessageDialog(null, "No file is open, please load one");
                JFileChooser chooseFile = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Files", "wav");
                chooseFile.setFileFilter(filter);
                int returnVal = chooseFile.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    if(filepath != null) {
                        Audio.disposeData();
                    }
                    filepath = chooseFile.getSelectedFile();
                }
             }
        });
        return playPauseButton;
     }

    public static void main(String[] args) throws IOException {
        Coconut.sanityCheck();
        EventQueue.invokeLater(new Main());
    }
}