package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import service.AudioFileService;
import service.AudioService;
import service.StringFormatter;
import stages.DialogStage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindowController {
    private boolean isPlaying = true;
    private final DialogStage errorStage = new DialogStage();
    private final Timer delay = new Timer();

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
        }
    }

    public void nextAudio(ActionEvent actionEvent) {
        try{
            AudioService.getAudiobook().pause();
            AudioService.nextOrPrevAudiobook(false);
            AudioFileService.deleteSaveFile();
        } catch (NullPointerException | IOException e){
            errorStage.showErrorStage("НЕТ ФАЙЛА");
        }
    }

    public void prevAudio(ActionEvent actionEvent) {
        try{
            AudioService.getAudiobook().pause();
            AudioService.nextOrPrevAudiobook(true);
            AudioFileService.deleteSaveFile();
        } catch (NullPointerException | IOException e){
            errorStage.showErrorStage("НЕТ ФАЙЛА");
        }
    }

    public void saveClick(ActionEvent actionEvent) {
        try {
            AudioService.getAudiobook().pause();
            AudioFileService.saveAudiobookData(AudioService.getAudiobookPath());
            Platform.exit();
            System.exit(0);
        }catch (NullPointerException e){
            errorStage.showErrorStage("НЕТ ФАЙЛА");
        } finally {
        }
    }

    public void resumeFromSaveClick(ActionEvent actionEvent) {
        try {
            AudioService.getAudiobook().pause();
            AudioFileService.openAudiobookFromFile();
        } catch (IOException e) {
            errorStage.showErrorStage("НЕТ ФАЙЛА");
        }
    }

    public void setTimer(){
        delay.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(AudioService.getAudiobook() != null) {
                    Platform.runLater(() -> timer.setText(
                            StringFormatter.formatDurationFromSecond(AudioService.getAudiobook().getCurrentTime())
                                    + "/"
                                    + StringFormatter.formatDurationFromSecond(AudioService.getAudiobook().getStopTime())
                    ));
                }
            }
        }, 0, 10);
    }

    public void setSliderSound(MouseEvent mouseEvent) {
        AudioService.getAudiobook().setVolume(sound.getValue());
    }
}
