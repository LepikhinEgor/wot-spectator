package com.egorl.battlespy.services.interfaces;

import com.egorl.spectator.domain.dto.BattleDto;
import reactor.core.publisher.Mono;

public interface BattleService {

    Mono<BattleDto> getBattle(String battle, Integer teamNumber);

    Mono<Void> updateBattle(BattleDto dto);
}
