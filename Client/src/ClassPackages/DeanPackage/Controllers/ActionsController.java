package ClassPackages.DeanPackage.Controllers;

import ClassPackages.MainPackage.Controllers.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionsController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();

    public Button backButton;
    public ChoiceBox choiceBox;

    public void backButton(ActionEvent actionEvent) throws IOException {
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryAuthorize.fxml"));
        newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        Controller.CurrentStage = newStage;
    }

    public void goButton(ActionEvent actionEvent) throws IOException {
        String actions = (String) choiceBox.getValue();
        if (actions == "Назначить стипендию") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryAddScholarship.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Добавить студента") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryAddStudent.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Удалить студента") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryDeleteStudent.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Изменить студента") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryEditStudent.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Успеваемость студентов") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryPerfomanceACtions.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Информация о стипендии") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryScholarshipInfo.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Информация о студентах") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryShowStudentsInfo.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        }
    }

    private void loadData() {
        list.removeAll(list);
        String a = "Назначить стипендию";
        String b = "Добавить студента";
        String c = "Удалить студента";
        String d = "Изменить студента";
        String e = "Успеваемость студентов";
        String f = "Информация о стипендии";
        String g = "Информация о студентах";
        list.addAll(a,b,c,d,e,f,g);
        choiceBox.getItems().addAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
