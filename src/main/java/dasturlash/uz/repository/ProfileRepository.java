package dasturlash.uz.repository;

import dasturlash.uz.entity.Company;
import dasturlash.uz.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {

    Optional<Profile> findByUsernameAndVisibleTrue(String username);

    boolean existsByUsername(String username);

    Page<Profile> findByVisibleTrue(Pageable pageable);

    Optional<Profile> findByIdAndVisibleTrue(String id);
}
