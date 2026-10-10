package tn.esprit.autoloc.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;

    private BigDecimal montantTotal;

    private boolean valide;


    @OneToOne(mappedBy = "contrat", fetch = FetchType.LAZY)
    private Reservation reservation;


    @OneToMany(mappedBy = "contrat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Paiement> paiements;
}