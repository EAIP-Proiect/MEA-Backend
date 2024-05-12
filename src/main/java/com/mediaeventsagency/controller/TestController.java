package com.mediaeventsagency.controller;

import com.mediaeventsagency.model.User;
import com.mediaeventsagency.service.UserDetailsImpl;
import com.mediaeventsagency.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Log4j2
public class TestController {
    @Autowired
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ORGANIZER')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/org")
    @PreAuthorize("hasRole('ORGANIZER')")
    public String moderatorAccess() {
        return "Organizer Board.";
    }

    @GetMapping("/owner")
    @PreAuthorize("hasRole('OWNER')")
    public String adminAccess() {
        return "Owner Board.";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ORGANIZER')")
    public UserDetailsImpl profile() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}
