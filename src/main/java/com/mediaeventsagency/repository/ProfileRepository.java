package com.mediaeventsagency.repository;

import com.mediaeventsagency.model.Profile;
import com.mediaeventsagency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    @Query("select p from Profile p where p.user.id=:userId")
    Optional<Profile> findByUserId(UUID userId);

    @Query("select p from Profile p where p.user.email=:email")
    Optional<Profile> getByEmail(String email);

}
