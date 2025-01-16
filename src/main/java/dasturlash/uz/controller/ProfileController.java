package dasturlash.uz.controller;

import dasturlash.uz.dto.profile.request.ProfileRequest;
import dasturlash.uz.dto.profile.request.ProfileUpdateRequest;
import dasturlash.uz.dto.profile.response.ProfileResponse;
import dasturlash.uz.dto.request.ChangeUsernameRequest;
import dasturlash.uz.dto.request.PasswordUpdateRequest;
import dasturlash.uz.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<String> createProfile(@RequestBody @Valid ProfileRequest request) {
        log.info("Creating new profile");
        return ResponseEntity.ok(profileService.createProfile(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileResponse> updateProfile(
            @PathVariable String id,
            @RequestBody @Valid ProfileUpdateRequest request) {
        log.info("Updating profile with id: {}", id);
        return ResponseEntity.ok(profileService.updateProfile(id, request));
    }

    @PutMapping("/update-password/{id}")
    public ResponseEntity<String> updatePassword(
            @PathVariable String id,
            @RequestBody @Valid PasswordUpdateRequest request) {
        log.info("Updating password for profile with id: {}", id);
        return ResponseEntity.ok(profileService.updatePassword(id, request));
    }

    @PutMapping("/change-username/{id}")
    public ResponseEntity<String> changeUsername(
            @PathVariable String id,
            @RequestBody @Valid ChangeUsernameRequest request) {
        log.info("Changing username for profile with id: {}", id);
        return ResponseEntity.ok(profileService.changeUsername(id, request));
    }



    @GetMapping("/list")
    public ResponseEntity<Page<ProfileResponse>> getProfiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Fetching profiles page: {}, size: {}", page, size);
        return ResponseEntity.ok(profileService.getProfiles(page, size));
    }

    @PutMapping("/change-status/{id}")
    public ResponseEntity<String> changeStatus(@PathVariable String id) {
        log.info("Changing status for profile with id: {}", id);
        return ResponseEntity.ok(profileService.changeStatus(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable String id) {
        log.info("Deleting profile with id: {}", id);
        return ResponseEntity.ok(profileService.deleteProfile(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String id) {
        log.info("Fetching profile with id: {}", id);
        return ResponseEntity.ok(profileService.getProfile(id));
    }
}