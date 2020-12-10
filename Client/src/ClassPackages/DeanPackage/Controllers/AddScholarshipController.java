package ClassPackages.DeanPackage.Controllers;

import ClassPackages.Interfaces.StoreIdInterface;
import ClassPackages.MainPackage.Controllers.Controller;
import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import ClassPackages.MainPackage.Models.ScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddScholarshipController implements Initializable, StoreIdInterface {
    public Button backButton;
    public ChoiceBox scholarBox;
    public ChoiceBox numberBox;
    public ListView lastName;
    public ListView name;
    public ListView group;
    public ListView recordBook;
    public ListView scholarship;
    public TextField scholarAmount;
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
            scholarBox.getItems().add("Обычная");
            scholarBox.getItems().add("Повышенная");
            handler.write("DeaneryAddScholarship");
            set_stored_id((Integer)handler.read());
            System.out.println(get_stored_id());
            handler.write(get_stored_id());
            while (true) {
                ArrayList data = (ArrayList) handler.read();
                if (data.get(0).equals("stop")) {
                    return;
                }
                name.getItems().add(data.get(0));
                lastName.getItems().add(data.get(1));
                group.getItems().add(data.get(2));
                recordBook.getItems().add(data.get(3));
                numberBox.getItems().add(data.get(3));
                scholarship.getItems().add(data.get(4));
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(ActionEvent actionEvent) throws IOException {
        if (numberBox.getValue() != null && scholarBox.getValue() != null) {
            handler.write("deanChangeStudentScholarship");
            handler.write((String) numberBox.getValue());
            handler.write((String) scholarBox.getValue());
            handler.write((String) scholarAmount.getText());

            handler.write("returnId");
            handler.write(get_stored_id());
            Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryAddScholarship.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        }
    }
}
