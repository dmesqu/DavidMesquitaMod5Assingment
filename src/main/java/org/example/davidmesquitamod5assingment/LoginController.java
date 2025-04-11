package org.example.davidmesquitamod5assingment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;

public class LoginController {

    @FXML
    Button btnLogin;

    @FXML
    void login() throws IOException {
        Scene scene = this.btnLogin.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        scene.setRoot(fxmlLoader.load());
    }

    @FXML
    void register() throws IOException {
        Scene scene = this.btnLogin.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        scene.setRoot(fxmlLoader.load());
    }
}
