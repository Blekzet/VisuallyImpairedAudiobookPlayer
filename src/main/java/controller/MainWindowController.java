package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import service.AudioService;
import service.StringFormatter;
import stages.DialogStage;

import java.util.Timer;
import java.util.TimerTask;

public class MainWindowController {
    private boolean isPlaying = true;
    private final DialogStage errorStage = new DialogStage();

    public Button pauseOrPlay;
    public Button next;
    public Button prev;
    public Slider sound;
    public Label timer;
    public Button saveTime;
    public Button resumeFromMemory;




    public void pauseOrPlayClick(ActionEvent actionEvent) {
        try{
           if(isPlaying){
               AudioService.getAudiobook().pause();
               isPlaying = false;
               pauseOrPlay.setText("ВОСПРОИЗВЕСТИ");
           }else {
               AudioService.getAudiobook().play();
               isPlaying = true;
               pauseOrPlay.setText("ПАУЗА");
           }
        } catch (NullPointerException e){
            errorStage.showErrorStage("НЕТ ФАЙЛА");
            return;
        }


    }

    public void nextAudio(ActionEvent actionEvent) {
        try{
            AudioService.nextAudiobook(false);
        } catch (NullPointerException e){
            errorStage.showErrorStage("НЕТ ФАЙЛА");
            return;
        }
    }

    public void prevAudio(ActionEvent actionEvent) {
        try{
            AudioService.nextAudiobook(true);
        } catch (NullPointerException e){
            errorStage.showErrorStage("НЕТ ФАЙЛА");
            return;
        }
    }

    public void saveClick(ActionEvent actionEvent) {
    }

    public void resumeFromSaveClick(ActionEvent actionEvent) {
    }

    public void setTimer(){
        Timer delay = new Timer();
        delay.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> timer.setText(
                        String.valueOf(StringFormatter.formatDurationFromSecondToStandart(AudioService.getAudiobook().getCurrentTime()))
                        + "/"
                        + String.valueOf(StringFormatter.formatDurationFromSecondToStandart(AudioService.getAudiobook().getStopTime()))
                ));
            }
        }, 0, 1000);
    }
}
