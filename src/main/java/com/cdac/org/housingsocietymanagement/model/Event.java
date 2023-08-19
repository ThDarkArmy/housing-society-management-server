package com.cdac.org.housingsocietymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String eventDateTime;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;  // organiser of event

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="building_id", nullable=true)
    private Building building; // building in which event is organised

    public Event() {
    }

    public Event(Long id, String title, String description, String eventDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDateTime = eventDateTime;
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

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", eventDataTime='" + eventDateTime + '\'' +
                ", user=" + user +
                ", building=" + building +
                '}';
    }
}
