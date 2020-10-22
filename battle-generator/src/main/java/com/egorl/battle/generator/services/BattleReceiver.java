package com.egorl.battle.generator.services;

import com.egorl.battle.generator.config.MicroservicesProperties;
import com.egorl.spectator.domain.dto.BattleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class BattleReceiver {

    private static final Logger logger = LoggerFactory.getLogger(BattleReceiver.class);

    private MicroservicesProperties microservicesProperties;

    private BattleHolder battleHolder;

    private BattleSpyClient battleSpyClient;

    @Autowired
    public void setBattleSpyClient(BattleSpyClient battleSpyClient) {
        this.battleSpyClient = battleSpyClient;
    }

    @Autowired
    public void setBattleHolder(BattleHolder battleHolder) {
        this.battleHolder = battleHolder;
    }

    @Autowired
    public void setMicroservicesProperties(MicroservicesProperties microservicesProperties) {
        this.microservicesProperties = microservicesProperties;
    }

    @Scheduled(fixedRate = 1000)
    public void requestEnemiesPosition() {
        Collection<BattleDto> activeBattles = battleHolder.getAll();
        activeBattles.stream()
                .map(this::createBattleRequestUrl)
                .forEach(this::requestBattleInfo);
    }

    private void requestBattleInfo(String url) {
        battleSpyClient.asyncRequestBattleInfo(url, battleDto ->
                logger.info("Updated battle " + battleDto.getBattleId()));
    }

    private String createBattleRequestUrl(BattleDto battleDto) {
        return String.format("%s/battle/info?battle_id=%s&team=%d",
                microservicesProperties.getSpyUrl(),
                battleDto.getBattleId(),
                battleDto.getTeam());
    }
}
