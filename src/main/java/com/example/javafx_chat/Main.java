package com.example.javafx_chat;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

    ChatController chatController;
    DialogController dialogController;



    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load dialog FXML file
        FXMLLoader dialogLoader = new FXMLLoader(getClass().getResource("Dialog.fxml"));
        Parent dialogRoot = dialogLoader.load();
        dialogController = dialogLoader.getController();

        // Create dialog stage
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(new Scene(dialogRoot));
        dialogStage.showAndWait();


        // Retrieve user name from dialog controller
        String userName = dialogController.getUserName();

// If user name is empty, exit
        if (userName.isEmpty()) {
            return;
        } else {

            // Create user and pass user name
            User user = new User(userName);

            // Load chat window FXML file
            FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("Chat.fxml"));
            Parent chatRoot = chatLoader.load();
            chatController = chatLoader.getController();

            // Create chat stage
            Stage chatStage = new Stage();
            chatStage.setScene(new Scene(chatRoot));
            chatStage.show();

            // Set chat controller and initialize chat
            chatController.setUser(user);
            chatController.initialize();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}