package tn.esprit.autoloc.Services;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Repository.IAgenceRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgence {

    private final IAgenceRepository agenceRepo;

    @Override
    public Agence addAgence(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("Une nouvelle agence ne doit pas avoir d'identifiant");
        }
        verifier(agence);
        return agenceRepo.save(agence);
    }

    @Override
    public void deleteAgence(long id) {
        if (!agenceRepo.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }
        agenceRepo.deleteById(id);
    }

    @Override
    public Set<Agence> retrieveAgence() {
        return new HashSet<>(agenceRepo.findAll());
    }

    @Override
    public Agence retrieveById(long id) {
        return agenceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public Agence updateAgence(Agence agence) {
        if (agence.getIdAgence() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Agence existant = retrieveById(agence.getIdAgence());
        verifier(agence);
        existant.setNom(agence.getNom());
        existant.setVille(agence.getVille());
        existant.setAdresse(agence.getAdresse());
        existant.setTelephone(agence.getTelephone());
        return agenceRepo.save(existant);
    }

    private void verifier(Agence agence) {
        if (agence.getNom() == null || agence.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
        }
        if (agence.getVille() == null || agence.getVille().isBlank()) {
            throw new IllegalArgumentException("La ville est obligatoire");
        }
        if (agence.getAdresse() == null || agence.getAdresse().isBlank()) {
            throw new IllegalArgumentException("L'adresse est obligatoire");
        }
        if (agence.getTelephone() == null || agence.getTelephone().isBlank()) {
            throw new IllegalArgumentException("Le téléphone est obligatoire");
        }
    }
}