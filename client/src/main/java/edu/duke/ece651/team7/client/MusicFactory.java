package edu.duke.ece651.team7.client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
public class MusicFactory {

    public static MediaPlayer createMediaPlayer(String mediaPath) {
        Media media = new Media(Paths.get(mediaPath).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }

    public static MediaPlayer createAttackPlayer(){
        String mediaPath = "src/main/resources/music/attack.wav";
        MediaPlayer attackMediaPlayer = createMediaPlayer(mediaPath);
        attackMediaPlayer.setVolume(1);
        return attackMediaPlayer;
    }

    public static MediaPlayer createMovePlayer(){
        String mediaPath = "src/main/resources/music/move.wav";
        MediaPlayer moveMediaPlayer = createMediaPlayer(mediaPath);
        moveMediaPlayer.setVolume(1);
        return moveMediaPlayer;
    }

    public static MediaPlayer createUpgradePlayer(){
        String mediaPath = "src/main/resources/music/upgrade.wav";
        MediaPlayer upgradeMediaPlayer = createMediaPlayer(mediaPath);
        upgradeMediaPlayer.setVolume(1);
        return upgradeMediaPlayer;
    }

    public static MediaPlayer createBackgroundPlayer(){
        String mediaPath = "src/main/resources/music/background.mp3";
        MediaPlayer backgroundMediaPlayer = createMediaPlayer(mediaPath);
        backgroundMediaPlayer.setVolume(0.4);
        return backgroundMediaPlayer;
    }

    public static MediaPlayer createActionFailedPlayer(){
        String mediaPath = "src/main/resources/music/actionFailed.wav";
        MediaPlayer actionFailedPlayer = createMediaPlayer(mediaPath);
        actionFailedPlayer.setVolume(1);
        return actionFailedPlayer;
    }

    public static MediaPlayer createAllyPlayer(){
        String mediaPath = "src/main/resources/music/ally.wav";
        MediaPlayer allyPlayer = createMediaPlayer(mediaPath);
        allyPlayer.setVolume(1);
        return allyPlayer;
    }

    public static MediaPlayer createManufacturePlayer(){
        String mediaPath = "src/main/resources/music/manufacture.mp3";
        MediaPlayer manufacturePlayer = createMediaPlayer(mediaPath);
        manufacturePlayer.setVolume(1);
        return manufacturePlayer;
    }
}