package com.example.javafx_chat.user;

import com.example.javafx_chat.controller.ChatController;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ThreadUser implements Runnable {
    private BufferedReader bfRd;
    private ChatController chatController;

    public ThreadUser(Socket socket, ChatController chatController) throws IOException {
        this.chatController = chatController;
        this.bfRd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = bfRd.readLine();
                Platform.runLater(() -> chatController.displayMessage(message));


            }
        } catch (SocketException e) {
            System.out.println("Has salido del chat");
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            try {
                bfRd.close();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }
}
