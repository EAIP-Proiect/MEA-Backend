package com.mediaeventsagency.controller;

import com.mediaeventsagency.model.Location;
import com.mediaeventsagency.repository.LocationRepository;
import com.mediaeventsagency.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private LocationService locationService;

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
