package stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViapMain extends Application {
    FXMLLoader fxmlLoader;

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

    }
}
