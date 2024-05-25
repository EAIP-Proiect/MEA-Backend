package com.mediaeventsagency.repository;

import com.mediaeventsagency.model.Role;
import com.mediaeventsagency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("select u.roles from User u where u.email=:email")
    List<Role> getRoleByEmail(@Param("email") String email);
}
