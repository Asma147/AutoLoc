package tn.esprit.autoloc.Services;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Paiement;
import tn.esprit.autoloc.Repository.IPaiementRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiement {

    private final IPaiementRepository paiementRepo;

    @Override
    public Set<Paiement> retrievePaiement() {
        return new HashSet<>(paiementRepo.findAll());
    }

    @Override
    public Paiement retrieveById(long id) {
        return paiementRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
    }
}