package com.joly.vhickes.controller;

import com.joly.vhickes.model.Habitat;

public class MainControllerFactory {
    public MainController getMainController(Habitat model) {
        return new MainController(model);
    }
}