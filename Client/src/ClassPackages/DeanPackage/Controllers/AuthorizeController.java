package ClassPackages.DeanPackage.Controllers;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizeController {

    public TextField loginField;
    public TextField passwordField;
    public static Stage CurrentStage;

    public void enterButton() throws IOException {
        CurrentStage = (Stage) loginField.getScene().getWindow();
        String log = loginField.getText();
        String pass = passwordField.getText();
        System.out.println("Login: " + log + ". Password " + pass);
    }
}