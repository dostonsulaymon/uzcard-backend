package dasturlash.uz.controller;

import dasturlash.uz.dto.company.request.CompanyRequest;
import dasturlash.uz.dto.company.response.CompanyResponse;
import dasturlash.uz.dto.company.request.CompanyUpdateRequest;
import dasturlash.uz.dto.request.ChangeUsernameRequest;
import dasturlash.uz.dto.request.PasswordUpdateRequest;
import dasturlash.uz.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

     
    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody @Valid CompanyRequest request) {
        log.info("Creating new company");
        return ResponseEntity.ok(companyService.createCompany(request));
    }

     
    @PutMapping("/update/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable String id,
            @RequestBody @Valid CompanyUpdateRequest request) {
        log.info("Updating company with id: {}", id);
        return ResponseEntity.ok(companyService.updateCompany(id, request));
    }

     
    @PutMapping("/update-password/{id}")
    public ResponseEntity<String> updatePassword(
            @PathVariable String id,
            @RequestBody @Valid PasswordUpdateRequest request) {
        log.info("Updating password for company with id: {}", id);
        return ResponseEntity.ok(companyService.updatePassword(id, request));
    }

    @PutMapping("/change-username/{id}")
    public ResponseEntity<String> changeUsername(
            @PathVariable String id,
            @RequestBody @Valid ChangeUsernameRequest request) {
        log.info("Changing username for company with id: {}", id);
        return ResponseEntity.ok(companyService.changeUsername(id, request));
    }



    @GetMapping("/list")
    public ResponseEntity<Page<CompanyResponse>> getCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Fetching companies page: {}, size: {}", page, size);
        return ResponseEntity.ok(companyService.getCompanies(page, size));
    }

     
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable String id) {
        log.info("Deleting company with id: {}", id);
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
}