package com.joly.vhickes.model.dto;

import com.joly.vhickes.model.spawners.ProbabilisticSpawnerParams;

public record HabitatParams(ProbabilisticSpawnerParams automobileParams, ProbabilisticSpawnerParams motorbikeParams) {
}