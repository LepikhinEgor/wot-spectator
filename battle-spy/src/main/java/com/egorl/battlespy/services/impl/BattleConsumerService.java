package com.egorl.battlespy.services.impl;

import com.egorl.spectator.domain.dto.BattleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BattleConsumerService {

    private static final Logger log = LoggerFactory.getLogger(BattleConsumerService.class);

    @KafkaListener(id = "spy", topics = {"battle.info"}, containerFactory = "singleFactory")
    public void consumeBattle(BattleDto battleDto) {
        log.info(battleDto.toString());
    }
}
