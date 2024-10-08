package com.joly.vhickes.model;

import com.joly.vhickes.model.entities.Automobile;
import com.joly.vhickes.model.entities.Motorbike;
import com.joly.vhickes.model.factories.AutomobileFactory;
import com.joly.vhickes.model.factories.MotorbikeFactory;
import com.joly.vhickes.model.repositories.IVehicleRepository;
import com.joly.vhickes.model.repositories.OutOfSpace;
import com.joly.vhickes.model.spawners.IVehicleSpawner;
import com.joly.vhickes.model.spawners.ProbabilisticSpawner;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.text.DecimalFormat;


public class Habitat {
    private final IVehicleRepository vehicleRepository;
    private IVehicleSpawner[] vehicleSpawners = null;
    private Pane view;
    private long startTime = 0;
    private final StringProperty timeLabelText = new SimpleStringProperty("time: 0");
    private final StringProperty vehiclesInfoText = new SimpleStringProperty("motorbikes: 0\nautomobiles: 0");
    private final int width;
    private final int height;

    public Habitat(IVehicleRepository vehicleRepository, int width, int height) {
        this.vehicleRepository = vehicleRepository;
        this.width = width;
        this.height = height;
    }

    public void start() {
        view.getChildren().clear();
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.vehicleRepository.deleteAll();
    }

    public void update(long timeOffset) throws OutOfSpace {
        for (IVehicleSpawner spawner : this.vehicleSpawners) {
            spawner.trySpawn(timeOffset);
        }

        timeLabelText.set("time: " + new DecimalFormat("0.0").format((double) (System.currentTimeMillis() - startTime) / 1000));
        vehiclesInfoText.set("motorbikes: "
                + vehicleRepository.getAll().filter(vehicle -> vehicle instanceof Motorbike).count()
                + "\nautomobiles: "
                + vehicleRepository.getAll().filter(vehicle -> vehicle instanceof Automobile).count()
        );
    }

    public void setView(Pane view, Label timeLabel, Label vehiclesInfo) {
        this.view = view;

        this.vehicleSpawners = new IVehicleSpawner[]{
                new ProbabilisticSpawner(vehicleRepository, new AutomobileFactory(this.width, this.height), view, 2000, 0.5f),
                new ProbabilisticSpawner(vehicleRepository, new MotorbikeFactory(this.width, this.height), view, 3000, 0.7f),
        };
        timeLabel.textProperty().bind(timeLabelText);
        vehiclesInfo.textProperty().bind(vehiclesInfoText);
    }
}