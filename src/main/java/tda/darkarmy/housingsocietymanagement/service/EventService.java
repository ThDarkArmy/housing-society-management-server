package tda.darkarmy.housingsocietymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.housingsocietymanagement.dto.EventDto;
import tda.darkarmy.housingsocietymanagement.exception.ResourceNotFoundException;
import tda.darkarmy.housingsocietymanagement.model.Event;
import tda.darkarmy.housingsocietymanagement.model.User;
import tda.darkarmy.housingsocietymanagement.repository.BuildingRepository;
import tda.darkarmy.housingsocietymanagement.repository.EventRepository;
import tda.darkarmy.housingsocietymanagement.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserService userService;

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

    private EventDto convertToDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setTitle(event.getTitle());
        eventDto.setDescription(event.getDescription());
        eventDto.setEventDateTime(event.getEventDateTime());
        //System.out.println("\n\n\nuser: "+ event.getUser().toString());
        //eventDto.setUser(userRepository.findById(event.getUser()));
        // Set other fields as needed
        return eventDto;
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    public Event createEvent(Event event) {
        User user = userService.getLoggedInUser();
        event.setUser(user);
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setEventDateTime(event.getEventDateTime());
        existingEvent.setBuilding(event.getBuilding());

        User user = userService.getLoggedInUser();
        existingEvent.setUser(user);

        return eventRepository.save(existingEvent);
    }

    public String deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        eventRepository.delete(event);
        return "Event deleted successfully";
    }
}

