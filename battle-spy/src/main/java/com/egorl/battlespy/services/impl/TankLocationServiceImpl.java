package com.egorl.battlespy.services.impl;

import com.egorl.battlespy.repository.TankLocationRepository;
import com.egorl.battlespy.services.interfaces.TankLocationService;
import com.egorl.spectator.domain.entities.Battle;
import com.egorl.spectator.domain.entities.TankLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TankLocationServiceImpl implements TankLocationService {

    private TankLocationRepository tankLocationRepository;

    @Autowired
    public void setTankLocationRepository(TankLocationRepository tankLocationRepository) {
        this.tankLocationRepository = tankLocationRepository;
    }

    @Override
    public Mono<Void> updateLocations(List<TankLocation> locations) {
        return Mono.fromCallable(() -> tankLocationRepository.saveAll(locations))
                .then();
    }

    @Override
    public Mono<List<TankLocation>> getByBattleAndTeam(Battle battle, Integer team) {
        return Mono.fromCallable(() -> tankLocationRepository.findByBattleAndTeam(battle, team));
    }
}
