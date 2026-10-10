package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Maintenance;
import java.util.Set;

public interface IMaintenance {
    Maintenance addMaintenance(Maintenance maintenance);
    void deleteMaintenance(long id);
    Set<Maintenance> retrieveMaintenance();
    Maintenance retrieveById(long id);
    Maintenance updateMaintenance(Maintenance maintenance);
}