package com.egorl.spectator.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class BattleDto {

    @JsonProperty("map")
    private String map;

    @JsonProperty("battle_id")
    private String battleId;

    @JsonProperty("team")
    private Integer team;

    @JsonProperty("enemies_location")
    private List<TankLocationDto> tanksLocation;

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getBattleId() {
        return battleId;
    }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    public List<TankLocationDto> getTanksLocation() {
        return tanksLocation;
    }

    public void setTanksLocation(List<TankLocationDto> tanksLocation) {
        this.tanksLocation = tanksLocation;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleDto battleDto = (BattleDto) o;
        return Objects.equals(map, battleDto.map) &&
                Objects.equals(battleId, battleDto.battleId) &&
                Objects.equals(team, battleDto.team) &&
                Objects.equals(tanksLocation, battleDto.tanksLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, battleId, team, tanksLocation);
    }

    @Override
    public String toString() {
        return "BattleDto{" +
                "map='" + map + '\'' +
                ", battleId='" + battleId + '\'' +
                ", team=" + team +
                ", tanksLocation=" + tanksLocation +
                '}';
    }
}
