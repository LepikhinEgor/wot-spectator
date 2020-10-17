package com.egorl.spectator.domain.entities;

public enum Team {
    FIRST(1), SECOND(2);

    Integer number;

    Team(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public static Team from(Integer number) {
        if (number != null) {
            for (Team operation : Team.values()) {
                if (operation.number.equals(number))
                    return operation;
            }
        }
        return null;
    }
}
