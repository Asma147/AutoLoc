package tn.esprit.autoloc.Services;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Repository.IReservationRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservation {

    private final IReservationRepository reservationRepo;

    @Override
    public Reservation addReservation(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            throw new IllegalArgumentException("Une nouvelle réservation ne doit pas avoir d'identifiant");
        }
        verifier(reservation);
        return reservationRepo.save(reservation);
    }

    @Override
    public void deleteReservation(long id) {
        if (!reservationRepo.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", id);
        }
        reservationRepo.deleteById(id);
    }

    @Override
    public Set<Reservation> retrieveReservation() {
        return new HashSet<>(reservationRepo.findAll());
    }

    @Override
    public Reservation retrieveById(long id) {
        return reservationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            throw new IllegalArgumentException("L'identifiant est obligatoire pour la mise à jour");
        }
        Reservation existant = retrieveById(reservation.getIdReservation());
        verifier(reservation);
        existant.setDateDebut(reservation.getDateDebut());
        existant.setDateFin(reservation.getDateFin());
        existant.setStatut(reservation.getStatut());
        existant.setClient(reservation.getClient());
        existant.setVehicule(reservation.getVehicule());
        existant.setContrat(reservation.getContrat());
        return reservationRepo.save(existant);
    }

    private void verifier(Reservation reservation) {
        if (reservation.getDateDebut() == null || reservation.getDateFin() == null) {
            throw new IllegalArgumentException("Les dates de début et de fin sont obligatoires");
        }
        if (reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin est antérieure à la date de début");
        }
    }
}