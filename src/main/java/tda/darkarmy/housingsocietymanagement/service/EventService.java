package tda.darkarmy.housingsocietymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.housingsocietymanagement.exception.ResourceNotFoundException;
import tda.darkarmy.housingsocietymanagement.model.Event;
import tda.darkarmy.housingsocietymanagement.repository.BuildingRepository;
import tda.darkarmy.housingsocietymanagement.repository.EventRepository;
import tda.darkarmy.housingsocietymanagement.repository.UserRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setEventDataTime(event.getEventDataTime());
        existingEvent.setUser(event.getUser());
        existingEvent.setBuilding(event.getBuilding());

        return eventRepository.save(existingEvent);
    }

    public String deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        eventRepository.delete(event);
        return "Event deleted successfully";
    }
}

