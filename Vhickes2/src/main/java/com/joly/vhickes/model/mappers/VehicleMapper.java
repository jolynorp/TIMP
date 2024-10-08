package com.joly.vhickes.model.mappers;

import com.joly.vhickes.model.dto.VehicleDto;
import com.joly.vhickes.model.entities.Vehicle;

public class VehicleMapper implements IVehicleMapper {
    @Override
    public VehicleDto convert(Vehicle v) {
        String classname = v.getClass().getName();
        return new VehicleDto(classname.substring(classname.lastIndexOf('.') + 1), v.getX(), v.getY());
    }
}