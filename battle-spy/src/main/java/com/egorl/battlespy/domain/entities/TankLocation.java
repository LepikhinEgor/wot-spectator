package com.egorl.battlespy.domain.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tank_location")
public class TankLocation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    @Column(name = "team")
    private Integer team;

    @Column(name = "timing")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "tank_id")
    private String tankId;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "location_x")
    private Double locationX;

    @Column(name = "location_y")
    private Double locationY;

    @Column(name = "hull_angle")
    private Double hullAngle;

    @Column(name = "turret_angle")
    private Double turretAngle;

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TankLocation that = (TankLocation) o;
        return Objects.equals(battle, that.battle) &&
                Objects.equals(team, that.team) &&
                Objects.equals(date, that.date) &&
                Objects.equals(tankId, that.tankId) &&
                Objects.equals(hp, that.hp) &&
                Objects.equals(locationX, that.locationX) &&
                Objects.equals(locationY, that.locationY) &&
                Objects.equals(hullAngle, that.hullAngle) &&
                Objects.equals(turretAngle, that.turretAngle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(battle, team, date, tankId, hp, locationX, locationY, hullAngle, turretAngle);
    }

    @Override
    public String toString() {
        return "TankLocation{" +
                "battle=" + battle +
                ", team=" + team +
                ", date=" + date +
                ", tankId='" + tankId + '\'' +
                ", hp=" + hp +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", hullAngle=" + hullAngle +
                ", turretAngle=" + turretAngle +
                '}';
    }
}
