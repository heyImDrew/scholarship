package ClassPackages.MainPackage.Controllers;

import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Button studentButton;
    public static Stage CurrentStage;

    public void studentAction() throws IOException {
        CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../StudentPackage/FXML/StudentAuthorize.fxml"));
        newStage.setTitle("Student Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        CurrentStage = newStage;
    }

    public void workerAction() throws IOException {
        CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../MainPackage/FXML/DeaneryAccountantAuthorizeMenu.fxml"));
        newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        CurrentStage = newStage;
    }

    public void deanAction() throws IOException {
        CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryAuthorizeController.fxml"));
        newStage.setTitle("Dean Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        CurrentStage = newStage;
    }

    public void accountantAction() throws IOException {
        CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../AccountantPackage/FXML/AccountantAuthorizeController.fxml"));
        newStage.setTitle("Accountant Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        CurrentStage = newStage;
    }
}
