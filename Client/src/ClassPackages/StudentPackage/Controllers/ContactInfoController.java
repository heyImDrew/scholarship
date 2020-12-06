package ClassPackages.StudentPackage.Controllers;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContactInfoController implements Initializable, StoreIdInterface {

    public Button backButton;
    public ChoiceBox deaneryBox;
    public Text room;
    public Text deanery;
    public Text phone;
    int stored_id;
    Handler handler = Client.get_handler();
    ObservableList list = FXCollections.observableArrayList();

    public void backButton(ActionEvent actionEvent) throws IOException {
        handler.write("returnId");
        handler.write(get_stored_id());
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../StudentPackage/FXML/StudentActions.fxml", "BSUIR TASK 2020");
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

    private void loadData() {
        list.removeAll(list);
        String a = "ИЭФ";
        String b = "ФКП";
        list.addAll(a,b);
        deaneryBox.getItems().addAll(list);
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

    public void goButton(ActionEvent actionEvent)  throws IOException {
        String actions = (String) deaneryBox.getValue();
        if (actions == "ИЭФ") {
            room.setText("810");
            deanery.setText("Сергей Ломан");
            phone.setText("2471213");
        } else if (actions == "ФКП") {
            room.setText("410");
            deanery.setText("Иван Иванов");
            phone.setText("2471213");

        }
    }
}
