package tn.esprit.autoloc.Services;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Contrat;
import tn.esprit.autoloc.Repository.IContratRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContrat {

    private final IContratRepository contratRepo;

    @Override
    public Contrat addContrat(Contrat contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException("Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        verifier(contrat);
        return contratRepo.save(contrat);
    }

    @Override
    public void deleteContrat(long id) {
        if (!contratRepo.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepo.deleteById(id);
    }

    @Override
    public Set<Contrat> retrieveContrat() {
        return new HashSet<>(contratRepo.findAll());
    }

    @Override
    public Contrat retrieveById(long id) {
        return contratRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public Contrat updateContrat(Contrat contrat) {
        if (contrat.getIdContrat() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Contrat existant = retrieveById(contrat.getIdContrat());
        verifier(contrat);
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        // on ne touche ni à l'id ni à la collection de paiements
        return contratRepo.save(existant);
    }

    private void verifier(Contrat contrat) {
        if (contrat.getMontantTotal() == null
                || contrat.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
    }
}