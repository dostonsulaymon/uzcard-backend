package dasturlash.uz.controller;

import dasturlash.uz.dto.client.ClientRequestDTO;
import dasturlash.uz.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping()
    public ResponseEntity<String > createClient(@RequestBody  @Valid ClientRequestDTO requestDTO){



        return ResponseEntity.status(HttpStatus.CREATED).body( clientService.createClient(requestDTO));
    }




}
