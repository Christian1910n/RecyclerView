package com.example.app5eartquake;

import java.util.Objects;

public class Earthquake {
    private String id;
    private String place;
    private double magnitude;
    private long time;
    private double latidude;
    private double longitude;

    public Earthquake(String id, String place, double magnitude, long time, double latidude, double longitude) {
        this.id = id;
        this.place = place;
        this.magnitude = magnitude;
        this.time = time;
        this.latidude = latidude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLatidude() {
        return latidude;
    }

    public void setLatidude(double latidude) {
        this.latidude = latidude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earthquake that = (Earthquake) o;
        return Double.compare(that.magnitude, magnitude) == 0 && time == that.time && Double.compare(that.latidude, latidude) == 0 && Double.compare(that.longitude, longitude) == 0 && id.equals(that.id) && place.equals(that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, magnitude, time, latidude, longitude);
    }
}
