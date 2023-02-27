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
        this.chatController = chatController;
        this.socket = new Socket("localhost", 5000);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.threadUser = new ThreadUser(socket);
        new Thread(threadUser).start();
        out.println(userName + ": se ha conectado al chat");
    }


    public void sendMessage(String message) {
        out.println(userName + ": " + message);
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
