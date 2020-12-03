package ClassPackages.AccountantPackage.Controllers;

import ClassPackages.MainPackage.Controllers.Controller;
import javafx.beans.Observable;
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
import java.util.ResourceBundle;

public class ActionsController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();

    public Button backButton;
    public Button goButton;
    public ChoiceBox choiceBox;

    public void backButton(ActionEvent actionEvent) throws IOException {
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../AccountantPackage/FXML/AccountantAuthorize.fxml"));
        newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        Controller.CurrentStage = newStage;
    }

    public void goAction(ActionEvent actionEvent) throws IOException {
        String actions = (String) choiceBox.getValue();
        if (actions == "Отчет") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../AccountantPackage/FXML/AccountantReport.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "История переводов") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../AccountantPackage/FXML/AccountantTransactionsHistory.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Изменение даты перевода"){
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../AccountantPackage/FXML/AccountantScholarshipTransaction.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        }
    }

    private void loadData() {
        list.removeAll(list);
        String a = "Отчет";
        String b = "История переводов";
        String c = "Изменение даты перевода";
        list.addAll(a,b,c);
        choiceBox.getItems().addAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
