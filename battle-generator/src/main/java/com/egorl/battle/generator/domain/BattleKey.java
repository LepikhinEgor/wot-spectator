package com.egorl.battle.generator.domain;

import java.util.Objects;
import java.util.UUID;

public class BattleKey {

    private UUID id;

    private Integer team;

    public BattleKey(UUID id, Integer team) {
        this.id = id;
        this.team = team;
    }

    public UUID getId() {
        return id;
    }

    public Integer getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleKey battleKey = (BattleKey) o;
        return Objects.equals(id, battleKey.id) &&
                Objects.equals(team, battleKey.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team);
    }

    @Override
    public String toString() {
        return "BattleKey{" +
                "id=" + id +
                ", team=" + team +
                '}';
    }
}
