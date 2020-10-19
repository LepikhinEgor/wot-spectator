package com.egorl.battlespy.services.impl;

import com.egorl.battlespy.repository.BattleRepository;
import com.egorl.battlespy.services.interfaces.BattleService;
import com.egorl.battlespy.services.interfaces.TankLocationService;
import com.egorl.battlespy.services.utils.BattleDtoConverter;
import com.egorl.battlespy.services.utils.TankLocationDtoConverter;
import com.egorl.spectator.domain.dto.BattleDto;
import com.egorl.spectator.domain.dto.TankLocationDto;
import com.egorl.spectator.domain.entities.Battle;
import com.egorl.spectator.domain.entities.TankLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BattleServiceImpl implements BattleService {

    private BattleRepository battleRepository;

    private BattleDtoConverter battleDtoConverter;

    private TankLocationDtoConverter tankLocationDtoConverter;

    private TankLocationService tankLocationService;

    @Autowired
    public void setTankLocationDtoConverter(TankLocationDtoConverter tankLocationDtoConverter) {
        this.tankLocationDtoConverter = tankLocationDtoConverter;
    }

    @Autowired
    public void setBattleDtoConverter(BattleDtoConverter battleDtoConverter) {
        this.battleDtoConverter = battleDtoConverter;
    }

    @Autowired
    public void setTankLocationService(TankLocationService tankLocationService) {
        this.tankLocationService = tankLocationService;
    }

    @Autowired
    public void setBattleRepository(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    @Override
    @Transactional
    public Mono<BattleDto> getBattle(String battleId, Integer teamNumber) {
        return Mono.fromCallable(() -> battleRepository.findById(UUID.fromString(battleId)))
                .flatMap(optionalBattle -> optionalBattle.map(Mono::just).orElseGet(Mono::empty)
                        .zipWhen(battle -> tankLocationService.getByBattleAndTeam(battle, getEnemiesTeam(teamNumber))))
                        .map(tuple -> {
                            BattleDto dto = battleDtoConverter.toDto(tuple.getT1());
                            List<TankLocationDto> locationsDto = tuple.getT2().stream()
                                    .map(tankLocationDtoConverter::toDto)
                                    .collect(Collectors.toList());
                            dto.setTanksLocation(locationsDto);

                            return dto;
                        });
    }

    @Override
    @Transactional
    public Mono<Void> updateBattle(BattleDto dto) {
        return Mono.just(battleDtoConverter.fromDto(dto))
                .flatMap(battle -> Mono.fromCallable(() -> battleRepository.save(battle)))
                .zipWhen(battle -> tankLocationService.getByBattleAndTeam(battle, dto.getTeam()))
                .flatMap(tuple ->  {
                    List<TankLocation> actualLocations = getActualTanksLocations(dto);
                    List<TankLocation> updatedLocations = updateTankLocations(tuple.getT2(), actualLocations, tuple.getT1());

                    return tankLocationService.updateLocations(updatedLocations);
                })
                .then();
    }

    private List<TankLocation> getActualTanksLocations(BattleDto battleDto) {
        return battleDto.getTanksLocation().stream()
                .map(tankLocationDtoConverter::fromDto)
                .peek(tankLocation -> tankLocation.setTeam(battleDto.getTeam()))
                .collect(Collectors.toList());
    }

    private List<TankLocation> updateTankLocations(List<TankLocation> oldLocations, List<TankLocation> newLocations, Battle battle) {
        for (TankLocation newLocation : newLocations) {
            newLocation.setTiming(new Date());
            newLocation.setBattle(battle);

            for (TankLocation oldLocation : oldLocations)
                if (oldLocation.getNickname().equals(newLocation.getNickname())) {
                    newLocation.setId(oldLocation.getId());
                }
        }

        return newLocations;
    }

    private int getEnemiesTeam(Integer team) {
        if (team == 1)
            return 2;
        if (team == 2)
            return 1;
        throw new  IllegalArgumentException();
    }

}
