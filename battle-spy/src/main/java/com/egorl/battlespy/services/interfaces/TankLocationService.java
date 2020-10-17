package com.egorl.battlespy.services.interfaces;

import com.egorl.spectator.domain.entities.Battle;
import com.egorl.spectator.domain.entities.TankLocation;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TankLocationService {

    Mono<Void> updateLocations(List<TankLocation> locations);

    Mono<List<TankLocation>> getByBattleAndTeam(Battle battle, Integer team);
}
