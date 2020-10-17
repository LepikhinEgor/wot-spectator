package com.egorl.battlespy.services.utils;

import com.egorl.spectator.domain.dto.TankLocationDto;
import com.egorl.spectator.domain.entities.TankLocation;
import org.springframework.stereotype.Service;

@Service
public class TankLocationDtoConverter {

    public TankLocationDto toDto(TankLocation tankLocation) {
        TankLocationDto dto = new TankLocationDto();

        dto.setLocationX(tankLocation.getLocationX());
        dto.setLocationY(tankLocation.getLocationY());
        dto.setTurretAngle(tankLocation.getTurretAngle());
        dto.setHullAngle(tankLocation.getHullAngle());
        dto.setTeam(tankLocation.getTeam());
        dto.setHp(tankLocation.getHp());

        return dto;
    }

    public TankLocation fromDto(TankLocationDto dto) {
        TankLocation tankLocation = new TankLocation();

        tankLocation.setLocationY(dto.getLocationY());
        tankLocation.setLocationX(dto.getLocationX());
        tankLocation.setTurretAngle(dto.getTurretAngle());
        tankLocation.setHullAngle(dto.getHullAngle());
        tankLocation.setTeam(dto.getTeam());
        tankLocation.setHp(dto.getHp());

        return tankLocation;
    }
}
