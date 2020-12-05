package ClassPackages.AccountantPackage.Controllers;

import ClassPackages.Interfaces.StoreIdInterface;
import ClassPackages.MainPackage.Controllers.Controller;
import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import ClassPackages.MainPackage.Models.ScreenHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActionsController implements Initializable, StoreIdInterface {
    ObservableList list = FXCollections.observableArrayList();

    public Button backButton;
    public Button goButton;
    public ChoiceBox choiceBox;
    Handler handler = Client.get_handler();
    int stored_id;

    public void backButton(ActionEvent actionEvent) throws IOException {
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../AccountantPackage/FXML/AccountantAuthorize.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    public void goAction(ActionEvent actionEvent) throws IOException {
        String actions = (String) choiceBox.getValue();
        if (actions == "Отчет") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../AccountantPackage/FXML/AccountantReport.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "История переводов") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../AccountantPackage/FXML/AccountantTransactionsHistory.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Изменение даты перевода"){
            handler.write("returnId");
            handler.write(get_stored_id());
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
        try {
            set_stored_id((Integer) handler.read());
            System.out.println(get_stored_id());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        loadData();
    }

    @Override
    public int get_stored_id() {
        return this.stored_id;
    }

    @Override
    public void set_stored_id(int new_id) {
        this.stored_id = new_id;
    }
}
