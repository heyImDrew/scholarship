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
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChosenStudentRatingController implements Initializable, StoreIdInterface {

    public Button backButton;
    public Text text;
    public ListView lastName;
    public ListView name;
    public ListView group;
    public ListView recordBook;
    public ListView avgMark;
    int stored_id;
    Handler handler = Client.get_handler();

    public void backButton(ActionEvent actionEvent) throws IOException {
        handler.write("returnId");
        handler.write(get_stored_id());
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryStudentsRatingsActions.fxml", "BSUIR TASK 2020");
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
            handler.write("chosenStudentRating");
            set_stored_id((Integer) handler.read());
            String action_name = (String) handler.read();
            System.out.println(get_stored_id());
            handler.write(get_stored_id());
            handler.write(action_name);
            text.setText(action_name);
            while (true) {
                ArrayList data = (ArrayList) handler.read();
                if (data.get(0).equals("stop")) {
                    return;
                }
                lastName.getItems().add(data.get(0));
                name.getItems().add(data.get(1));
                group.getItems().add(data.get(2));
                recordBook.getItems().add(data.get(3));
                avgMark.getItems().add(data.get(4));
            }

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
}

