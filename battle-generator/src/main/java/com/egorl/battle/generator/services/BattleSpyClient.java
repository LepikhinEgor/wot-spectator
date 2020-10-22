package com.egorl.battle.generator.services;

import com.egorl.spectator.domain.dto.BattleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;

@Service
public class BattleSpyClient {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void asyncRequestBattleInfo(String url, Consumer<BattleDto> consumer) {
        Thread newThread = new Thread(() -> {
            BattleDto receivedBattleDto = restTemplate.getForObject(url, BattleDto.class);
            consumer.accept(receivedBattleDto);
        });
        newThread.start();
    }
}
