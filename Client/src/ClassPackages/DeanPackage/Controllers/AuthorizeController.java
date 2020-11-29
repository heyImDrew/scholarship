package ClassPackages.DeanPackage.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizeController {

    public TextField loginField;
    public TextField passwordField;
    public static Stage CurrentStage;

    public void enterDean() throws IOException {
        CurrentStage = (Stage) loginField.getScene().getWindow();
        String log = loginField.getText();
        String pass = passwordField.getText();
        System.out.println("Login: " + log + ". Password " + pass);
    }

    public void registerDean() throws IOException {
        CurrentStage = (Stage) loginField.getScene().getWindow();
        String log = loginField.getText();
        String pass = passwordField.getText();
        System.out.println("Login: " + log + ". Password " + pass);
    }

    public void backDean() throws IOException {
        CurrentStage = (Stage) loginField.getScene().getWindow();
        CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../MainPackage/FXML/DeaneryAccountantAuthorizeMenu.fxml"));
        newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        CurrentStage = newStage;
    }
}