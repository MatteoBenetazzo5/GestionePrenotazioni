package matteobenetazzo.gestioneprenotazioni.repositories;

import matteobenetazzo.gestioneprenotazioni.entities.Postazione;
import matteobenetazzo.gestioneprenotazioni.entities.Prenotazione;
import matteobenetazzo.gestioneprenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    // controllo se una postazione è già prenotata in un certo giorno
    boolean existsByPostazioneAndGiorno(Postazione postazione, LocalDate giorno);

    // controllo se un utente ha già una prenotazione in un certo giorno
    boolean existsByUtenteAndGiorno(Utente utente, LocalDate giorno);
}

