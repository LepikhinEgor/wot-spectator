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
        dto.setHp(tankLocation.getHp());
        dto.setNickname(tankLocation.getNickname());
        dto.setTankId(tankLocation.getTankId());

        return dto;
    }

    public TankLocation fromDto(TankLocationDto dto) {
        TankLocation tankLocation = new TankLocation();

        tankLocation.setLocationY(dto.getLocationY());
        tankLocation.setLocationX(dto.getLocationX());
        tankLocation.setTurretAngle(dto.getTurretAngle());
        tankLocation.setHullAngle(dto.getHullAngle());
        tankLocation.setHp(dto.getHp());
        tankLocation.setNickname(dto.getNickname());
        tankLocation.setTankId(dto.getTankId());

        return tankLocation;
    }
}
