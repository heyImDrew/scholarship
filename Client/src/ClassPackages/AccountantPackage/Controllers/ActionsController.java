package ClassPackages.AccountantPackage.Controllers;

import ClassPackages.MainPackage.Controllers.Controller;
import ClassPackages.MainPackage.Models.ScreenHandler;
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
        ScreenHandler screen = new ScreenHandler("../../AccountantPackage/FXML/AccountantAuthorize.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    public void goAction(ActionEvent actionEvent) throws IOException {
        String actions = (String) choiceBox.getValue();
        if (actions == "Отчет") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../AccountantPackage/FXML/AccountantReport.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "История переводов") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../AccountantPackage/FXML/AccountantTransactionsHistory.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Изменение даты перевода"){
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../AccountantPackage/FXML/AccountantScholarshipTransaction.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
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
