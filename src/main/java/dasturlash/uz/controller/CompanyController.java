package dasturlash.uz.controller;

import dasturlash.uz.dto.request.CompanyRequest;
import dasturlash.uz.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody @Valid CompanyRequest request) {
        log.info("I am being called");

        return ResponseEntity.ok().body(companyService.createCompany(request));

    }


}
