package com.egorl.battlespy.repository;

import com.egorl.spectator.domain.entities.Battle;
import com.egorl.spectator.domain.entities.TankLocation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TankLocationRepository extends CrudRepository<TankLocation, Long> {

    List<TankLocation> findByBattleAndTeam(Battle battle, Integer team);

}
