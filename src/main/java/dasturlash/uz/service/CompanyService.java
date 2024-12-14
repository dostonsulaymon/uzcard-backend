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

        if (request.getRole() == null) {
            throw new AppBadRequestException("Role is required to create Company");
        }

        if (existByUsername(request.getUsername())) {
            throw new CompanyExistsException("Username must be unique. Company with username " + request.getUsername() + " exists");
        }

        if (request.getRole().equals(CompanyRole.BANK)) {
            if (request.getCode() == null || request.getCode().isEmpty()) {
                throw new AppBadRequestException("Code is required to create Company with role of Bank");
            }
        }

        Company newCompany = new Company();
        newCompany.setName(request.getName());
        newCompany.setAddress(request.getAddress());
        newCompany.setContact(request.getContact());
        newCompany.setRole(request.getRole());
        newCompany.setUsername(request.getUsername());
        newCompany.setPassword(passwordEncoder.encode(request.getPassword()));
        newCompany.setCreatedDate(LocalDateTime.now());
        newCompany.setVisible(Boolean.TRUE);

        // Set code only if it is provided (typically when role is BANK)
        if (request.getCode() != null) {
            newCompany.setCode(request.getCode());
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
}
