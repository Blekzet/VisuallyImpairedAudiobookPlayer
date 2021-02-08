package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileListingService {
    private static Set<String> filenameSet;

    public static Set<String> filesSet(Path path){
        if(filenameSet == null) {
            try (Stream<Path> stream = Files.list(path)) {
                filenameSet = stream
                        .filter(file -> !Files.isDirectory(file))
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toSet());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return filenameSet;
        }else return filenameSet;
    }

    public static String getFilename(String audiobook, boolean reverseFlag){
        String currentElement = audiobook;
        Iterator<String> filenameIter;

        if(reverseFlag){
           filenameIter = new TreeSet<String>(filenameSet).descendingIterator();
        } else {
            filenameIter = filenameSet.iterator();
        }

       while(filenameIter.hasNext()){
           currentElement = filenameIter.next();

           if(currentElement.equals(audiobook) & filenameIter.hasNext() & getFileExtension(currentElement).equals(".mp3")){
               return filenameIter.next();
           } return filenameSet.stream().findFirst().get().toString();
       }
       return currentElement;
    }

    private static String getFileExtension(String mystr) {
        int index = mystr.indexOf('.');
        return index == -1 ? null : mystr.substring(index);
    }

}
