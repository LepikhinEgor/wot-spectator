package com.egorl.battlespy.repository;

import com.egorl.spectator.domain.entities.Battle;
import com.egorl.spectator.domain.entities.TankLocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TankLocationRepository extends CrudRepository<TankLocation, Long> {

    @Query("select tl from TankLocation tl join fetch tl.battle where tl.battle = :battle and tl.team = :team")
    List<TankLocation> findByBattleAndTeam(Battle battle, Integer team);

}
