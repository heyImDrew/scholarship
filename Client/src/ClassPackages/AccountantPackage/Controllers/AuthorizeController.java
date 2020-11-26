package ClassPackages.AccountantPackage.Controllers;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizeController {

    public TextField loginField;
    public TextField passwordField;
    public static Stage CurrentStage;

    public void enterAccountant() throws IOException {
        CurrentStage = (Stage) loginField.getScene().getWindow();
        String log = loginField.getText();
        String pass = passwordField.getText();
        System.out.println("Login: " + log + ". Password " + pass);
    }

    public void registerAccountant() throws IOException {
        CurrentStage = (Stage) loginField.getScene().getWindow();
        String log = loginField.getText();
        String pass = passwordField.getText();
        System.out.println("Login: " + log + ". Password " + pass);
    }

    public void backAccountant() {
        System.out.println("BACK");
    }

}
