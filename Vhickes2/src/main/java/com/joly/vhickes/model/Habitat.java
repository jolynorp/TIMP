package com.joly.vhickes.model;

import com.joly.vhickes.model.dto.HabitatParams;
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
    private final int width;
    private final int height;

    public Habitat(IVehicleRepository vehicleRepository, int width, int height) {
        this.vehicleRepository = vehicleRepository;
        this.width = width;
        this.height = height;
    }

    public void start(HabitatParams params) {
        view.getChildren().clear();
        startTime = System.currentTimeMillis();

        this.vehicleSpawners = new IVehicleSpawner[]{
                new ProbabilisticSpawner(vehicleRepository, new AutomobileFactory(this.width, this.height), view, params.automobileParams()),
                new ProbabilisticSpawner(vehicleRepository, new MotorbikeFactory(this.width, this.height), view, params.motorbikeParams()),
        };
    }

    public void stop() {
        this.vehicleRepository.deleteAll();
    }

    public void update(long timeOffset) throws OutOfSpace {
        for (IVehicleSpawner spawner : this.vehicleSpawners) {
            spawner.trySpawn(timeOffset);
        }

        timeLabelText.set("time: " + new DecimalFormat("0.0").format((double) (System.currentTimeMillis() - startTime) / 1000));
    }

    public void setView(Pane view, Label timeLabel) {
        this.view = view;
        timeLabel.textProperty().bind(timeLabelText);
    }

    public IVehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }
}