package dasturlash.uz.service;

import dasturlash.uz.dto.company.request.CompanyRequest;
import dasturlash.uz.dto.company.response.CompanyResponse;
import dasturlash.uz.dto.company.request.CompanyUpdateRequest;
import dasturlash.uz.dto.request.ChangeUsernameRequest;
import dasturlash.uz.dto.request.PasswordUpdateRequest;
import dasturlash.uz.entity.Company;
import dasturlash.uz.enums.Role;
import dasturlash.uz.exceptions.InvalidPasswordException;
import dasturlash.uz.exceptions.company_related.CompanyExistsException;
import dasturlash.uz.exceptions.company_related.CompanyNotFoundException;
import dasturlash.uz.exceptions.company_related.CompanyStatusException;
import dasturlash.uz.exceptions.company_related.InvalidBankCodeException;
import dasturlash.uz.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public CompanyResponse updateCompany(String id, CompanyUpdateRequest request) {
        Company company = getCompanyById(id);

        // Update basic fields
        company.setName(request.name());
        company.setAddress(request.address());
        company.setContact(request.contact());

        // Update code if provided and role is BANK
        if (request.code() != null && company.getRole().equals(Role.ROLE_BANK)) {
            // Only check for code existence if the new code is different from the company's current code
            if (!request.code().equals(company.getCode()) && companyRepository.existsByCode(request.code())) {
                throw new CompanyExistsException("Bank with code " + request.code() + " already exists");
            }
            company.setCode(request.code());
        }

        company.setUpdatedDate(LocalDateTime.now());

        Company updatedCompany = companyRepository.save(company);
        return mapToCompanyResponse(updatedCompany);
    }

    @Transactional
    public String updatePassword(String id, PasswordUpdateRequest request) {
        Company company = getCompanyById(id);


        // Verify old password
        if (!passwordEncoder.matches(request.oldPassword(), company.getPassword())) {
            throw new InvalidPasswordException("Invalid old password");
        }

        // Check if the new password is the same as the old password
        if (request.oldPassword().equals(request.newPassword())) {
            throw new InvalidPasswordException("New password cannot be the same as the old password");
        }

        company.setPassword(passwordEncoder.encode(request.newPassword()));
        company.setUpdatedDate(LocalDateTime.now());

        companyRepository.save(company);

        return "Password updated successfully";
    }

    @Transactional
    public String changeUsername(String id, ChangeUsernameRequest request) {
        Company company = getCompanyById(id);

        // Check if the new username is already taken
        if (!request.newUsername().equals(company.getUsername()) && existByUsername(request.newUsername())) {
            throw new CompanyExistsException("Username already exists: " + request.newUsername());
        }

        // Update the username
        company.setUsername(request.newUsername());
        company.setUpdatedDate(LocalDateTime.now());
        companyRepository.save(company);

        return "Username changed successfully";
    }



    public Page<CompanyResponse> getCompanies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Company> companies = companyRepository.findByVisibleTrue(pageable);

        List<Integer> list = new ArrayList<>();
        return companies.map(this::mapToCompanyResponse);
    }

    @Transactional
    public String deleteCompany(String id) {
        Company company = getCompanyById(id);

        company.setVisible(false);
        company.setUpdatedDate(LocalDateTime.now());
        companyRepository.save(company);

        return "Company deleted successfully";
    }

    private Company getCompanyById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
    }

    private CompanyResponse mapToCompanyResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getContact(),
                company.getRole(),
                company.getCode(),
                company.getUsername(),
                company.getCreatedDate(),
                company.getUpdatedDate()
        );
    }    private Boolean existByUsername(String username) {

        if (username == null) {
            return false;
        }

        return companyRepository.existsByUsernameAndVisibleTrue(username);

    }

    private boolean existsByCode(String code) {
        if (code == null) {
            return false;
        }
        return companyRepository.existsByCode(code);
    }

    private void validateCompany(CompanyRequest request){
        if (request.role() == null) {
            throw new CompanyStatusException("Role is required to create Company");
        }

        if (existByUsername(request.username())) {
            throw new CompanyExistsException("Username must be unique. Company with username " + request.username() + " exists");
        }

        if (request.role().equals(Role.ROLE_BANK)) {
            if (request.code() == null || request.code().isEmpty()) {
                throw new InvalidBankCodeException("Code is required to create Company with role of Bank");
            }
            if (existsByCode(request.code())) {
                throw new CompanyExistsException("Code must be unique. Bank with code " + request.code() + " already exists");
            }
        }

    }
}
