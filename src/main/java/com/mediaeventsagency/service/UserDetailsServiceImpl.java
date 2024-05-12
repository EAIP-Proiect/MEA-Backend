package com.mediaeventsagency.service;

import com.mediaeventsagency.model.User;
import com.mediaeventsagency.repository.ProfileRepository;
import com.mediaeventsagency.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public UserDetailsServiceImpl(UserRepository userRepository,
                                  ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email" + email));
        return UserDetailsImpl.build(user);
    }
}
