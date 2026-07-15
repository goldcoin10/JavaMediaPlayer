package com.github.goldcoin10;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Audio {
    static boolean jfxinit = false;
    static MediaPlayer mediaPlayer;
    static String filePath;
    static Media media;

    public static void playAudio(){
        Platform.runLater(() -> {
            mediaPlayer.play();
        });
    }
    public void printFilePath(){
        System.out.println(filePath);
    }

    public static void pauseAudio() {
        Platform.runLater(() -> {
            if (mediaPlayer != null) mediaPlayer.pause();
        });
    }

    public static void disposeData() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }

    public static double currentTime() {
        if(mediaPlayer == null) return 0;
        else if(mediaPlayer.getCurrentTime().toSeconds() == 0) return 0;
        else return mediaPlayer.getCurrentTime().toSeconds();
    }

    public static double songLength() {
        if(mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCycleDuration().toSeconds();
    }

    public static void jumpFive() {
        mediaPlayer.seek(Duration.seconds(currentTime() + 5));
    }

    public static void backFive() {
        mediaPlayer.seek(Duration.seconds(currentTime() - 5));
    }
}