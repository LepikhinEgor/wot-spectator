package com.egorl.battlespy.services.utils;

import com.egorl.spectator.domain.dto.BattleDto;
import com.egorl.spectator.domain.entities.Battle;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BattleDtoConverter {

    public BattleDto toDto(Battle battle) {
        BattleDto dto = new BattleDto();

        dto.setBattleKey(battle.getId().toString());
        dto.setMap(battle.getMap());

        return dto;
    }

    public Battle fromDto(BattleDto dto) {
        Battle battle = new Battle();
        battle.setId(UUID.fromString(dto.getBattleKey()));
        battle.setAuthor(null);
        battle.setMap(dto.getMap());

        return battle;
    }
}
