package ClassPackages.MainPackage.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenHandler {
    private Stage newStage;
    private Integer id;
    public ScreenHandler(String url, String title) throws IOException {
        newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(url));
        newStage.setTitle(title);
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
    }
    public ScreenHandler(String url, String title, Integer id) throws IOException {
        this.id = id;
        newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(url));
        newStage.setTitle(title);
        newStage.setScene(new Scene(root, 600, 400));
        newStage.show();
    }
    public Stage get_new_stage() {
        return this.newStage;
    }
    public Integer get_id() {
        return this.id;
    }
}
