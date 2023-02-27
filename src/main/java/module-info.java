module com.example.javafx_chat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_chat to javafx.fxml;
    exports com.example.javafx_chat;
    exports com.example.javafx_chat.controller;
    opens com.example.javafx_chat.controller to javafx.fxml;
    exports com.example.javafx_chat.server;
    opens com.example.javafx_chat.server to javafx.fxml;
    exports com.example.javafx_chat.user;
    opens com.example.javafx_chat.user to javafx.fxml;
}