package com.test.bpdts.heruokuapp.models;

import java.util.Objects;

public class User {

    private Integer id;
    private Double latitude;
    private Double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
