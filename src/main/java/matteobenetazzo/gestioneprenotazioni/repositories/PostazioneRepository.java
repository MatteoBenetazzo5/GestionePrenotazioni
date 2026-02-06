package matteobenetazzo.gestioneprenotazioni.repositories;

import matteobenetazzo.gestioneprenotazioni.entities.Postazione;
import matteobenetazzo.gestioneprenotazioni.enums.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    // cerco le postazioni per tipo e città dell'edificio
    List<Postazione> findByTipoPostazioneAndEdificio_Citta(TipoPostazione tipoPostazione, String citta);
}

