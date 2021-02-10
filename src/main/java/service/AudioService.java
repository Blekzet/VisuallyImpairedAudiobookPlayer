package service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.nio.file.Path;

public class AudioService {
    private static MediaPlayer audiobook;
    private static String currentAudiobookName;
    private static Path currentDir;
    private static Path currentAudiobookPath;

    private AudioService(){}

    public static MediaPlayer getAudiobook() {
        return audiobook;
    }

    public static void openAudiobookFromPath(Path path){
        currentAudiobookPath = path;
        currentAudiobookName = path.getFileName().toString();
        currentDir = path.getParent();

        Media bookFile = new Media(path.toUri().toString());
        audiobook = new MediaPlayer(bookFile);
        audiobook.play();
    }

    public static void nextOrPrevAudiobook(boolean reverseFlag){
        AudioFileService.filesList(currentDir);

        String nextAudiobookName = AudioFileService.getNextOrPrevFilename(currentAudiobookName, reverseFlag);
        currentAudiobookName = nextAudiobookName;
        String formatAudiobookName = nextAudiobookName.replaceAll(" ", "%20");

        currentAudiobookPath = Path.of(currentDir.toAbsolutePath() + "\\" + currentAudiobookName);
        Media bookFile = new Media(currentDir.toUri() + formatAudiobookName);
        audiobook = new MediaPlayer(bookFile);
        audiobook.play();
    }
    public static Path getAudiobookPath(){
        return currentAudiobookPath;
    }

    public static void setTimeOnAudiobook(String readLine) {
        audiobook.setStartTime(Duration.millis(Double.parseDouble(readLine)));
    }
}
