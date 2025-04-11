package org.example.davidmesquitamod5assingment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class RegisterController {

    @FXML
    Button btnRegister;

    @FXML
    void login() throws IOException {
        Scene scene = this.btnRegister.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        scene.setRoot(fxmlLoader.load());
    }

    @FXML
    void register() throws IOException {
        Scene scene = this.btnRegister.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        scene.setRoot(fxmlLoader.load());
    }

}
