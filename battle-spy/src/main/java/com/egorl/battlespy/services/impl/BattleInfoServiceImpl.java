package com.egorl.battlespy.services.impl;

import com.egorl.battlespy.domain.dto.BattleResponseDto;
import com.egorl.battlespy.domain.entities.Battle;
import com.egorl.battlespy.repository.BattleRepository;
import com.egorl.battlespy.repository.TankLocationRepository;
import com.egorl.battlespy.services.interfaces.BattleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class BattleInfoServiceImpl implements BattleInfoService {

    private BattleRepository battleRepository;

    private TankLocationRepository tankLocationRepository;

    @Autowired
    public void setTankLocationRepository(TankLocationRepository tankLocationRepository) {
        this.tankLocationRepository = tankLocationRepository;
    }

    @Autowired
    public void setBattleRepository(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    @Override
    public Mono<BattleResponseDto> getBattle(String battleId, Integer teamNumber) {
        return Mono.fromCallable(() -> battleRepository.findById(UUID.fromString(battleId)))
                .flatMap(optionalBattle -> optionalBattle.map(Mono::just).orElseGet(Mono::empty)
                        .zipWhen(battle -> Mono.fromCallable(() -> tankLocationRepository.findByBattleAndTeam(battle, getEnemiesTeam(teamNumber))))
                        .map(objects -> {
                            BattleResponseDto dto = toDto(objects.getT1());
                            dto.setEnemiesLocation(objects.getT2());

                            return dto;
                        }));
    }

    @Override
    @Transactional
    public Mono<Void> updateBattle(BattleResponseDto dto) {
        return Mono.just(buildBattle(dto))
                .flatMap(battle -> Mono.fromCallable(() ->battleRepository.save(battle)))
                .doOnNext(battle ->  {
                    dto.getEnemiesLocation()
                            .forEach(tankLocation -> {
                                tankLocation.setBattle(battle);
                                tankLocation.setTiming(new Date());
                                tankLocation.setTeam(1);
                                tankLocation.setHullAngle(1D);
                                tankLocation.setTurretAngle(1D);
                                tankLocation.setLocationX(1D);
                                tankLocation.setLocationY(1D);
                            });
                    tankLocationRepository.saveAll(dto.getEnemiesLocation());
                })
                .then();
    }

    private int getEnemiesTeam(Integer team) {
        if (team == 1)
            return 2;
        if (team == 2)
            return 1;
        throw new  IllegalArgumentException();
    }

    private BattleResponseDto toDto(Battle battle) {
        BattleResponseDto dto = new BattleResponseDto();
        dto.setBattleKey(battle.getId().toString());
        dto.setMap(battle.getMap());

        return dto;
    }

    private Battle buildBattle(BattleResponseDto dto) {
        Battle battle = new Battle();
        battle.setId(UUID.fromString(dto.getBattleKey()));
        battle.setAuthor(null);
        battle.setMap(dto.getMap());

        return battle;
    }
}
