package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DialogController {
    public Button close;
    public Label error;

    public void setError(String message) {
        error.setText(message);
    }

    public void closeDialog(ActionEvent actionEvent) {
        Stage errorStage = (Stage) close.getScene().getWindow();
        errorStage.close();
    }
}
