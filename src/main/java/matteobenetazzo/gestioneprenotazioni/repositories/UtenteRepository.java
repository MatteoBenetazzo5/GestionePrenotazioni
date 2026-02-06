package matteobenetazzo.gestioneprenotazioni.repositories;

import matteobenetazzo.gestioneprenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}

