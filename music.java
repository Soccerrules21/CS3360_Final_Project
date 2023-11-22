package view;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Objects;


public class music {

    MediaPlayer mediaPlayer;

    @FXML
    void play(MouseEvent event) {
        String fileName = "player/8-bit-background-music-for-arcade-game-come-on-mario-164702.mp3";
        playHitSound(fileName);
    }


    private void playHitSound(String fileName){
        String path = Objects.requireNonNull(getClass().getResource(fileName)).getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}