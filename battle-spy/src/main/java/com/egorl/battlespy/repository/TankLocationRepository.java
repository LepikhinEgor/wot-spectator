package com.egorl.battlespy.repository;

import com.egorl.battlespy.domain.entities.Battle;
import com.egorl.battlespy.domain.entities.TankLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TankLocationRepository extends CrudRepository<TankLocation, Long> {

    List<TankLocation> findByBattleAndTeam(Battle battle, Integer team);
}
