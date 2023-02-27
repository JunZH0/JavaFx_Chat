package com.example.javafx_chat.user;

import com.example.javafx_chat.controller.ChatController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class ThreadUser implements Runnable {

    private Socket socket;
    private BufferedReader in;

    ChatController chatController;

    private HashMap<String, User> userList;

    public ThreadUser(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        userList = chatController.userList;
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("quit")) {
                    break;
                }
                else {
                    sendMessageToAllUsers(inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to read from socket: " + e.getMessage());
        }
    }

    private void sendMessageToAllUsers(String message) {
        if (!message.isBlank()) {
            for (User user : userList.values()) {
                user.sendMessage(user.getUserName(), message);
            }
        }
    }
}
