package com.example.javafx_chat.user;

import java.io.*;
import java.net.Socket;

import com.example.javafx_chat.controller.ChatController;

import java.io.PrintWriter;

public class User {
    private String userName;
    private Socket socket;
    private PrintWriter out;
    private ThreadUser threadUser;

    ChatController chatController;

    public User(String userName, ChatController chatController) throws IOException {
        this.userName = userName;
        this.socket = new Socket("localhost", 5000);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.threadUser = new ThreadUser(socket, this.chatController);
        new Thread(threadUser).start();
        out.println(userName + ": se ha conectado al chat");
    }

    public void sendMessage(String message) {
        // Check if the message is a whisper
        if (message.startsWith("/w ")) {
            String[] parts = message.split(" ", 3);
            // Check if the whisper command is formatted correctly
            if (parts.length < 3) {
                System.out.println("Invalid whisper command. Use '/w <recipient> <message>'.");
                return;
            }
            String recipient = parts[1];
            String whisperMessage = parts[2];
            // Check if the recipient is empty
            if (recipient.isEmpty()) {
                System.out.println("Please specify a recipient for the whisper command.");
                return;
            }
            out.println(userName + " (whisper to " + recipient + "): " + whisperMessage);
        } else {
            out.println(userName + ": " + message);
        }
    }


    public void close() {
        out.println(userName + ": ha cerrado sesión");
        try {
            socket.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public String getUserName() {
        return userName;
    }
}
