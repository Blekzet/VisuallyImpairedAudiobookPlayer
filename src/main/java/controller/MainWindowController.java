package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import service.AudioService;
import stages.DialogStage;

public class MainWindowController {
    private boolean isPlaying = true;
    private AudioService bookService;
    private final DialogStage errorStage = new DialogStage();
    private MediaPlayer audiobookPlayer;
    public Button pauseOrPlay;
    public Button next;
    public Button prev;
    public Slider sound;
    public Label timer;
    public Button saveTime;
    public Button resumeFromMemory;

    public void pauseOrPlayClick(ActionEvent actionEvent) {
        try{
            audiobookPlayer = bookService.getAudiobook();
        } catch (NullPointerException e){
            errorStage.showErrorStage("НЕТ ФАЙЛА");
            return;
        }

        if(isPlaying){
            audiobookPlayer.pause();
            isPlaying = false;
        }else {
            audiobookPlayer.play();
            isPlaying = true;
        }
    }

    public void nextAudio(ActionEvent actionEvent) {

    }

    public void prevAudio(ActionEvent actionEvent) {
    }

    public void saveClick(ActionEvent actionEvent) {
    }

    public void resumeFromSaveClick(ActionEvent actionEvent) {
    }
}
