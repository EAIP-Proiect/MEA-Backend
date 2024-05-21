package com.mediaeventsagency.repository;

import com.mediaeventsagency.model.Event;
import com.mediaeventsagency.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query("select e from Event e where e.location.id=:locationId")
    List<Event> findByLocationId(UUID locationId);
}
