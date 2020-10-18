package com.egorl.battle.generator.services;

import com.egorl.spectator.domain.dto.BattleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BattleSupplier {

    private BattleHolder battleHolder;

    private BattleGenerator battleGenerator;

    private KafkaTemplate<String, BattleDto> kafkaTemplate;

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, BattleDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public void setBattleGenerator(BattleGenerator battleGenerator) {
        this.battleGenerator = battleGenerator;
    }

    @Autowired
    public void setBattleHolder(BattleHolder battleHolder) {
        this.battleHolder = battleHolder;
    }

    @Scheduled(fixedRate = 500)
    public void updateBattles() {
        Collection<BattleDto> activeBattles = battleHolder.getAll();

        for (BattleDto battle: activeBattles) {
            BattleDto updatedBattle = battleGenerator.updateBattle(battle);

            sendToKafka(battle);

            battleHolder.saveBattle(updatedBattle);
        }
    }

    private void sendToKafka(BattleDto battle) {
        kafkaTemplate.send("battle.info", battle);
    }
}
