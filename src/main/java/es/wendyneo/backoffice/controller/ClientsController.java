package es.wendyneo.backoffice.controller;

import es.wendyneo.backoffice.domain.ClientRequest;
import es.wendyneo.backoffice.persistence.Client;
import es.wendyneo.backoffice.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientRepository clientRepository;

    public ClientsController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Client getClient(@PathVariable String id) {
        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping(path = "/", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody ClientRequest clientRequest) throws URISyntaxException {
        Client savedClient = clientRepository.save(getNewClient(clientRequest));
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
    }

    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody ClientRequest clientRequest) {
        Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        currentClient.setName(clientRequest.name());
        currentClient.setPhone(clientRequest.phone());
        currentClient.setPetName(clientRequest.petName());
        currentClient.setPetType(clientRequest.petType());
        currentClient.setAllergy(clientRequest.allergy());
        currentClient.setNotes(clientRequest.notes());
        currentClient = clientRepository.save(currentClient);

        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private static Client getNewClient(ClientRequest clientRequest) {
        return new Client(null, clientRequest.name(), clientRequest.phone(), clientRequest.petName(), clientRequest.petType(), clientRequest.allergy(), clientRequest.notes());
    }
}