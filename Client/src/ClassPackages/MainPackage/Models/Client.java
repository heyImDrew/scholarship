package ClassPackages.MainPackage.Models;

import ClassPackages.MainPackage.Controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.Control;
import java.io.IOException;
import java.util.Scanner;

public class Client extends Application {
    private static Handler handler;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        handler = new Handler("127.0.0.1", 8030);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (Controller.CurrentStage != null) {Controller.CurrentStage.close();}
        ScreenHandler screen = new ScreenHandler("../FXML/MainAuthorize.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    public static Handler get_handler() {
        return handler;
    }
}
