package stages;

import controller.DialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DialogStage{
    private final Stage errorDialog;
    FXMLLoader fxmlLoader;

    public DialogStage(){
        errorDialog = new Stage();
        fxmlLoader = new FXMLLoader(ViapMain.class.getResource("/fxml/Dialog.fxml"));
        Scene errorScene = null;
        try {
            errorScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        errorDialog.setScene(errorScene);
    }

    public void showErrorStage(String error){
        DialogController errorDialogController = fxmlLoader.getController();
        errorDialogController.setError(error);
        errorDialog.show();
    }

}
