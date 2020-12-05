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

public class ActionsController implements Initializable, StoreIdInterface {
    ObservableList list = FXCollections.observableArrayList();

    public Button backButton;
    public ChoiceBox choiceBox;
    Handler handler = Client.get_handler();
    int stored_id;

    public void backButton(ActionEvent actionEvent) throws IOException {
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryAuthorize.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    public void goButton(ActionEvent actionEvent) throws IOException {
        String actions = (String) choiceBox.getValue();
        if (actions == "Назначить стипендию") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryAddScholarship.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Добавить студента") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryAddStudent.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Удалить студента") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryDeleteStudent.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Изменить студента") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryEditStudent.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Успеваемость студентов") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryPerfomanceActions.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Информация о стипендии") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryScholarshipInfo.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        } else if (actions == "Информация о студентах") {
            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryShowStudentsInfo.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
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
