package tn.esprit.autoloc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Repositories.VehiculeRepository;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final VehiculeRepository vehiculeRepository;

    @Override
    public void run(String... args) {

        if (vehiculeRepository.count() == 0) {

            Vehicule v1 = new Vehicule();
            v1.setImmatriculation("123 TU 4567");
            v1.setMarque("Renault");
            v1.setModele("Clio");
            v1.setTarifJournalier(new BigDecimal("120.00"));
            v1.setCategorie(CategorieVehicule.CITADINE);
            v1.setStatut(StatutVehicule.DISPONIBLE);

            Vehicule v2 = new Vehicule();
            v2.setImmatriculation("234 TU 5678");
            v2.setMarque("Peugeot");
            v2.setModele("308");
            v2.setTarifJournalier(new BigDecimal("150.00"));
            v2.setCategorie(CategorieVehicule.BERLINE);
            v2.setStatut(StatutVehicule.DISPONIBLE);

            Vehicule v3 = new Vehicule();
            v3.setImmatriculation("345 TU 6789");
            v3.setMarque("Dacia");
            v3.setModele("Duster");
            v3.setTarifJournalier(new BigDecimal("180.00"));
            v3.setCategorie(CategorieVehicule.SUV);
            v3.setStatut(StatutVehicule.DISPONIBLE);

            vehiculeRepository.save(v1);
            vehiculeRepository.save(v2);
            vehiculeRepository.save(v3);

            System.out.println("=== 3 véhicules de démonstration insérés ===");
        }
    }
}