package com.mediaeventsagency.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediaeventsagency.model.Location;
import com.mediaeventsagency.repository.LocationRepository;
import com.mediaeventsagency.service.LocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private LocationService locationService;
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // @GetMapping("/{id}")
    // public void getLocation(@PathVariable("id") UUID id) {
    //     locationRepository.findById(id);
    // }
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable("id") UUID id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return new ResponseEntity<>(location.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}

    @GetMapping("/all")
    List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("/addLocation")
    Location addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable("id") UUID id) {
        locationService.deleteLocation(id);
    }
}
