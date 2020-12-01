package ClassPackages.AccountantPackage.Controllers;

import ClassPackages.MainPackage.Controllers.Controller;
import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
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

    public void enterAccountant() throws IOException, ClassNotFoundException {
        handler.write(new String("enterAccountant"));
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
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../../AccountantPackage/FXML/AccountantActions.fxml"));
            newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
            newStage.setScene(new Scene(root, 600, 400));
            newStage.show();
            Controller.CurrentStage = newStage;
        }
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
