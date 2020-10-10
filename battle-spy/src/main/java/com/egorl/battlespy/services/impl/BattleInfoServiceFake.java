package com.egorl.battlespy.services.impl;

import com.egorl.battlespy.domain.dto.BattleInfoDto;
import com.egorl.battlespy.domain.entities.TankLocation;
import com.egorl.battlespy.services.interfaces.BattleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class BattleInfoServiceFake implements BattleInfoService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<BattleInfoDto> getBattleInfo(String battleKey, Integer teamNumber) {
        return Mono.just(buildDto(battleKey));
    }

    @Override
    public void saveBattleInfo(BattleInfoDto dto) {
        logger.info(dto.toString());
    }

    private BattleInfoDto buildDto(String battleKey) {
        BattleInfoDto battleInfoDto = new BattleInfoDto();

        battleInfoDto.setBattleKey(battleKey);
        battleInfoDto.setMap("Cliff");
        battleInfoDto.setEnemiesLocation(generateEnemiesLocation());

        return battleInfoDto;
    }

    private List<TankLocation> generateEnemiesLocation() {
        List<TankLocation> enemiesLocation = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            TankLocation tankLocation = new TankLocation();

            tankLocation.setLocationX(Math.random() * 1000);
            tankLocation.setLocationY(Math.random() * 1000);
            tankLocation.setHullAngle(Math.random()*360);
            tankLocation.setTurretAngle(Math.random()*360);

            enemiesLocation.add(tankLocation);
        }

        return enemiesLocation;
    }
}
