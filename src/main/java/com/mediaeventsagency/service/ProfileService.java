package com.mediaeventsagency.service;

import com.mediaeventsagency.model.Profile;
import com.mediaeventsagency.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile saveProfileSignUp(String firstName, String lastName, String profilePicture) {
        Profile profile = new Profile();
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setProfilePicture(profilePicture);
        return profileRepository.save(profile);
    }

    public Optional<Profile> getProfileByEmail(String email) {
        return profileRepository.getByEmail(email);
    }
}
