package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Client;
import java.util.Set;

public interface IClient {
    Client addClient(Client client);
    void deleteClient(long id);
    Set<Client> retrieveClient();
    Client retrieveById(long id);
    Client updateClient(Client client);
}