package com.egorl.battle.generator.services;

import com.egorl.battle.generator.domain.BattleCreationRequest;
import com.egorl.battle.generator.domain.Point;
import com.egorl.spectator.domain.dto.BattleDto;
import com.egorl.spectator.domain.dto.TankLocationDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BattleGenerator {

    private static final int TEAM_SIZE = 15;

    private static final double FIRST_TEAM_RESPAWN_X = 20;
    private static final double FIRST_TEAM_RESPAWN_Y = 20;

    private static final double SECOND_TEAM_RESPAWN_X = 20;
    private static final double SECOND_TEAM_RESPAWN_Y = 20;

    private static final double RESPAWN_RADIUS = 10;

    public BattleDto generateBattle(BattleCreationRequest battleCreation) {
        BattleDto battle = new BattleDto();
        battle.setMap(battleCreation.getMap());
        battle.setBattleId(battleCreation.getId());
        battle.setTanksLocation(generateNewLocations(battleCreation.getTeam()));

        return battle;
    }

    public BattleDto updateBattle(BattleDto oldBattleDto) {
        List<TankLocationDto> newLocations = oldBattleDto.getTanksLocation().stream()
                .peek(dto -> {
                    Point newPosition = updatePosition(new Point(dto.getLocationX(), dto.getLocationY()));
                    dto.setLocationX(newPosition.getX());
                    dto.setLocationY(newPosition.getY());
                })
                .collect(Collectors.toList());
        oldBattleDto.setTanksLocation(newLocations);

        return oldBattleDto;
    }

    private List<TankLocationDto> generateNewLocations(Integer team) {
        List<TankLocationDto> locations = new ArrayList<>();
        for (int i = 0; i < TEAM_SIZE; i++) {
            TankLocationDto tankLocation = new TankLocationDto();
            tankLocation.setHp(1000);
            tankLocation.setHullAngle(0D);
            tankLocation.setTurretAngle(0D);
            tankLocation.setNickname(generateNickname(team, i));

            Point startPosition = generateStartPosition(team);
            tankLocation.setLocationX(startPosition.getX());
            tankLocation.setLocationY(startPosition.getY());

            locations.add(tankLocation);
        }

        return locations;
    }

    private String generateNickname(Integer team, Integer number) {
        return "player_" + number * team;
    }

    private Point generateStartPosition(Integer team) {
        Point point = getRespawnPoint(team);

        return updatePosition(point);
    }

    private Point updatePosition(Point lastPosition) {
        Point newPosition = new Point();
        newPosition.setX(lastPosition.getX() + Math.random()*RESPAWN_RADIUS);
        newPosition.setY(lastPosition.getY() + Math.random()*RESPAWN_RADIUS);

        return newPosition;
    }

    private Point getRespawnPoint(Integer team) {
        switch (team) {
            case 1:
                return getFirstTeamRespawn();
            case 2:
                return getSecondTeamRespawn();
            default: throw new IllegalArgumentException("Incorrect team number");
        }
    }

    private Point getFirstTeamRespawn() {
        return new Point(FIRST_TEAM_RESPAWN_X, FIRST_TEAM_RESPAWN_Y);
    }

    private Point getSecondTeamRespawn() {
        return new Point(SECOND_TEAM_RESPAWN_X, SECOND_TEAM_RESPAWN_Y);
    }
}
