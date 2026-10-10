package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Reservation;
import java.util.Set;

public interface IReservation {
    Reservation addReservation(Reservation reservation);
    void deleteReservation(long id);
    Set<Reservation> retrieveReservation();
    Reservation retrieveById(long id);
    Reservation updateReservation(Reservation reservation);
}