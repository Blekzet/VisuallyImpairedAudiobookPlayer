package stages;

import controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.AudioService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ViapMain extends Application {
    FXMLLoader fxmlLoader;
    MainWindowController mainWindowController;

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("VIAP: Visually Impaired Audiobook Player");
        fxmlLoader = new FXMLLoader(ViapMain.class.getResource("/fxml/MainWindow.fxml"));
        Scene mainScene = null;
        try {
            mainScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(mainScene);
        stage.show();
        audioStart();
    }

    private void audioStart(){
        mainWindowController = fxmlLoader.getController();
        Parameters parameters = getParameters();
        List<String> args = parameters.getRaw();
        if(args.size() > 0){
            Path path = Paths.get(args.get(0));
            AudioService.openAudiobookFromPath(path);
            mainWindowController.setTimer();
        }else return;
    }
}
