package dasturlash.uz.security;

import dasturlash.uz.entity.Company;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.enums.Role;
import dasturlash.uz.enums.UserType;
import dasturlash.uz.repository.CompanyRepository;
import dasturlash.uz.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CompanyRepository companyRepository;
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find company user
        Optional<Company> companyOpt = companyRepository.findByUsernameAndVisibleTrue(username);
        if (companyOpt.isPresent()) {
            Company company = companyOpt.get();
            return CustomUserDetails.builder()
                    .id(company.getId().toString())
                    .username(company.getUsername())
                    .password(company.getPassword())
                    .userType(UserType.COMPANY)
                    .role(Role.valueOf(company.getRole().name()))
                    .build();
        }

        // Try to find profile user
        Optional<Profile> profileOpt = profileRepository.findByUsername(username);
        if (profileOpt.isPresent()) {
            Profile profile = profileOpt.get();
            return CustomUserDetails.builder()
                    .id(profile.getId().toString())
                    .username(profile.getUsername())
                    .password(profile.getPassword())
                    .userType(UserType.PROFILE)
                    .role(Role.valueOf(profile.getRole().name()))
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}