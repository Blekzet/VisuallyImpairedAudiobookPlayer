package service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Path;

public class AudioService {
    private static MediaPlayer audiobook;
    private static String currentAudiobookName;
    private static Path currentDir;
    private static Path currentAudiobookPath;

    private AudioService(){};

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

    public static void nextAudiobook(boolean reverceFlag){
        AudioFileService.filesList(currentDir);
        currentAudiobookName = AudioFileService.getNextOrPrevFilename(currentAudiobookName, reverceFlag);
        Media bookFile = new Media(currentDir.toUri().toString() + currentAudiobookName);

        audiobook = new MediaPlayer(bookFile);
        audiobook.play();
    }
    public static Path getAudiobookPath(){
        return currentAudiobookPath;
    }
}
