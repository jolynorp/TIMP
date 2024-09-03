package com.joly.vhickes.controller;

import com.joly.vhickes.model.Habitat;
import javafx.stage.Stage;

public class MainControllerFactory {
    public MainController getMainController(Stage stage, Habitat model) {
        return new MainController(stage, model);
    }
}