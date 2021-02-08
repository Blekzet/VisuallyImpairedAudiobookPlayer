package service;

import javafx.util.Duration;

public class StringFormatter {

    public static String formatDurationFromSecondToStandart(Duration duration){
        int seconds = (int) duration.toSeconds();
        return String.format("%d:%02d:%02d",  seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
    }

}
