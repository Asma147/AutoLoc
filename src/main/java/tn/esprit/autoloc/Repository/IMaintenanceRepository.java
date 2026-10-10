package tn.esprit.autoloc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.Entities.Maintenance;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
