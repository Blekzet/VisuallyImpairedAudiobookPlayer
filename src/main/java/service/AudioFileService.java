package service;

import stages.DialogStage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AudioFileService {
    private static List<String> filenameList;
    @Deprecated
    private static List<String> reverseFilenameList;

    public static void filesSet(Path path){
        if(filenameList == null) {
            try (Stream<Path> stream = Files.list(path)) {
                filenameList = stream
                        .filter(file -> !Files.isDirectory(file))
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reverseFilenameList = new ArrayList<>(filenameList);
        Collections.reverse(reverseFilenameList);
    }

    public static String getNextOrPrevFilename(String audiobook, boolean reverseFlag){
        int index = filenameList.indexOf(audiobook);
        try {
            return reverseFlag ? filenameList.get(index - 1) : filenameList.get(index + 1);
        } catch (IndexOutOfBoundsException e){
            return reverseFlag ? filenameList.get(filenameList.size() - 1) : filenameList.get(0);
        }
    }

    @Deprecated
    public static String getFilenameOld(String audiobook, boolean reverseFlag){
        String currentElement = audiobook;
        Iterator<String> filenameIter;

        if(reverseFlag){
            filenameIter = reverseFilenameList.iterator();
        } else {
            filenameIter = filenameList.iterator();
        }

       while(filenameIter.hasNext()){
           currentElement = filenameIter.next();

           if(!currentElement.equals(audiobook)) continue;
           else if(filenameIter.hasNext()) currentElement = filenameIter.next();
           else if(reverseFlag) return reverseFilenameList.get(0);
           else return filenameList.get(0);

           if(StringFormatter.getFileExtension(currentElement).equals(".mp3")){
               return currentElement;
           }
       }
       return currentElement;
    }


    public static void saveAudiobookData(Path audiobookPath){
        try {
            Path audiobookDataSaveFile = Files.createFile(audiobookPath);
            Files.writeString(audiobookDataSaveFile, audiobookPath.toString(), StandardOpenOption.APPEND);
            Files.writeString(audiobookDataSaveFile, AudioService.getAudiobook().getCurrentTime().toString(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            new DialogStage().showErrorStage("НЕВОЗМОЖНО СОХРАНИТЬ");
        }

    }
    public static List<String> getFilenameSet(){
        return filenameList;
    }


}
