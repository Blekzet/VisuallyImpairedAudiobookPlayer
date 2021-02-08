package service;

import controller.MainWindowController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioService {
    private static MediaPlayer audiobook;

    private AudioService(){};

    public static MediaPlayer getAudiobook() {
        return audiobook;
    }

    public static void openAudiobookFromPath(String path){
        Media bookFile = new Media(path);
        audiobook = new MediaPlayer(bookFile);
        audiobook.play();
    }

    public static void nextAudiobook(){

    }

    public static void prevAudiobook(){

    }

}
