package tn.esprit.autoloc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.Entities.Agence;

public interface IAgenceRepository extends JpaRepository<Agence,Long> {

}
