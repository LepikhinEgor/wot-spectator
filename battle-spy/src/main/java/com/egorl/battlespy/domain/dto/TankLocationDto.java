package com.egorl.battlespy.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TankLocationDto {

    @JsonProperty("tank_id")
    private String tankId;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("hp")
    private Integer hp;

    @JsonProperty("location_x")
    private Double locationX;

    @JsonProperty("location_y")
    private Double locationY;

    @JsonProperty("hull_angle")
    private Double hullAngle;

    @JsonProperty("turret_angle")
    private Double turretAngle;

    @JsonProperty("team")
    private Integer team;

    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Double getLocationX() {
        return locationX;
    }

    public void setLocationX(Double locationX) {
        this.locationX = locationX;
    }

    public Double getLocationY() {
        return locationY;
    }

    public void setLocationY(Double locationY) {
        this.locationY = locationY;
    }

    public Double getHullAngle() {
        return hullAngle;
    }

    public void setHullAngle(Double hullAngle) {
        this.hullAngle = hullAngle;
    }

    public Double getTurretAngle() {
        return turretAngle;
    }

    public void setTurretAngle(Double turretAngle) {
        this.turretAngle = turretAngle;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TankLocationDto dto = (TankLocationDto) o;
        return Objects.equals(tankId, dto.tankId) &&
                Objects.equals(nickname, dto.nickname) &&
                Objects.equals(hp, dto.hp) &&
                Objects.equals(locationX, dto.locationX) &&
                Objects.equals(locationY, dto.locationY) &&
                Objects.equals(hullAngle, dto.hullAngle) &&
                Objects.equals(turretAngle, dto.turretAngle) &&
                Objects.equals(team, dto.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tankId, nickname, hp, locationX, locationY, hullAngle, turretAngle, team);
    }

    @Override
    public String toString() {
        return "TankLocationDto{" +
                "tankId='" + tankId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", hp=" + hp +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", hullAngle=" + hullAngle +
                ", turretAngle=" + turretAngle +
                ", team=" + team +
                '}';
    }
}
