package tda.darkarmy.housingsocietymanagement.model;

import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String eventDataTime;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;  // organiser of event

    @ManyToOne
    @JoinColumn(name="building_id", nullable=false)
    private Building building; // building in which event is organised

    public Event() {
    }

    public Event(Long id, String title, String description, String eventDataTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDataTime = eventDataTime;
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

    public String getEventDataTime() {
        return eventDataTime;
    }

    public void setEventDataTime(String eventDataTime) {
        this.eventDataTime = eventDataTime;
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
                ", eventDataTime='" + eventDataTime + '\'' +
                ", user=" + user +
                ", building=" + building +
                '}';
    }
}
