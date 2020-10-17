package com.egorl.battlespy.controllers;

import com.egorl.spectator.domain.dto.BattleDto;
import com.egorl.battlespy.services.interfaces.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class BattleSpyController {

    private BattleService battleService;

    @Autowired
    public void setBattleService(@Qualifier("battleServiceImpl") BattleService battleService) {
        this.battleService = battleService;
    }

    @GetMapping("/battle/info")
    public Mono<BattleDto> getBattleInfo(@RequestParam(name = "battleId", required = true) String battleId,
                                         @RequestParam(name = "team", required = true) Integer team) {
        return battleService.getBattle(battleId, team);
    }

    @PostMapping("/battle/info")
    public Mono<Void> saveBattleInfo(@RequestBody BattleDto battleDto) {
        return battleService.updateBattle(battleDto);
    }
}
