package ClassPackages.MainPackage.Controllers;

import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DeanAccountantController {
    public Button deaneryButton;
    public Button accountantButton;
    public static Stage CurrentStage;

    public void deanAction() throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryAuthorizeController.fxml"));
        newStage.setTitle("Dean Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        CurrentStage = newStage;
    }

    public void accountantAction() throws IOException {
        CurrentStage.close();
        Handler handler = Client.get_handler();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../AccountantPackage/FXML/AccountantAuthorizeController.fxml"));
        newStage.setTitle("Accountant Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        CurrentStage = newStage;
    }
}
