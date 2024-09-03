module ru.nstu.vehicles.app {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.nstu.vehicles.app to javafx.fxml;
    opens ru.nstu.vehicles.app.controller to javafx.fxml;
    opens ru.nstu.vehicles.app.view.components to javafx.fxml;

    exports ru.nstu.vehicles.app;
    exports ru.nstu.vehicles.app.controller;
    exports ru.nstu.vehicles.app.model;
    exports ru.nstu.vehicles.app.model.repository;
    exports ru.nstu.vehicles.app.model.service;
    exports ru.nstu.vehicles.app.view;
    exports ru.nstu.vehicles.app.view.components;
}