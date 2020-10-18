package com.egorl.battle.generator.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BattleCreationRequest {

    @JsonProperty
    private String id;

    @JsonProperty
    private String map;

    @JsonProperty
    private Integer team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
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
        BattleCreationRequest that = (BattleCreationRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(map, that.map) &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, map, team);
    }

    @Override
    public String toString() {
        return "BattleCreationRequest{" +
                "id='" + id + '\'' +
                ", map='" + map + '\'' +
                ", team=" + team +
                '}';
    }
}
