package tn.esprit.autoloc.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    private String immatriculation;

    private String marque;

    private String modele;
    private BigDecimal tarifJournalier;


    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;


    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;
}

