package dasturlash.uz.repository;

import dasturlash.uz.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
