package com.example.javafx_chat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogController {

    @FXML
    private TextField nameField;

    @FXML
    private Button connectButton;

    private String userName;

    public String getUserName() {
        return userName;
    }

    @FXML
    public void handleOkButton() {
        userName = nameField.getText();
        Stage stage = (Stage) connectButton.getScene().getWindow();
        stage.close();
    }
}
