package dasturlash.uz.repository;

import dasturlash.uz.entity.Company;
import dasturlash.uz.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {
    boolean existsByUsernameAndVisibleTrue(String username);

    Optional<Company> findByUsernameAndVisibleTrue(String username);

    boolean existsByCode(String code);

    Page<Company> findByVisibleTrue(Pageable pageable);
}
