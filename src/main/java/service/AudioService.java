package service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioService {
    private MediaPlayer audiobook;

    public void setAudiobook(MediaPlayer audiobook) {
        this.audiobook = audiobook;
    }

    public MediaPlayer getAudiobook() {
        return audiobook;
    }

    public MediaPlayer openAudiobookFromPath(String path){
        Media bookFile = new Media(path);
        return new MediaPlayer(bookFile);
    }


}
