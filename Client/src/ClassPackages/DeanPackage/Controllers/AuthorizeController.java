package ClassPackages.DeanPackage.Controllers;

import ClassPackages.MainPackage.Controllers.Controller;
import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import ClassPackages.MainPackage.Models.ScreenHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizeController {

    public TextField loginField;
    public PasswordField passwordField;
    Handler handler = Client.get_handler();

    public void enterDean() throws IOException, ClassNotFoundException {
        handler.write(new String("enterDean"));
        String log = loginField.getText();
        String pass = passwordField.getText();
        handler.write(log);
        handler.write(pass);
        String response = (String) handler.read();
        if (!response.equals("Nothing")) {
            handler.write("returnId");
            handler.write(Integer.valueOf(response));
            Controller.CurrentStage = (Stage) loginField.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryActions.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        }
    }

    public void backDean() throws IOException {
        Controller.CurrentStage = (Stage) loginField.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../MainPackage/FXML/DeaneryAccountantAuthorizeMenu.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }
}