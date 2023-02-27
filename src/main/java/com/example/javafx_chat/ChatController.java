package com.example.javafx_chat;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {

    private User user;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField msgField;

    @FXML
    private Button sendButton;

    @FXML
    private Button closeButton;

    public void setUser(User user) {
        this.user = user;
    }

    public void initialize() {
        // Initialize chat UI
        //chatArea.appendText("Bienvenido al chat!\n");
        msgField.requestFocus();
    }

    @FXML
    private void handleSendButton() {
        String message = msgField.getText();
        // Send message to server
        user.sendMessage(message);
        // Append message to chat area
        chatArea.appendText("Yo: " + message + " \n");

        msgField.clear();

    }

    @FXML
    private void handleCloseButton() {
        user.close();
        closeButton.getScene().getWindow().hide();
    }

    public String getUserName() {
        return user.getUserName();
    }

    public void displayMessage(String message) {
        chatArea.appendText(message + " \n");

    }


}
