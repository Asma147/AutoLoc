package tn.esprit.autoloc.Services;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Repository.IMaintenanceRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenance {

    private final IMaintenanceRepository maintenanceRepo;

    @Override
    public Maintenance addMaintenance(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() != null) {
            throw new IllegalArgumentException("Une nouvelle maintenance ne doit pas avoir d'identifiant");
        }
        verifier(maintenance);
        return maintenanceRepo.save(maintenance);
    }

    @Override
    public void deleteMaintenance(long id) {
        if (!maintenanceRepo.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }
        maintenanceRepo.deleteById(id);
    }

    @Override
    public Set<Maintenance> retrieveMaintenance() {
        return new HashSet<>(maintenanceRepo.findAll());
    }

    @Override
    public Maintenance retrieveById(long id) {
        return maintenanceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public Maintenance updateMaintenance(Maintenance maintenance) {
        if (maintenance.getIdMaintenance() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Maintenance existant = retrieveById(maintenance.getIdMaintenance());
        verifier(maintenance);
        existant.setDateDebut(maintenance.getDateDebut());
        existant.setDateFin(maintenance.getDateFin());
        existant.setDescription(maintenance.getDescription());
        existant.setVehicule(maintenance.getVehicule());
        return maintenanceRepo.save(existant);
    }

    private void verifier(Maintenance maintenance) {
        if (maintenance.getDateDebut() == null || maintenance.getDateFin() == null) {
            throw new IllegalArgumentException("Les dates de début et de fin sont obligatoires");
        }
        if (maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin est antérieure à la date de début");
        }
    }
}