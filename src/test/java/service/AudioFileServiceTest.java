package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

class AudioFileServiceTest {

    @Test
    void getFilenameTest1() {
        AudioFileService.filesSet(Path.of("D:/IJProject/VisuallyImpairedAudiobookPlayer/src/main/test"));
        Assertions.assertEquals(AudioFileService.getFilename("41331.mp3", true), "32399.mp3");
    }
    @Test
    void getFilenameTest2() {
        AudioFileService.filesSet(Path.of("D:/IJProject/VisuallyImpairedAudiobookPlayer/src/main/test"));
        Assertions.assertEquals(AudioFileService.getFilename("54847.mp3", true), "41331.mp3");
    }
    @Test
    void getFilenameTest3() {
        AudioFileService.filesSet(Path.of("D:/IJProject/VisuallyImpairedAudiobookPlayer/src/main/test"));
        Assertions.assertEquals(AudioFileService.getFilename("31871.mp3", true), "54847.mp3");
    }
    @Test
    void getFilenameTest4() {
        AudioFileService.filesSet(Path.of("D:/IJProject/VisuallyImpairedAudiobookPlayer/src/main/test"));
        Assertions.assertEquals(AudioFileService.getFilename("31871.mp3", false), "32399.mp3");
    }
    @Test
    void getFilenameTest5() {
        AudioFileService.filesSet(Path.of("D:/IJProject/VisuallyImpairedAudiobookPlayer/src/main/test"));
        Assertions.assertEquals(AudioFileService.getFilename("32399.mp3", false), "41331.mp3");
    }
    @Test
    void getFilenameTest6() {
        AudioFileService.filesSet(Path.of("D:/IJProject/VisuallyImpairedAudiobookPlayer/src/main/test"));
        Assertions.assertEquals(AudioFileService.getFilename("54847.mp3", false), "31871.mp3");
    }
}