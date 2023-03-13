package es.wendyneo.backoffice.repository;

import es.wendyneo.backoffice.persistence.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {

    List<Client> findAll();

    Optional<Client> findById(Long id);

    Client save(Client client);

    void deleteById(Long id);
}
