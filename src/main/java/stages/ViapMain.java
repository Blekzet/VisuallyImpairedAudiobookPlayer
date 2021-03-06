package stages;

import controller.MainWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    public void start(Stage stage) throws IOException {
        stage.setTitle("VIAP: Visually Impaired Audiobook Player");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/icon.png"));
        fxmlLoader = new FXMLLoader(ViapMain.class.getResource("/fxml/MainWindow.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        stage.setScene(mainScene);

        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
        audioStart();
    }

    private void audioStart(){
        mainWindowController = fxmlLoader.getController();
        mainWindowController.setTimer();
        Parameters parameters = getParameters();
        List<String> args = parameters.getRaw();
        if(args.size() > 0){
            Path path = Paths.get(args.get(0));
            AudioService.openAudiobookFromPath(path);
        }
    }
}
