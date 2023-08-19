package com.cdac.org.housingsocietymanagement.dto;


import com.cdac.org.housingsocietymanagement.model.User;
import com.cdac.org.housingsocietymanagement.model.Building;

public class EventDto {
    private Long id;

    private String title;
    private String description;
    private String eventDateTime;

    private User user;  // organiser of event

    private Building building; // building in which event is organised

    public EventDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
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
}
