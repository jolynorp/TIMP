package com.joly.vhickes.model.mappers;

import com.joly.vhickes.model.dto.VehicleDto;
import com.joly.vhickes.model.entities.Vehicle;

public interface IVehicleMapper {
    VehicleDto convert(Vehicle v);
}