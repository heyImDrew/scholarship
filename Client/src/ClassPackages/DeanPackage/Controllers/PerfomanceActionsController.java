package ClassPackages.DeanPackage.Controllers;

import ClassPackages.Interfaces.StoreIdInterface;
import ClassPackages.MainPackage.Controllers.Controller;
import ClassPackages.MainPackage.Models.Client;
import ClassPackages.MainPackage.Models.Handler;
import ClassPackages.MainPackage.Models.ScreenHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PerfomanceActionsController implements Initializable, StoreIdInterface {

    public Button backButton;
    int stored_id;
    Handler handler = Client.get_handler();

    public void backButton(ActionEvent actionEvent) throws IOException {
        handler.write("returnId");
        handler.write(get_stored_id());
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryActions.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    @Override
    public int get_stored_id() {
        return this.stored_id;
    }

    @Override
    public void set_stored_id(int new_id) {
        this.stored_id = new_id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            set_stored_id((Integer)handler.read());
            System.out.println(get_stored_id());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void ratingButton(ActionEvent actionEvent) throws IOException {
        handler.write("returnId");
        handler.write(get_stored_id());
        Controller.CurrentStage = (Stage) backButton.getScene().getWindow();
        Controller.CurrentStage.close();
        ScreenHandler screen = new ScreenHandler("../../DeanPackage/FXML/DeaneryStudentsRatingsActions.fxml", "BSUIR TASK 2020");
        Controller.CurrentStage = screen.get_new_stage();
    }

    public void graphButton(ActionEvent actionEvent) throws IOException {
        handler.write("loadGraph");
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Успеваемость");
        window.setMinWidth(300);

        Button closeButton = new Button("Закрыть");
        closeButton.setOnAction(e -> window.close());

        Label label = new Label();
        label.setText("Количество студентов с определенным средним баллом");
        label.setStyle("-fx-font-size: 20;");
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("9", 2),
                        new PieChart.Data("8", 1),
                        new PieChart.Data("5", 1),
                        new PieChart.Data("6", 2));

        final PieChart chart = new PieChart(pieChartData);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, chart, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 500);
        window.setScene(scene);
        window.showAndWait();
    }
}
