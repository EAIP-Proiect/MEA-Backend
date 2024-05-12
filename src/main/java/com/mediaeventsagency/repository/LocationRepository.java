package com.mediaeventsagency.repository;

import com.mediaeventsagency.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    @Query("select l from Location l where l.user_id=:userId")
    List<Location> findByUserId(UUID userId);

    @Query("select l from Location l where l.user.email=:email")
    List<Location> findByEmail(String email);
}
