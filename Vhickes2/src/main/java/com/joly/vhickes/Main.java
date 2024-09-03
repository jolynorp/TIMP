package com.joly.vhickes;

import com.joly.vhickes.controller.MainController;
import com.joly.vhickes.controller.MainControllerFactory;
import com.joly.vhickes.model.Habitat;
import com.joly.vhickes.model.repositories.FixedArrayVehicleRepository;
import com.joly.vhickes.model.repositories.IVehicleRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        IVehicleRepository vehicleRepository = new FixedArrayVehicleRepository(10);
        Habitat model = new Habitat(vehicleRepository, 800, 800);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/joly/vhickes/fxml/main-view.fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> new MainControllerFactory().getMainController(stage, model));

        Scene scene = new Scene(fxmlLoader.load(), 800, 800);

        final MainController controller = fxmlLoader.getController();
        stage.setOnShown(controller::adjustView);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}