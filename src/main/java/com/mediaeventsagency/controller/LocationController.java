package com.mediaeventsagency.controller;

import com.mediaeventsagency.model.Location;
import com.mediaeventsagency.repository.LocationRepository;
import com.mediaeventsagency.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationRepository locationRepository;
    private final LocationService locationService;

    public LocationController(LocationRepository locationRepository, LocationService locationService) {
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable("id") UUID id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("/addLocation")
    Location addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    @PutMapping("/updateLocation")
    Location updateLocation(@RequestBody Location location){
        return locationService.updateLocation(location);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable("id") UUID id) {
        locationService.deleteLocation(id);
    }
}
