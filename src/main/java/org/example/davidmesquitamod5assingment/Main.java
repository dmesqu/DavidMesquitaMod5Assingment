package org.example.davidmesquitamod5assingment;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.util.Duration;
import org.example.davidmesquitamod5assingment.db.ConnDbOps;

public class Main extends Application {

    private Stage primaryStage;
    private static ConnDbOps cdbop;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        showScene1();

    }

    private void showScene1() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
            Scene scene = new Scene(root, 850, 560);
            scene.getStylesheets().add(getClass().getResource("styling/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            changeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    FadeTransition fadeIn;

    public void changeScene() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));

            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();
            //currentScene.getStylesheets().add(getClass().getResource("styling/style.css").toExternalForm());
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                newRoot.setOpacity(0);
                Scene newScene = new Scene(newRoot,850, 560);
                primaryStage.setScene(newScene);
                fadeIn.play();
            });
            fadeOut.play();
            fadeIn = new FadeTransition(Duration.seconds(2), newRoot);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);

        char input;
        do {
            System.out.println(" ");
            System.out.println("============== Menu ==============");
            System.out.println("| To start GUI,           press 'g' |");
            System.out.println("| To connect to DB,       press 'c' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by name,       press 'q' |");
            System.out.println("| To exit,                press 'e' |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");
            input = scan.next().charAt(0);

            switch (input) {
                case 'g':
                    launch(args); //GUI
                    break;

                case 'c':
                    cdbop.connectToDatabase(); //Your existing method
                    break;
                case 'a':
                    cdbop.listAllUsers(); //all users in DB
                    break;

                case 'i':
                    System.out.print("Enter First Name: ");
                    String firstname = scan.next();
                    System.out.print("Enter Last Name: ");
                    String lastname = scan.next();
                    System.out.print("Enter Department: ");
                    String dept = scan.next();
                    System.out.print("Enter Major: ");
                    String major = scan.next();
                    cdbop.insertUser(firstname, lastname, dept, major); //Your insertUser method
                    break;
                case 'q':
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.next();
                    cdbop.queryUserByName(queryName); //Your queryUserByName method
                    break;
                case 'e':
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println(" ");
        } while (input != 'e');

        scan.close();
    }
    */


}
