package dasturlash.uz.service;

import dasturlash.uz.dto.request.CompanyRequest;
import dasturlash.uz.entity.Company;
import dasturlash.uz.enums.CompanyRole;
import dasturlash.uz.exceptions.AppBadRequestException;
import dasturlash.uz.exceptions.CompanyExistsException;
import dasturlash.uz.repository.CompanyRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String createCompany(CompanyRequest request) {


        validateCompany(request);


        Company newCompany = new Company();
        newCompany.setName(request.name());
        newCompany.setAddress(request.address());
        newCompany.setContact(request.contact());
        newCompany.setRole(request.role());
        newCompany.setUsername(request.username());
        newCompany.setPassword(passwordEncoder.encode(request.password()));
        newCompany.setCreatedDate(LocalDateTime.now());
        newCompany.setVisible(Boolean.TRUE);

        // Set code only if it is provided (typically when role is BANK)
        if (request.code() != null) {
            newCompany.setCode(request.code());
        }

        companyRepository.save(newCompany);


        return "Company create with id: " + newCompany.getId();
    }

    private Boolean existByUsername(String username) {

        if (username == null) {
            return false;
        }

        return companyRepository.existsByUsername(username);

    }

    private void validateCompany(CompanyRequest request){
        if (request.role() == null) {
            throw new AppBadRequestException("Role is required to create Company");
        }

        if (existByUsername(request.username())) {
            throw new CompanyExistsException("Username must be unique. Company with username " + request.username() + " exists");
        }

        if (request.role().equals(CompanyRole.BANK)) {
            if (request.code() == null || request.code().isEmpty()) {
                throw new AppBadRequestException("Code is required to create Company with role of Bank");
            }
        }

    }
}
