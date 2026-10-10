package tn.esprit.autoloc.Services;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Repository.IEmployeRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmploye {

    private final IEmployeRepository employeRepo;

    @Override
    public Employe addEmploye(Employe employe) {
        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException("Un nouvel employé ne doit pas avoir d'identifiant");
        }
        verifier(employe);
        return employeRepo.save(employe);
    }

    @Override
    public void deleteEmploye(long id) {
        if (!employeRepo.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }
        employeRepo.deleteById(id);
    }

    @Override
    public Set<Employe> retrieveEmploye() {
        return new HashSet<>(employeRepo.findAll());
    }

    @Override
    public Employe retrieveById(long id) {
        return employeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        if (employe.getIdEmploye() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Employe existant = retrieveById(employe.getIdEmploye());
        verifier(employe);
        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());
        existant.setAgence(employe.getAgence());
        return employeRepo.save(existant);
    }

    private void verifier(Employe employe) {
        if (employe.getNom() == null || employe.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        if (employe.getPrenom() == null || employe.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom de l'employé est obligatoire");
        }
    }
}