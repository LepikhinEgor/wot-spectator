package com.egorl.battlespy.controllers;

import com.egorl.battlespy.domain.dto.BattleResponseDto;
import com.egorl.battlespy.services.interfaces.BattleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class BattleSpyController {

    private BattleInfoService battleInfoService;

    @Autowired
    public void setBattleInfoService(BattleInfoService battleInfoService) {
        this.battleInfoService = battleInfoService;
    }

    @GetMapping("/battle/info")
    public Mono<BattleResponseDto> getBattleInfo(@RequestParam(name = "battleId", required = true) String battleId,
                                                 @RequestParam(name = "team", required = true) Integer team) {
        return battleInfoService.getBattleInfo(battleId, team);
    }

    @PostMapping("/battle/info")
    public void saveBattleInfo(@RequestBody BattleResponseDto battleResponseDto) {
        battleInfoService.updateBattle(battleResponseDto);
    }
}
