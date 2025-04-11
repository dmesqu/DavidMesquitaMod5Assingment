package org.example.davidmesquitamod5assingment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SplashScreenController {

    @FXML
    void clickme(MouseEvent event) {
        Parent newRoot;
        Scene scene= ((ImageView)event.getSource()).getParent().getScene();
        try {
            newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.setRoot(newRoot);
    }

}
