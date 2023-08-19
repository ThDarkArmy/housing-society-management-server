package com.cdac.org.housingsocietymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cdac.org.housingsocietymanagement.model.Event;
import com.cdac.org.housingsocietymanagement.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<?>> getAllEvents() {
        List<?> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event newEvent = eventService.createEvent(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        String message = eventService.deleteEvent(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

