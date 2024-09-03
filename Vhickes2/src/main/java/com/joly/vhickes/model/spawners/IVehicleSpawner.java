package com.joly.vhickes.model.spawners;

import com.joly.vhickes.model.repositories.OutOfSpace;

public interface IVehicleSpawner {
    void trySpawn(long timeOffset) throws OutOfSpace;
}