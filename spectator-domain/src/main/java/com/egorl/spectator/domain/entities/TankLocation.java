package com.egorl.spectator.domain.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tank_location")
public class TankLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_seq_gen")
    @SequenceGenerator(name = "location_id_seq_gen", sequenceName = "location_id_seq" , initialValue = 1, allocationSize =1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    @Column(name = "team")
    private Integer team;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "timing")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timing;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getTiming() {
        return timing;
    }

    public void setTiming(Date timing) {
        this.timing = timing;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(battle, that.battle) &&
                Objects.equals(team, that.team) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(timing, that.timing) &&
                Objects.equals(tankId, that.tankId) &&
                Objects.equals(hp, that.hp) &&
                Objects.equals(locationX, that.locationX) &&
                Objects.equals(locationY, that.locationY) &&
                Objects.equals(hullAngle, that.hullAngle) &&
                Objects.equals(turretAngle, that.turretAngle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, battle, team, nickname, timing, tankId, hp, locationX, locationY, hullAngle, turretAngle);
    }

    @Override
    public String toString() {
        return "TankLocation{" +
                "id=" + id +
                ", battle=" + battle +
                ", team=" + team +
                ", nickname='" + nickname + '\'' +
                ", timing=" + timing +
                ", tankId='" + tankId + '\'' +
                ", hp=" + hp +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", hullAngle=" + hullAngle +
                ", turretAngle=" + turretAngle +
                '}';
    }
}
