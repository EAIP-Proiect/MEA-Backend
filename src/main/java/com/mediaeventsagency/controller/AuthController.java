package com.mediaeventsagency.controller;

import com.mediaeventsagency.dto.JwtResponse;
import com.mediaeventsagency.dto.SignInRequest;
import com.mediaeventsagency.dto.SignUpRequest;
import com.mediaeventsagency.model.ERole;
import com.mediaeventsagency.model.Profile;
import com.mediaeventsagency.model.Role;
import com.mediaeventsagency.repository.ProfileRepository;
import com.mediaeventsagency.repository.RoleRepository;
import com.mediaeventsagency.repository.UserRepository;
import com.mediaeventsagency.service.ProfileService;
import com.mediaeventsagency.service.UserDetailsImpl;
import com.mediaeventsagency.service.UserService;
import com.mediaeventsagency.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final ProfileService profileService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          ProfileService profileService, PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.profileService = profileService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtResponse res = new JwtResponse();
        res.setToken(jwt);
        res.setId(userDetails.getId());
        res.setUsername(userDetails.getUsername());
        res.setRoles(roles);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        if (profileService.getProfileByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"{\\\"error\\\":\\\"email is already taken\\\"}\"");
        }

        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Set<Role> roles = new HashSet<>();
        ERole role = signUpRequest.getRole().equals("user") ? ERole.ROLE_USER : signUpRequest.getRole().equals("owner") ? ERole.ROLE_OWNER : ERole.ROLE_ORGANIZER;
        System.out.println(role);
        System.out.println(signUpRequest.getRole());
        Optional<Role> userRole = roleRepository.findByName(role);
        if (userRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("role not found");
        }
        roles.add(userRole.get());
        userService.saveUserSignUp(signUpRequest.getEmail(), roles, hashedPassword);
        profileService.saveProfileSignUp(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getProfilePicture());
        return ResponseEntity.ok("User registered success");
    }

    @GetMapping("/profile/{email}")
    public Profile getProfile(@PathVariable("email") String email) {
        return profileService.getProfileByEmail(email).get();
    }

    @GetMapping("/profile/{id}")
    public Profile getProfile(@PathVariable("id") UUID id) {
        return profileService.getProfileByUserId(id).get();
    }
}
