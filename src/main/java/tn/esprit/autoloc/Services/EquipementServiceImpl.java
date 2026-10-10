package tn.esprit.autoloc.Services;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Repository.IEquipementRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipement {

    private final IEquipementRepository equipementRepo;

    @Override
    public Equipement addEquipement(Equipement equipement) {
        if (equipement.getIdEquipement() != null) {
            throw new IllegalArgumentException("Un nouvel équipement ne doit pas avoir d'identifiant");
        }
        verifier(equipement);
        return equipementRepo.save(equipement);
    }

    @Override
    public void deleteEquipement(long id) {
        if (!equipementRepo.existsById(id)) {
            throw new ResourceNotFoundException("Equipement", id);
        }
        equipementRepo.deleteById(id);
    }

    @Override
    public Set<Equipement> retrieveEquipement() {
        return new HashSet<>(equipementRepo.findAll());
    }

    @Override
    public Equipement retrieveById(long id) {
        return equipementRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public Equipement updateEquipement(Equipement equipement) {
        if (equipement.getIdEquipement() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Equipement existant = retrieveById(equipement.getIdEquipement());
        verifier(equipement);
        existant.setLibelle(equipement.getLibelle());
        return equipementRepo.save(existant);
    }

    private void verifier(Equipement equipement) {
        if (equipement.getLibelle() == null || equipement.getLibelle().isBlank()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }
    }
}