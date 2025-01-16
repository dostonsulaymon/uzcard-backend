package dasturlash.uz.service;

import dasturlash.uz.dto.profile.request.ProfileRequest;
import dasturlash.uz.dto.profile.request.ProfileUpdateRequest;
import dasturlash.uz.dto.profile.response.ProfileResponse;
import dasturlash.uz.dto.request.ChangeUsernameRequest;
import dasturlash.uz.dto.request.PasswordUpdateRequest;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.enums.Role;
import dasturlash.uz.exceptions.InvalidPasswordException;
import dasturlash.uz.exceptions.profile_related.ProfileExistsException;
import dasturlash.uz.exceptions.profile_related.ProfileNotFoundException;
import dasturlash.uz.exceptions.profile_related.ProfileStatusException;
import dasturlash.uz.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public String createProfile(ProfileRequest request) {


        validateProfile(request);

        Profile newProfile = new Profile();
        newProfile.setName(request.name());
        newProfile.setSurname(request.surname());
        newProfile.setRole(request.role());
        newProfile.setUsername(request.username());
        newProfile.setPassword(request.password()); // Add encoding if needed
        newProfile.setCreatedDate(LocalDateTime.now());
        newProfile.setVisible(Boolean.TRUE);

        profileRepository.save(newProfile);
        return "Profile created with id: " + newProfile.getId();
    }

    @Transactional
    public ProfileResponse updateProfile(String id, ProfileUpdateRequest request) {
        Profile profile = getProfileById(id);

        profile.setName(request.name());
        profile.setSurname(request.surname());
        profile.setRole(request.role());
        profile.setUpdatedDate(LocalDateTime.now());

        Profile updatedProfile = profileRepository.save(profile);
        return mapToProfileResponse(updatedProfile);
    }

    public Page<ProfileResponse> getProfiles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Profile> profiles = profileRepository.findByVisibleTrue(pageable);
        return profiles.map(this::mapToProfileResponse);
    }

    @Transactional
    public String changeStatus(String id) {
        Profile profile = getProfileById(id);
        profile.setVisible(!profile.getVisible());
        profile.setUpdatedDate(LocalDateTime.now());
        profileRepository.save(profile);
        return "Profile status changed successfully";
    }

    @Transactional
    public String updatePassword(String id, PasswordUpdateRequest request) {
        Profile profile = getProfileById(id);

        // Verify old password (if applicable)
        if (!passwordEncoder.matches(request.oldPassword(), profile.getPassword())) {
            throw new InvalidPasswordException("Invalid old password");
        }

        // Check if the new password is the same as the old password
        if (request.oldPassword().equals(request.newPassword())) {
            throw new InvalidPasswordException("New password cannot be the same as the old password");
        }

        // Update the password
        profile.setPassword(passwordEncoder.encode(request.newPassword()));
        profile.setUpdatedDate(LocalDateTime.now());
        profileRepository.save(profile);

        return "Password updated successfully";
    }


    @Transactional
    public String changeUsername(String id, ChangeUsernameRequest request) {
        Profile profile = getProfileById(id);

        // Check if the new username is already taken
        if (profileRepository.existsByUsername(request.newUsername())) {
            throw new ProfileExistsException("Username already exists: " + request.newUsername());
        }

        // Update the username
        profile.setUsername(request.newUsername());
        profile.setUpdatedDate(LocalDateTime.now());
        profileRepository.save(profile);

        return "Username changed successfully";
    }

    public String deleteProfile(String id) {
        Profile profile = getProfileById(id);
        profile.setVisible(false);
        profile.setUpdatedDate(LocalDateTime.now());
        profileRepository.save(profile);
        return "Profile deleted successfully";
    }

    public ProfileResponse getProfile(String id) {
        Profile profile = getProfileById(id);
        return mapToProfileResponse(profile);
    }


    private Profile getProfileById(String id) {
        return profileRepository.findByIdAndVisibleTrue(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with id: " + id));
    }

    private void validateProfile(ProfileRequest request) {
        // Check if username already exists
        if (profileRepository.existsByUsername(request.username())) {
            throw new ProfileExistsException("Username must be unique. Profile with username " + request.username() + " exists");
        }

        // Validate that the role is either ROLE_ADMIN or ROLE_MODERATOR
        if (request.role() != Role.ROLE_ADMIN && request.role() != Role.ROLE_MODERATOR) {
            throw new ProfileStatusException("Profile can only be created with ROLE_ADMIN or ROLE_MODERATOR");
        }
    }

    private ProfileResponse mapToProfileResponse(Profile profile) {
        return new ProfileResponse(
                profile.getId(),
                profile.getName(),
                profile.getSurname(),
                profile.getRole(),
                profile.getUsername(),
                profile.getCreatedDate(),
                profile.getUpdatedDate(),
                profile.getVisible()
        );
    }
}