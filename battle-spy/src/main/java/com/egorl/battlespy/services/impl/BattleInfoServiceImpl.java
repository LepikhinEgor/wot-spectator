package com.egorl.battlespy.services.impl;

import com.egorl.battlespy.domain.dto.BattleDto;
import com.egorl.battlespy.domain.dto.TankLocationDto;
import com.egorl.battlespy.domain.entities.Battle;
import com.egorl.battlespy.domain.entities.TankLocation;
import com.egorl.battlespy.repository.BattleRepository;
import com.egorl.battlespy.repository.TankLocationRepository;
import com.egorl.battlespy.services.interfaces.BattleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public Mono<BattleDto> getBattle(String battleId, Integer teamNumber) {
        return Mono.fromCallable(() -> battleRepository.findById(UUID.fromString(battleId)))
                .flatMap(optionalBattle -> optionalBattle.map(Mono::just).orElseGet(Mono::empty)
                        .zipWhen(battle -> Mono.fromCallable(() -> tankLocationRepository.findByBattleAndTeam(battle, getEnemiesTeam(teamNumber))))
                        .map(tuple -> {
                            BattleDto dto = toDto(tuple.getT1());
                            List<TankLocationDto> locationsDto = tuple.getT2().stream()
                                    .map(this::toDto)
                                    .collect(Collectors.toList());
                            dto.setEnemiesLocation(locationsDto);

                            return dto;
                        }));
    }

    @Override
    @Transactional
    public Mono<Void> updateBattle(BattleDto dto) {
        return Mono.just(buildBattle(dto))
                .flatMap(battle -> Mono.fromCallable(() -> battleRepository.save(battle)))
                .zipWhen(battle -> Mono.fromCallable(() ->
                        tankLocationRepository.findByBattleAndTeam(battle, getTanksTeam(dto.getEnemiesLocation()))))
                .doOnNext(tuple ->  {
                    Battle battle = tuple.getT1();
                    List<TankLocation> oldLocations = tuple.getT2();
                    List<TankLocation> newLocations = dto.getEnemiesLocation().stream()
                            .map(this::buildTankLocation)
                            .collect(Collectors.toList());

                    for (TankLocation newLocation : newLocations)
                        for (TankLocation oldLocation : oldLocations)
                            if (oldLocation.getNickname().equals(newLocation.getNickname())) {
                                newLocation.setId(oldLocation.getId());
                                newLocation.setTiming(new Date());
                                newLocation.setBattle(battle);
                            }

                    tankLocationRepository.saveAll(newLocations);
                })
                .then();
    }

    private Integer getTanksTeam(List<TankLocationDto> tankLocationsDto) {
        if (tankLocationsDto.size() != 0) {
            Integer team = tankLocationsDto.get(0).getTeam();

            tankLocationsDto.forEach(dto -> {
                if (!dto.getTeam().equals(team))
                    throw new IllegalArgumentException("All tanks must be in same team");
            });

            return team;
        }

        throw new IllegalArgumentException("Tanks list must be not empty");
    }

    private TankLocation buildTankLocation(TankLocationDto dto) {
        TankLocation tankLocation = new TankLocation();
        tankLocation.setLocationX(dto.getLocationX());
        tankLocation.setLocationY(dto.getLocationY());
        tankLocation.setTurretAngle(dto.getTurretAngle());
        tankLocation.setHullAngle(dto.getHullAngle());
        tankLocation.setTeam(dto.getTeam());
        tankLocation.setHp(dto.getHp());

        return tankLocation;
    }

    private int getEnemiesTeam(Integer team) {
        if (team == 1)
            return 2;
        if (team == 2)
            return 1;
        throw new  IllegalArgumentException();
    }

    private BattleDto toDto(Battle battle) {
        BattleDto dto = new BattleDto();
        dto.setBattleKey(battle.getId().toString());
        dto.setMap(battle.getMap());

        return dto;
    }

    private TankLocationDto toDto(TankLocation tankLocation) {
        TankLocationDto dto = new TankLocationDto();

        dto.setLocationX(tankLocation.getLocationX());
        dto.setLocationY(tankLocation.getLocationY());
        dto.setTurretAngle(tankLocation.getTurretAngle());
        dto.setHullAngle(tankLocation.getHullAngle());
        dto.setTeam(tankLocation.getTeam());
        dto.setHp(tankLocation.getHp());

        return dto;
    }

    private Battle buildBattle(BattleDto dto) {
        Battle battle = new Battle();
        battle.setId(UUID.fromString(dto.getBattleKey()));
        battle.setAuthor(null);
        battle.setMap(dto.getMap());

        return battle;
    }
}
