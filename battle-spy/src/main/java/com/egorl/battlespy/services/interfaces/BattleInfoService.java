package com.egorl.battlespy.services.interfaces;

import com.egorl.battlespy.domain.dto.BattleResponseDto;
import reactor.core.publisher.Mono;

public interface BattleInfoService {

    Mono<BattleResponseDto> getBattle(String battle, Integer teamNumber);

    Mono<Void> updateBattle(BattleResponseDto dto);
}
