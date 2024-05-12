package com.mediaeventsagency.repository;

import com.mediaeventsagency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u join Profile p on u.id = p.user.id where p.email=:email")
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
