package com.egorl.spectator.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class BattleDto {

    @JsonProperty("map")
    private String map;

    @JsonProperty("battle_key")
    private String battleKey;

    @JsonProperty("enemies_location")
    List<TankLocationDto> enemiesLocation;

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getBattleKey() {
        return battleKey;
    }

    public void setBattleKey(String battleKey) {
        this.battleKey = battleKey;
    }

    public List<TankLocationDto> getEnemiesLocation() {
        return enemiesLocation;
    }

    public void setEnemiesLocation(List<TankLocationDto> enemiesLocation) {
        this.enemiesLocation = enemiesLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleDto battleDto = (BattleDto) o;
        return Objects.equals(map, battleDto.map) &&
                Objects.equals(battleKey, battleDto.battleKey) &&
                Objects.equals(enemiesLocation, battleDto.enemiesLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, battleKey, enemiesLocation);
    }

    @Override
    public String toString() {
        return "BattleDto{" +
                "map='" + map + '\'' +
                ", battleKey='" + battleKey + '\'' +
                ", enemiesLocation=" + enemiesLocation +
                '}';
    }
}
