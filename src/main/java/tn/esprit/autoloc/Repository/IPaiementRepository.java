package tn.esprit.autoloc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Entities.Paiement;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}
