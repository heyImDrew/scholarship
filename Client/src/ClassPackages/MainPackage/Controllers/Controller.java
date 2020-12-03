package ClassPackages.MainPackage.Controllers;

import ClassPackages.MainPackage.Models.Client;
//import ClassPackages.MainPackage.Models.Handler;
import ClassPackages.MainPackage.Models.Handler;
import ClassPackages.MainPackage.Models.ScreenHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Button studentButton;
    public static Stage CurrentStage;
    Handler handler = Client.get_handler();

    public void studentAction() throws IOException {
        CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../StudentPackage/FXML/StudentAuthorize.fxml", "BSUIR TASK 2020");
        CurrentStage = screen.get_new_stage();
    }

    public void workerAction() throws IOException {
        CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../MainPackage/FXML/DeaneryAccountantAuthorizeMenu.fxml", "BSUIR TASK 2020");
        CurrentStage = screen.get_new_stage();
    }

    public void deanAction() throws IOException {
        CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryAuthorize.fxml", "BSUIR TASK 2020");
        CurrentStage = screen.get_new_stage();
    }

    public void accountantAction() throws IOException {
        CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../AccountantPackage/FXML/AccountantAuthorize.fxml", "BSUIR TASK 2020");
        CurrentStage = screen.get_new_stage();
    }

    public void back() throws IOException {
        CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../FXML/MainAuthorize.fxml", "BSUIR TASK 2020");
        CurrentStage = screen.get_new_stage();
    }
}
