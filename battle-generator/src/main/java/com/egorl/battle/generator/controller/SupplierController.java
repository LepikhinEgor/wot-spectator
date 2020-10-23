package com.egorl.battle.generator.controller;

import com.egorl.battle.generator.domain.BattleCreationRequest;
import com.egorl.battle.generator.services.BattleGenerator;
import com.egorl.battle.generator.services.BattleHolder;
import com.egorl.spectator.domain.dto.BattleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    private static final Logger log = LoggerFactory.getLogger(SupplierController.class);

    private BattleGenerator battleGenerator;

    private BattleHolder battleHolder;

    @Autowired
    public void setBattleGenerator(BattleGenerator battleGenerator) {
        this.battleGenerator = battleGenerator;
    }

    @Autowired
    public void setBattleHolder(BattleHolder battleHolder) {
        this.battleHolder = battleHolder;
    }

    @PostMapping("/battle/generate")
    public void addBattle(@RequestBody BattleCreationRequest battleCreationRequest) {
        try {
            BattleDto newBattle = battleGenerator.generateBattle(battleCreationRequest);
            battleHolder.saveBattle(newBattle);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @GetMapping("/battle/delete")
    public void removeBattle(@RequestParam(name = "battle_id") String battleId) {
        try {
            battleHolder.deleteBattle(battleId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @GetMapping("/battle/generate/{count}")
    public void generateBattles(@PathVariable("count") Integer count) {
        try {
            List<BattleDto> battles = battleGenerator.generateBattles(count);
            battleHolder.saveAll(battles);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @GetMapping("/battle/delete/all")
    public void deleteAllBattles() {
        try {
            battleHolder.deleteAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
