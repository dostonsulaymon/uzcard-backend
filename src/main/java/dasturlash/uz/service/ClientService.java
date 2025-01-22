package dasturlash.uz.service;

import dasturlash.uz.dto.client.ClientRequestDTO;
import dasturlash.uz.repository.ClientRepository;
import dasturlash.uz.util.PassportValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PassportValidator passportValidator;


    public String createClient(ClientRequestDTO requestDTO) {

        passportValidator.isValidPassport(requestDTO.passportSeria(), requestDTO.passportNumber());



        return "hello";
    }

    private void validateClient(ClientRequestDTO requestDTO) {


    }

}
