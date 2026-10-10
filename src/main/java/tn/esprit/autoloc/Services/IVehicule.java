package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Vehicule;
import java.util.Set;

public interface IVehicule {
    Vehicule addVehicule(Vehicule vehicule);
    void deleteVehicule(long id);
    Set<Vehicule> retrieveVehicule();
    Vehicule retrieveById(long id);
    Vehicule updateVehicule(Vehicule vehicule);
}