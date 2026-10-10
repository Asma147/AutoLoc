package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Contrat;
import java.util.Set;

public interface IContrat {
    Contrat addContrat(Contrat contrat);
    void deleteContrat(long id);
    Set<Contrat> retrieveContrat();
    Contrat retrieveById(long id);
    Contrat updateContrat(Contrat contrat);
}