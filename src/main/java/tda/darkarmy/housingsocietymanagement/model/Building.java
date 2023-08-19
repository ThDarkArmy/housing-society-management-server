package tda.darkarmy.housingsocietymanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long numberOfFlats;
    private String address;

    private String imageUrl;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user; // secretary of the building

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private Set<Flat> flats = new HashSet<>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private Set<Announcement> announcements = new HashSet<>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    public Building() {
    }

    public Building(Long id, String name, Long numberOfFlats, String address, String imageUrl) {
        this.id = id;
        this.name = name;
        this.numberOfFlats = numberOfFlats;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfFlats() {
        return numberOfFlats;
    }

    public void setNumberOfFlats(Long numberOfFlats) {
        this.numberOfFlats = numberOfFlats;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Flat> getFlats() {
        return flats;
    }

    public void setFlats(Set<Flat> flats) {
        this.flats = flats;
    }

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }


    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfFlats=" + numberOfFlats +
                ", address='" + address + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", user=" + user +
                ", flats=" + flats +
                ", announcements=" + announcements +
                ", events=" + events +
                '}';
    }
}
