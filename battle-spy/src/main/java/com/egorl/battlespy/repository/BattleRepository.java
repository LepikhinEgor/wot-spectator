package com.egorl.battlespy.repository;

import com.egorl.battlespy.domain.entities.Battle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BattleRepository extends CrudRepository<Battle, UUID> {
}
