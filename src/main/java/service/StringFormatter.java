package service;

import javafx.util.Duration;

public class StringFormatter {

    public static String formatDurationFromSecond(Duration duration){
        int seconds = (int) duration.toSeconds();
        return String.format("%d:%02d:%02d",  seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
    }

    public static String getFileExtension(String fullFilename) {
        int index = fullFilename.indexOf('.');
        return index == -1 ? null : fullFilename.substring(index);
    }

}
