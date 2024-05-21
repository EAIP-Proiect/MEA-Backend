package com.mediaeventsagency.controller;

import com.mediaeventsagency.model.Event;
import com.mediaeventsagency.model.Location;
import com.mediaeventsagency.repository.EventRepository;
import com.mediaeventsagency.repository.LocationRepository;
import com.mediaeventsagency.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/all")
    List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/addEvent")
    Event addEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @GetMapping("/{id}")
    public void getEvent(@PathVariable("id") UUID id) {
        eventRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable("id") UUID id) {
        eventRepository.deleteById(id);
    }
}
