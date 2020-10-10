package com.egorl.battlespy.controllers;

import com.egorl.battlespy.domain.dto.BattleInfoDto;
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
    public Mono<BattleInfoDto> getBattleInfo(@RequestParam(name = "battleId", required = true) String battleId,
                                             @RequestParam(name = "team", required = true) Integer team) {
        return battleInfoService.getBattleInfo(battleId, team);
    }

    @PostMapping("/battle/info")
    public void saveBattleInfo(@RequestBody BattleInfoDto battleInfoDto) {
        battleInfoService.saveBattleInfo(battleInfoDto);
    }
}
