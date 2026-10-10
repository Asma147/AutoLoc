package tn.esprit.autoloc.Services;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Repository.IVehiculeRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehicule {

    private final IVehiculeRepository vehiculeRepo;

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("Un nouveau véhicule ne doit pas avoir d'identifiant");
        }
        verifier(vehicule);
        return vehiculeRepo.save(vehicule);
    }

    @Override
    public void deleteVehicule(long id) {
        if (!vehiculeRepo.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepo.deleteById(id);
    }

    @Override
    public Set<Vehicule> retrieveVehicule() {
        return new HashSet<>(vehiculeRepo.findAll());
    }

    @Override
    public Vehicule retrieveById(long id) {
        return vehiculeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        if (vehicule.getIdVehicule() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Vehicule existant = retrieveById(vehicule.getIdVehicule());
        verifier(vehicule);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setCategorie(vehicule.getCategorie());
        existant.setStatut(vehicule.getStatut());
        existant.setAgence(vehicule.getAgence());
        existant.setEquipements(vehicule.getEquipements());
        return vehiculeRepo.save(existant);
    }

    private void verifier(Vehicule vehicule) {
        if (vehicule.getImmatriculation() == null || vehicule.getImmatriculation().isBlank()) {
            throw new IllegalArgumentException("L'immatriculation est obligatoire");
        }
        if (vehicule.getTarifJournalier() == null
                || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
    }
}