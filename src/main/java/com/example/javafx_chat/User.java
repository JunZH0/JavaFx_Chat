package com.example.javafx_chat;

import java.io.*;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class User {
    private String userName;
    private Socket socket;
    private PrintWriter out;
    private ThreadUser threadUser;

    ChatController chatController;

    public User(String userName) throws IOException {
        this.userName = userName;
        this.socket = new Socket("localhost", 5000);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.threadUser = new ThreadUser(socket, chatController);
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
