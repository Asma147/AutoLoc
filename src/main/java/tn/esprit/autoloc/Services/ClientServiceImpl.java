package tn.esprit.autoloc.Services;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Repository.IClientRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClient {

    private final IClientRepository clientRepo;

    @Override
    public Client addClient(Client client) {
        if (client.getIdClient() != null) {
            throw new IllegalArgumentException("Un nouveau client ne doit pas avoir d'identifiant");
        }
        verifier(client);
        return clientRepo.save(client);
    }

    @Override
    public void deleteClient(long id) {
        if (!clientRepo.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        clientRepo.deleteById(id);
    }

    @Override
    public Set<Client> retrieveClient() {
        return new HashSet<>(clientRepo.findAll());
    }

    @Override
    public Client retrieveById(long id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }

    @Override
    public Client updateClient(Client client) {
        if (client.getIdClient() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Client existant = retrieveById(client.getIdClient());
        verifier(client);
        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());
        return clientRepo.save(existant);
    }

    private void verifier(Client client) {
        if (client.getNom() == null || client.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom du client est obligatoire");
        }
        if (client.getPrenom() == null || client.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom du client est obligatoire");
        }
        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'e-mail du client est obligatoire");
        }
    }
}