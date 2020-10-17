package com.egorl.battlespy.repository;

import com.egorl.spectator.domain.entities.Battle;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BattleRepository extends CrudRepository<Battle, UUID> {
}
