package tn.esprit.autoloc.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    private String libelle;


    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    private List<Vehicule> vehicules;
}