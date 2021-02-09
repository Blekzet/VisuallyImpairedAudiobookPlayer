package service;

import stages.DialogStage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    public static void saveAudiobookData(Path audiobookPath){
        try {
            Files.deleteIfExists(VIAPSave);
            Files.createFile(VIAPSave);
            Files.writeString(VIAPSave, audiobookPath.toAbsolutePath() + "\n" + AudioService.getAudiobook().getCurrentTime().toString(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            new DialogStage().showErrorStage("НЕВОЗМОЖНО СОХРАНИТЬ");
        }

    }
    public static void openAudiobookFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(VIAPSave.toAbsolutePath()), StandardCharsets.UTF_8))){
            AudioService.openAudiobookFromPath(Path.of(reader.readLine()));
            AudioService.setTimeOnAudiobook(reader.readLine().replace(" ms", ""));
        }
    }
    public static void deleteSaveFile() throws IOException {
        Files.deleteIfExists(VIAPSave);
    }

}
