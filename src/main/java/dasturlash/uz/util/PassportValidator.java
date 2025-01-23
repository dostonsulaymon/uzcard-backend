package dasturlash.uz.util;

import dasturlash.uz.exceptions.client_related.DuplicatePassportException;
import dasturlash.uz.exceptions.client_related.InvalidPassportNumberFormatException;
import dasturlash.uz.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class PassportValidator {

    private final ClientRepository clientRepository;

    private static final Map<String, String> REGION_SERIAS = Map.ofEntries(
            Map.entry("AA", "Tashkent City"), Map.entry("AB", "Tashkent City"), Map.entry("AC", "Tashkent City"),
            Map.entry("AD", "Andijan"), Map.entry("AE", "Andijan"), Map.entry("AF", "Andijan"),
            Map.entry("BA", "Bukhara"), Map.entry("BB", "Bukhara"), Map.entry("BC", "Bukhara"),
            Map.entry("FA", "Fergana"), Map.entry("FB", "Fergana"), Map.entry("FC", "Fergana"),
            Map.entry("JA", "Jizzakh"), Map.entry("JB", "Jizzakh"), Map.entry("JC", "Jizzakh"),
            Map.entry("KA", "Kashkadarya"), Map.entry("KB", "Kashkadarya"), Map.entry("KC", "Kashkadarya"),
            Map.entry("KH", "Khorezm"), Map.entry("KI", "Khorezm"), Map.entry("KJ", "Khorezm"),
            Map.entry("NA", "Namangan"), Map.entry("NB", "Namangan"), Map.entry("NC", "Namangan"),
            Map.entry("NV", "Navoi"), Map.entry("NX", "Navoi"), Map.entry("NZ", "Navoi"),
            Map.entry("SA", "Samarkand"), Map.entry("SB", "Samarkand"), Map.entry("SC", "Samarkand"),
            Map.entry("SU", "Surkhandarya"), Map.entry("SV", "Surkhandarya"), Map.entry("SX", "Surkhandarya"),
            Map.entry("TA", "Tashkent Region"), Map.entry("TB", "Tashkent Region"), Map.entry("TC", "Tashkent Region"),
            Map.entry("KK", "Karakalpakstan"), Map.entry("KL", "Karakalpakstan"), Map.entry("KM", "Karakalpakstan")
    );

    public void isValidPassport(String seria, String number) {

        // Validate seria
        if (!REGION_SERIAS.containsKey(seria)) {
            throw new InvalidPassportNumberFormatException("Invalid seria");
        }

        System.out.println(REGION_SERIAS.get(seria));

        existsByPassSeriaAndNumber(seria, number);

        // If valid
        System.out.println("Passport belongs to region: " + REGION_SERIAS.get(seria));

    }

    private void existsByPassSeriaAndNumber(String seria, String number) {

        if (seria == null || number == null) {
            throw new IllegalArgumentException("Seria and number can't be null");
        }

        if (clientRepository.existsByPassportSeriaAndPassportNum(seria, number)) {
            throw new DuplicatePassportException("Duplicate passport found: Seria " + seria + ", Number " + number + " already exists in the system.");
        }
    }


}
