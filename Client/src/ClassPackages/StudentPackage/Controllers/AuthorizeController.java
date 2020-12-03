package ClassPackages.StudentPackage.Controllers;

import ClassPackages.MainPackage.Controllers.Controller;
import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import ClassPackages.MainPackage.Models.ScreenHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizeController {

    public TextField loginField;
    public TextField passwordField;
    Handler handler = Client.get_handler();
    
    public void enterStudent() throws IOException, ClassNotFoundException {
        handler.write(new String("enterStudent"));
        String log = loginField.getText();
        String pass = passwordField.getText();
        handler.write(log);
        handler.write(pass);
        String response = (String) handler.read();
        if (!response.equals("Nothing")) {
            // Print ID
            System.out.println("ID: " + response);

            // Switch to new scene
            Controller.CurrentStage = (Stage) loginField.getScene().getWindow();
            Controller.CurrentStage.close();
            ScreenHandler screen = new ScreenHandler("../../StudentPackage/FXML/StudentActions.fxml", "BSUIR TASK 2020");
            Controller.CurrentStage = screen.get_new_stage();
        }
    }

    public void backStudent() throws IOException {
        Controller.CurrentStage = (Stage) loginField.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../MainPackage/FXML/MainAuthorize.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }
}
