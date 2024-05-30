package com.mediaeventsagency.controller;

import com.mediaeventsagency.model.Event;
import com.mediaeventsagency.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ORGANIZER') or hasRole('USER')")
    List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/addEvent")
    @PreAuthorize("hasRole('ORGANIZER')")
    Event addEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ORGANIZER') or hasRole('USER')")
    public ResponseEntity<Event> getEvent(@PathVariable("id") UUID id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public void deleteEvent(@PathVariable("id") UUID id) {
        eventRepository.deleteById(id);
    }
}
