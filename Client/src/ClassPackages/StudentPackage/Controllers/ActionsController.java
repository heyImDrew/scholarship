package ClassPackages.StudentPackage.Controllers;

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
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../StudentPackage/FXML/StudentAuthorize.fxml"));
        newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        Controller.CurrentStage = newStage;
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
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../StudentPackage/FXML/StudentBankInfo.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Контактная информация") {
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../StudentPackage/FXML/StudentContactInfo.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Информация об экзаменах"){
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../StudentPackage/FXML/StudentExamInfo.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        } else if (actions == "Информация о стипендиях"){
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../StudentPackage/FXML/StudentScholarshipInfo.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        }
    }
}