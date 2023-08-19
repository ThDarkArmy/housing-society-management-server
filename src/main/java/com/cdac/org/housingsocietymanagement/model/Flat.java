package com.cdac.org.housingsocietymanagement.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flatNumber;
    private Long floorNumber;
    private String flatType;
    private Boolean status = false;
    private Long rent;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=true)
    private User user; // flat owner

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="building_id", nullable=false)
    private Building building;

    public Flat() {
    }

    public Flat(Long id, Long flatNumber, Long floorNumber, String flatType, Boolean status, Long rent) {
        this.id = id;
        this.flatNumber = flatNumber;
        this.floorNumber = floorNumber;
        this.flatType = flatType;
        this.status = status;
        this.rent = rent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Long flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Long getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Long floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Long getRent() {
        return rent;
    }

    public void setRent(Long rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", flatNumber=" + flatNumber +
                ", floorNumber=" + floorNumber +
                ", flatType='" + flatType + '\'' +
                ", status=" + status +
                ", rent=" + rent +
                '}';
    }
}
