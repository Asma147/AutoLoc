package tn.esprit.autoloc.Services;

import java.util.Set;
import tn.esprit.autoloc.Entities.Paiement;

public interface IPaiement {
    Set<Paiement> retrievePaiement();
    Paiement retrieveById(long id);
}