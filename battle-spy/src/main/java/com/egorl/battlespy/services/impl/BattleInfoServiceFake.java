package com.egorl.battlespy.services.impl;

import com.egorl.battlespy.domain.dto.BattleResponseDto;
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
    public Mono<BattleResponseDto> getBattle(String battleKey, Integer teamNumber) {
        return Mono.just(buildDto(battleKey));
    }

    @Override
    public Mono<Void> updateBattle(BattleResponseDto dto) {
        logger.info(dto.toString());
        return Mono.empty();
    }

    private BattleResponseDto buildDto(String battleKey) {
        BattleResponseDto battleResponseDto = new BattleResponseDto();

        battleResponseDto.setBattleKey(battleKey);
        battleResponseDto.setMap("Cliff");
        battleResponseDto.setEnemiesLocation(generateEnemiesLocation());

        return battleResponseDto;
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
