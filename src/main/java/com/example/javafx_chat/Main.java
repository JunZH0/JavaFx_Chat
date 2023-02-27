package com.example.javafx_chat;


import com.example.javafx_chat.controller.ChatController;
import com.example.javafx_chat.controller.DialogController;
import com.example.javafx_chat.user.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

    ChatController chatController;
    DialogController dialogController;



    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar ventana de diálogo
        FXMLLoader dialogLoader = new FXMLLoader(getClass().getResource("Dialog.fxml"));
        Parent dialogRoot = dialogLoader.load();
        dialogController = dialogLoader.getController();

        // Crea la ventana de diálogo
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(new Scene(dialogRoot));
        dialogStage.showAndWait();


        // Obtener el nombre de usuario
        String userName = dialogController.getUserName();

        // comprobar si el nombre de usuario está vacío
        if (userName.isEmpty()) {
            System.out.println("No se ha introducido ningún nombre");
            System.exit(0);
        } else {
            // Crear usuario
            User user = new User(userName);

            // Cargar ventana de chat
            FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("Chat.fxml"));
            Parent chatRoot = chatLoader.load();
            chatController = chatLoader.getController();

            // Crear ventana de chat
            Stage chatStage = new Stage();
            chatStage.setScene(new Scene(chatRoot));
            chatStage.show();
            // mostrar mensaje de bienvenida
            chatController.displayMessage("Bienvenid@ " + userName + "!" + "\nRecuerda no faltar al respeto a nadie y no usar palabras malsonantes.");

            // Inicializar el controlador de chat
            chatController.setUser(user);
            chatController.initialize();

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}