package tn.esprit.autoloc.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agence")
    private Agence agence;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vehicule_equipement", joinColumns = @JoinColumn(name = "id_vehicule"), inverseJoinColumns = @JoinColumn(name = "id_equipement")
    )
    private List<Equipement> equipements;


    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}