package ClassPackages.MainPackage.Controllers;

import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Controller {
    public Button strudentButton;
    public static Stage CurrentStage;

    public void studentAction() throws IOException {
        Handler handler = Client.get_handler();

//        String request = "req";
//        System.out.println("** REQUEST: '" + request + "'");
//        handler.write_line(request);
//        String response = handler.read_line();
//        System.out.println("** RESPONSE: '" + response + "'");

        CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../StudentPackage/FXML/StudentAuthorize.fxml"));
        newStage.setTitle("Student Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        CurrentStage = newStage;
        newStage.show();
    }

    public void workerAction() throws IOException {
        Handler handler = Client.get_handler();

//        String request = "req";
//        System.out.println("** REQUEST: '" + request + "'");
//        handler.write_line(request);
//        String response = handler.read_line();
//        System.out.println("** RESPONSE: '" + response + "'");

        CurrentStage.close();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../DeanPackage/FXML/DeaneryAccountantAuthorizeMenu.fxml"));
        newStage.setTitle("Worker Authorize | BSUIR IEF 2020");
        newStage.setScene(new Scene(root, 600, 400));
        CurrentStage = newStage;
        newStage.show();
    }
}
