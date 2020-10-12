package com.egorl.battlespy.domain.dto;

import com.egorl.battlespy.domain.entities.TankLocation;

import java.util.List;
import java.util.Objects;

public class BattleDto {

    private String map;

    private String battleKey;

    List<TankLocation> enemiesLocation;

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

    public List<TankLocation> getEnemiesLocation() {
        return enemiesLocation;
    }

    public void setEnemiesLocation(List<TankLocation> enemiesLocation) {
        this.enemiesLocation = enemiesLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleDto that = (BattleDto) o;
        return Objects.equals(map, that.map) &&
                Objects.equals(battleKey, that.battleKey) &&
                Objects.equals(enemiesLocation, that.enemiesLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, battleKey, enemiesLocation);
    }

    @Override
    public String toString() {
        return "BattleInfoDto{" +
                "map='" + map + '\'' +
                ", battleKey='" + battleKey + '\'' +
                ", enemiesLocation=" + enemiesLocation +
                '}';
    }
}