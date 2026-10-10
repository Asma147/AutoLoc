package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Employe;
import java.util.Set;

public interface IEmploye {
    Employe addEmploye(Employe employe);
    void deleteEmploye(long id);
    Set<Employe> retrieveEmploye();
    Employe retrieveById(long id);
    Employe updateEmploye(Employe employe);
}