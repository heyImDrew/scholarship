package ClassPackages.StudentPackage.Controllers;

import ClassPackages.MainPackage.Controllers.Controller;
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
import java.util.ResourceBundle;

public class ActionsController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();

    public Button backButton;
    public ChoiceBox choiceBox;

    public void backButton(ActionEvent actionEvent) throws IOException {
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../StudentPackage/FXML/StudentAuthorize.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    private void loadData() {
        list.removeAll(list);
        String a = "Информация о карте";
        String b = "Контактная информация";
        String c = "Информация об экзаменах";
        String d = "Информация о стипендиях";
        list.addAll(a,b,c,d);
        choiceBox.getItems().addAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public void goButton(ActionEvent actionEvent) throws IOException {
        String actions = (String) choiceBox.getValue();
        if (actions == "Информация о карте") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../StudentPackage/FXML/StudentBankInfo.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Контактная информация") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../StudentPackage/FXML/StudentContactInfo.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Информация об экзаменах"){
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../StudentPackage/FXML/StudentExamInfo.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Информация о стипендиях"){
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../StudentPackage/FXML/StudentScholarshipInfo.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        }
    }
}