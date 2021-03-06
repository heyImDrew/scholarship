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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentRatingsActionsController implements Initializable, StoreIdInterface {

    public Button backButton;
    public ChoiceBox choiceBox;
    int stored_id;
    Handler handler = Client.get_handler();
    ObservableList list = FXCollections.observableArrayList();

    public void backButton(ActionEvent actionEvent) throws IOException {
        handler.write("returnId");
        handler.write(get_stored_id());
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryPerfomanceActions.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    private void loadData() {
        list.removeAll(list);
        String a = "По возрастанию рейтинга";
        String b = "По убыванию рейтинга";
        String c = "По возрастанию зачетки";
        String d = "По убыванию зачетки";
        list.addAll(a,b,c,d);
        choiceBox.getItems().addAll(list);
    }

    public void goButton(ActionEvent actionEvent) throws IOException {
        String actions = (String) choiceBox.getValue();

        if (actions == "По возрастанию рейтинга") {
            handler.write("returnIdStr");
            handler.write(get_stored_id());
            handler.write(actions);
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryChosenStudentRating.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "По убыванию рейтинга") {
            handler.write("returnIdStr");
            handler.write(get_stored_id());
            handler.write(actions);
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryChosenStudentRating.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "По возрастанию зачетки"){
            handler.write("returnIdStr");
            handler.write(get_stored_id());
            handler.write(actions);
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryChosenStudentRating.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "По убыванию зачетки"){
            handler.write("returnIdStr");
            handler.write(get_stored_id());
            handler.write(actions);
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryChosenStudentRating.fxml", "BSUIR TASK 2020");
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
}
