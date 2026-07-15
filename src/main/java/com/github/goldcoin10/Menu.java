package com.github.goldcoin10;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu {
    private static JMenu fileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        openItem.addActionListener(e -> {
            JFileChooser chooseFile = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Audio Files", "wav", "aac", "mp3");
            chooseFile.setFileFilter(filter);
            int returnVal = chooseFile.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                if(Audio.mediaPlayer != null) {
                    Audio.disposeData();
                }
                Main.filepath = chooseFile.getSelectedFile();
                Audio.filePath = Main.filepath.toURI().toString();
                Audio.media = new Media(Audio.filePath);
                Audio.mediaPlayer = new MediaPlayer(Audio.media);
            }

        });
        return fileMenu;
    }

    public static JMenuBar renderMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu());
        return menuBar;
    }
}
