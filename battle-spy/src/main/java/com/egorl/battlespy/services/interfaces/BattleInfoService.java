package com.egorl.battlespy.services.interfaces;

import com.egorl.spectator.domain.dto.BattleDto;
import reactor.core.publisher.Mono;

public interface BattleInfoService {

    Mono<BattleDto> getBattle(String battle, Integer teamNumber);

    Mono<Void> updateBattle(BattleDto dto);
}
