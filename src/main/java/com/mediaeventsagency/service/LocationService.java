package com.mediaeventsagency.service;

import com.mediaeventsagency.model.Location;
import com.mediaeventsagency.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(UUID id) {
        locationRepository.deleteById(id);
    }

    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }
}
