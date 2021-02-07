import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViapMain extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("VIAP: Visually Impaired Audiobook Player");
        FXMLLoader fxmlLoader = new FXMLLoader(ViapMain.class.getResource("/fxml/MainWindow.fxml"));

        Scene mainScene = new Scene(fxmlLoader.load());

        stage.setScene(mainScene);
        stage.show();

    }
}
