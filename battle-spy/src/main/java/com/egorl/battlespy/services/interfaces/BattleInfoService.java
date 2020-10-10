package com.egorl.battlespy.services.interfaces;

import com.egorl.battlespy.domain.dto.BattleInfoDto;
import reactor.core.publisher.Mono;

public interface BattleInfoService {

    Mono<BattleInfoDto> getBattleInfo(String battle, Integer teamNumber);

    void saveBattleInfo(BattleInfoDto dto);
}
