package ClassPackages.AccountantPackage.Controllers;

import ClassPackages.MainPackage.Controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizeController {

    public TextField loginField;
    public TextField passwordField;

    public void enterAccountant() throws IOException {
        String log = loginField.getText();
        String pass = passwordField.getText();
        System.out.println("Login: " + log + ". Password " + pass);
    }

    public void registerAccountant() throws IOException {
        String log = loginField.getText();
        String pass = passwordField.getText();
        System.out.println("Login: " + log + ". Password " + pass);
    }

    public void backAccountant() throws IOException {
        Controller.CurrentStage = (Stage) loginField.getScene().getWindow();
        Controller.CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../MainPackage/FXML/DeaneryAccountantAuthorizeMenu.fxml"));
        newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
        Controller.CurrentStage = newStage;
    }

}
