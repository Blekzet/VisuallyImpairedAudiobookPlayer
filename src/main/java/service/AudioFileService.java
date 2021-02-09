package service;

import stages.DialogStage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AudioFileService {
    private static List<String> filenameList;
    private static final Path VIAPSave = Paths.get("VIAPSave.save");

    public static void filesList(Path path){
        if(filenameList == null) {
            try (Stream<Path> stream = Files.list(path)) {
                filenameList = stream
                        .filter(file -> !Files.isDirectory(file))
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .filter(x -> Objects.equals(StringFormatter.getFileExtension(x), ".mp3"))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getNextOrPrevFilename(String audiobook, boolean reverseFlag){
        int index = filenameList.indexOf(audiobook);
        try {
            return reverseFlag ? filenameList.get(index - 1) : filenameList.get(index + 1);
        } catch (IndexOutOfBoundsException e){
            return reverseFlag ? filenameList.get(filenameList.size() - 1) : filenameList.get(0);
        }
    }

    public static void saveAudiobookData(String audiobookPath){
        try {
            Files.deleteIfExists(VIAPSave);
            Files.createFile(VIAPSave);
            Files.writeString(VIAPSave, audiobookPath + "\n" + AudioService.getAudiobook().getCurrentTime().toString(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            new DialogStage().showErrorStage("НЕВОЗМОЖНО СОХРАНИТЬ");
        }

    }

}
