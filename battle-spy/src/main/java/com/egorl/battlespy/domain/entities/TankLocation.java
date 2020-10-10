package com.egorl.battlespy.domain.entities;

import java.util.Objects;

public class TankLocation {

    private double locationX;

    private double locationY;

    private double hullAngle;

    private double turretAngle;

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public double getHullAngle() {
        return hullAngle;
    }

    public void setHullAngle(double hullAngle) {
        this.hullAngle = hullAngle;
    }

    public double getTurretAngle() {
        return turretAngle;
    }

    public void setTurretAngle(double turretAngle) {
        this.turretAngle = turretAngle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TankLocation that = (TankLocation) o;
        return Double.compare(that.locationX, locationX) == 0 &&
                Double.compare(that.locationY, locationY) == 0 &&
                Double.compare(that.hullAngle, hullAngle) == 0 &&
                Double.compare(that.turretAngle, turretAngle) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationX, locationY, hullAngle, turretAngle);
    }

    @Override
    public String toString() {
        return "TankLocation{" +
                "locationX=" + locationX +
                ", locationY=" + locationY +
                ", hullAngle=" + hullAngle +
                ", turretAngle=" + turretAngle +
                '}';
    }
}
