package org.example.davidmesquitamod5assingment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private ConnDbOps cdbop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cdbop = new ConnDbOps();
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tv_dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        tv_major.setCellValueFactory(new PropertyValueFactory<>("major"));

        data.addAll(cdbop.getAllUsers());
        tv.setItems(data);
    }

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

    @FXML
    protected void refresh(){
        cdbop = new ConnDbOps();
        tv.getItems().clear();
        data.addAll(cdbop.getAllUsers());
        tv.setItems(data);
    }

    @FXML
    protected void clearForm() {
        first_name.clear();
        last_name.setText("");
        department.setText("");
        major.setText("");
    }

    @FXML
    protected void closeApplication() {
        System.exit(0);
    }

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

    @FXML
    protected void deleteRecord() {
        cdbop = new ConnDbOps();
        Person user = tv.getSelectionModel().getSelectedItem();
        if (user == null) return;
        cdbop.deleteUser(user.getId());
        refresh();
        clearForm();
    }

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