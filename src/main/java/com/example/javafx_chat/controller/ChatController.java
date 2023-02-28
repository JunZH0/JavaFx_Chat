package com.example.javafx_chat.controller;


import com.example.javafx_chat.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class ChatController {

    private User user;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField msgField;

    @FXML
    private Button closeButton;

    public HashMap<String, User> userList = new HashMap<>();

    public void setUser(User user) {
        this.user = user;
    }

    public void initialize() {
        // Al presionar ENTER se envía el mensaje y al presionar ESCAPE se cierra la ventana
        msgField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    handleSendButton();
                    break;
                case ESCAPE:
                    handleCloseButton();
                    break;
            }
        });
    }

    @FXML
    private void handleSendButton() {
        String message = msgField.getText();
        // Send message to server
        if (!message.isBlank()) {
            for (String recipient : userList.keySet()) {
                userList.get(recipient).sendMessage(recipient, message);
            }
            chatArea.appendText("Yo: " + message + " \n");
        }
        msgField.clear();
        msgField.requestFocus();
    }

    @FXML
    private void handleCloseButton() {
        user.close();
        closeButton.getScene().getWindow().hide();
    }

    public void displayMessage(String message) {
        chatArea.appendText(message + " \n");
    }
}
