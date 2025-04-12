package org.example.davidmesquitamod5assingment;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.example.davidmesquitamod5assingment.db.ConnDbOps;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
            );

    @FXML
    TextField first_name, last_name, department, major;
    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, Integer> tv_id;
    @FXML
    private TableColumn<Person, String> tv_fn, tv_ln, tv_dept, tv_major;
    @FXML
    ImageView img_view;
    @FXML
    MenuBar m_bar;

    private ConnDbOps cdbop;
    //initalizes table and menu bar
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createShortcuts();
        cdbop = new ConnDbOps();
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tv_dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        tv_major.setCellValueFactory(new PropertyValueFactory<>("major"));
        data.addAll(cdbop.getAllUsers());
        tv.setItems(data);
        Platform.runLater(() -> {
            m_bar.getScene().getRoot().getStyleClass().add("light-theme");
        });
    }
    //helper method for initalize
    @FXML
    protected void createShortcuts(){
        Menu fileMenu = new Menu("File");
        MenuItem openFile = new MenuItem("Upload Profile Picture");
        openFile.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        openFile.setOnAction(e -> {
           showImage();
        });
        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        exitApp.setOnAction(e -> {
            System.exit(0);
        });
        fileMenu.getItems().addAll(openFile, exitApp);
        Menu editMenu = new Menu("Edit");
        MenuItem clearFields = new MenuItem("Clear Fields");
        clearFields.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        clearFields.setOnAction(e -> {
            clearForm();
        });
        editMenu.getItems().add(clearFields);
        Menu settingsMenu = new Menu("Settings");
        MenuItem toggleTheme = new MenuItem("Change Theme");
        toggleTheme.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
        toggleTheme.setOnAction(e -> {
            ObservableList<String> styles = m_bar.getScene().getRoot().getStyleClass();
            if (styles.contains("light-theme")) {
                styles.remove("light-theme");
                styles.add("dark-theme");
            } else {
                styles.remove("dark-theme");
                styles.add("light-theme");
            }
        });
        settingsMenu.getItems().add(toggleTheme);
        Menu helpMenu = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        about.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Challenges Faced");
            alert.setContentText("By far the biggest issue I found while doing this was getting the pom file to actually allow me to connect to my database, I had to edit it a few times to make it work but everything else was pretty straightforward and wasn't too bad");
            alert.showAndWait();
        });
        helpMenu.getItems().add(about);
        m_bar.getMenus().addAll(fileMenu, editMenu, settingsMenu, helpMenu);
    }
    //adds new entry to database
    @FXML
    protected void addNewRecord() {
        cdbop = new ConnDbOps();
        String firstName = first_name.getText();
        String lastName = last_name.getText();
        String department = this.department.getText();
        String major = this.major.getText();
        cdbop.insertUser(firstName, lastName, major, department);
        refresh();
        clearForm();
    }
    //refreshes table after any action is done to keep data in sync
    @FXML
    protected void refresh(){
        cdbop = new ConnDbOps();
        tv.getItems().clear();
        data.addAll(cdbop.getAllUsers());
        tv.setItems(data);
    }
    //clears current data entered
    @FXML
    protected void clearForm() {
        first_name.clear();
        last_name.setText("");
        department.setText("");
        major.setText("");
    }
    //edits data in question in the database
    @FXML
    protected void editRecord() {
        cdbop = new ConnDbOps();
        Person user = tv.getSelectionModel().getSelectedItem();
        if (user == null) return;
        String newFirstName = first_name.getText();
        String newLastName = last_name.getText();
        String newDepartment = department.getText();
        String newMajor = major.getText();
        cdbop.updateUser(user.getId(), newFirstName, newLastName, newDepartment, newMajor);
        refresh();
        clearForm();
    }
    //deletes data from database
    @FXML
    protected void deleteRecord() {
        cdbop = new ConnDbOps();
        Person user = tv.getSelectionModel().getSelectedItem();
        if (user == null) return;
        cdbop.deleteUser(user.getId());
        refresh();
        clearForm();
    }
    //method for the profile picture
    @FXML
    protected void showImage() {
        File file= (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
        if(file!=null){
            img_view.setImage(new Image(file.toURI().toString()));

        }
    }

    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        Person p= tv.getSelectionModel().getSelectedItem();
        first_name.setText(p.getFirstName());
        last_name.setText(p.getLastName());
        department.setText(p.getDept());
        major.setText(p.getMajor());
    }

}