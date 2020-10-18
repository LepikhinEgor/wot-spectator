package com.egorl.battle.generator.services;

import com.egorl.battle.generator.domain.BattleKey;
import com.egorl.spectator.domain.dto.BattleDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BattleHolder {

    HashMap<BattleKey, BattleDto> activeBattles = new HashMap<>();

    public void saveBattle(BattleDto battle) {
        BattleKey key = new BattleKey(UUID.fromString(battle.getBattleId()), battle.getTeam());
        activeBattles.put(key, battle);
    }

    public Collection<BattleDto> getAll() {
        return activeBattles.values();
    }

    public void deleteBattle(String battleId) {
        UUID uuid = UUID.fromString(battleId);
        List<BattleKey> keys =  Arrays.asList(new BattleKey(uuid, 1), new BattleKey(uuid,2));

        keys.forEach(battleKey -> activeBattles.remove(battleKey));
    }
}
