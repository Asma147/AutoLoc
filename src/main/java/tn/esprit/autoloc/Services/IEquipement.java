package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Equipement;
import java.util.Set;

public interface IEquipement {
    Equipement addEquipement(Equipement equipement);
    void deleteEquipement(long id);
    Set<Equipement> retrieveEquipement();
    Equipement retrieveById(long id);
    Equipement updateEquipement(Equipement equipement);
}