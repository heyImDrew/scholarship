package ClassPackages.DeanPackage.Controllers;

import ClassPackages.Interfaces.StoreIdInterface;
import ClassPackages.MainPackage.Controllers.Controller;
import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import ClassPackages.MainPackage.Models.ScreenHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable, StoreIdInterface {
    
    public Button backButton;
    
    // Student
    public TextField surnameField;
    public TextField nameField;
    public TextField patronymicField;
    public TextField groupField;
    public TextField recordBookField;
    public TextField cardField;
    public ChoiceBox facultyBox;
    // Exam 1
    public TextField dateField;
    public TextField markField;
    public TextField subjectField;
    // Exam 2
    public TextField subjectField1;
    public TextField markField1;
    public TextField dateField1;
    // Exam 3
    public TextField dateField11;
    public TextField markField11;
    public TextField subjectField11;
    // Exam 4
    public TextField dateField12;
    public TextField markField12;
    public TextField subjectField12;
    int stored_id;
    Handler handler = Client.get_handler();

    public void backButton(ActionEvent actionEvent) throws IOException {
        handler.write("returnId");
        handler.write(get_stored_id());
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryActions.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    public void addButton(ActionEvent actionEvent) throws IOException {
        if (surnameField.getText() != "" && nameField.getText() != "" && patronymicField.getText() != ""
            && groupField.getText() != "" && recordBookField.getText() != "" && cardField.getText() != ""
            && facultyBox.getValue() != null && dateField.getText() != "" && markField.getText() != ""
            && surnameField.getText() != "" && dateField1.getText() != "" && markField1.getText() != ""
            && subjectField1.getText() != "" && dateField11.getText() != "" && markField11.getText() != ""
            && subjectField11.getText() != "" && dateField12.getText() != "" && markField12.getText() != ""
            && subjectField12.getText() != "") {
            handler.write("deanAddStudent");

            ArrayList student = new ArrayList();
            student.add(surnameField.getText());
            student.add(nameField.getText());
            student.add(patronymicField.getText());
            student.add(groupField.getText());
            student.add(recordBookField.getText());
            student.add(cardField.getText());
            if (facultyBox.getValue() == "ИЭФ") {
                student.add(1);
            }
            else {
                student.add(2);
            }


            ArrayList exam1 = new ArrayList();
            exam1.add(dateField.getText());
            exam1.add(markField.getText());
            exam1.add(subjectField.getText());

            ArrayList exam2 = new ArrayList();
            exam2.add(dateField1.getText());
            exam2.add(markField1.getText());
            exam2.add(subjectField1.getText());

            ArrayList exam3 = new ArrayList();
            exam3.add(dateField11.getText());
            exam3.add(markField11.getText());
            exam3.add(subjectField11.getText());

            ArrayList exam4 = new ArrayList();
            exam4.add(dateField12.getText());
            exam4.add(markField12.getText());
            exam4.add(subjectField12.getText());

            handler.write(student);
            handler.write(exam1);
            handler.write(exam2);
            handler.write(exam3);
            handler.write(exam4);

            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryActions.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        }
    }

    @Override
    public int get_stored_id() {
        return this.stored_id;
    }

    @Override
    public void set_stored_id(int new_id) {
        this.stored_id = new_id;
    }

    private void loadData() {
        ObservableList list = FXCollections.observableArrayList();
        String a = "ИЭФ";
        String b = "ФКП";
        list.addAll(a,b);
        facultyBox.getItems().addAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            set_stored_id((Integer)handler.read());
            loadData();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
