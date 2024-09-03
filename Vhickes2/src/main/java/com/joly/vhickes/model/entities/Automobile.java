package com.joly.vhickes.model.entities;

import javafx.scene.image.Image;

import java.util.Objects;

public class Automobile extends Vehicle implements IBehavior {
    private final static Image image = new Image(Objects.requireNonNull(Automobile.class.getResourceAsStream("/com/joly/vhickes/images/automobile.jpg")));

    public Automobile(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public Image getImage() {
        return image;
    }
}