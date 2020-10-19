package com.egorl.battlespy.services.impl;

import com.egorl.battlespy.services.interfaces.BattleService;
import com.egorl.spectator.domain.dto.BattleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BattleConsumerService {

    private static final Logger log = LoggerFactory.getLogger(BattleConsumerService.class);

    private BattleService battleService;

    @Autowired
    public void setBattleService(BattleService battleService) {
        this.battleService = battleService;
    }

    @KafkaListener(id = "spy", topics = {"battle.info"}, containerFactory = "singleFactory")
    public void consumeBattle(BattleDto battleDto) {
        try {
            battleService.updateBattle(battleDto).block();
        } catch (Exception e) {
            log.error("Error during update battle", e);
        }
    }
}
