package service;

import controller.MainWindowController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Path;
import java.util.Set;

public class AudioService {
    private static MediaPlayer audiobook;
    private static String currentAudiobookName;
    private static Path currentDir;
    private static Set<String> audiobooksNameSet;

    private AudioService(){};

    public static MediaPlayer getAudiobook() {
        return audiobook;
    }

    public static void openAudiobookFromPath(Path path){
        currentAudiobookName = path.getFileName().toString();
        currentDir = path.getParent();

        Media bookFile = new Media(path.toUri().toString());

        audiobook = new MediaPlayer(bookFile);
        audiobook.play();
    }

    public static void nextAudiobook(boolean reverceFlag){
        String nextFilename = FileListingService.getFilename(currentAudiobookName, reverceFlag);
        Media bookFile = new Media(currentDir.toUri().toString() + nextFilename);

        audiobook = new MediaPlayer(bookFile);
        audiobook.play();
    }

}
