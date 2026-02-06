package matteobenetazzo.gestioneprenotazioni.runners;

import matteobenetazzo.gestioneprenotazioni.entities.Edificio;
import matteobenetazzo.gestioneprenotazioni.entities.Postazione;
import matteobenetazzo.gestioneprenotazioni.entities.Prenotazione;
import matteobenetazzo.gestioneprenotazioni.entities.Utente;
import matteobenetazzo.gestioneprenotazioni.enums.TipoPostazione;
import matteobenetazzo.gestioneprenotazioni.repositories.EdificioRepository;
import matteobenetazzo.gestioneprenotazioni.repositories.PostazioneRepository;
import matteobenetazzo.gestioneprenotazioni.repositories.UtenteRepository;
import matteobenetazzo.gestioneprenotazioni.services.PrenotazioniService;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TestRunner implements CommandLineRunner {

    private final EdificioRepository edificioRepository;
    private final PostazioneRepository postazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PrenotazioniService prenotazioniService;

    public TestRunner(EdificioRepository edificioRepository, PostazioneRepository postazioneRepository, UtenteRepository utenteRepository, PrenotazioniService prenotazioniService) {
        this.edificioRepository = edificioRepository;
        this.postazioneRepository = postazioneRepository;
        this.utenteRepository = utenteRepository;
        this.prenotazioniService = prenotazioniService;
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {

        // 1) CREO 1 EDIFICIO
        Edificio edificio1 = new Edificio("Sede SpaceX", "Via Musk 89", "LosAngeles");
        edificio1 = edificioRepository.save(edificio1);

        // 2) CREO 3 POSTAZIONI
        Postazione p1 = new Postazione("LA-PR-01", "Ufficio Privato", TipoPostazione.PRIVATO, 1, edificio1);
        Postazione p2 = new Postazione("LA-OP-01", "Ufficio open space vicino alle finestre", TipoPostazione.OPENSPACE, 6, edificio1);
        Postazione p3 = new Postazione("LA-SR-01", "Sala riunioni grande", TipoPostazione.SALA_RIUNIONI, 10, edificio1);

        p1 = postazioneRepository.save(p1);
        p2 = postazioneRepository.save(p2);
        p3 = postazioneRepository.save(p3);

        // 3) CREO 2 UTENTI
        Utente u1 = new Utente("matt", "Mattia Bianchi", "matt@email.it");
        Utente u2 = new Utente("luca", "Luca Rossi", "luca@email.it");

        u1 = utenteRepository.save(u1);
        u2 = utenteRepository.save(u2);

        // 4) TEST RICERCA POSTAZIONI PER TIPO e CITTA'
        System.out.println("---- POSTAZIONI OPENSPACE A LOSANGELES ----");
        List<Postazione> risultatoRicerca = prenotazioniService.cercaPostazioni(TipoPostazione.OPENSPACE, "LosAngeles");
        for (Postazione p : risultatoRicerca) {
            System.out.println(p);
        }

        // 5) CREO 1 PRENOTAZIONE
        LocalDate giorno = LocalDate.now();
        Prenotazione pren1 = prenotazioniService.prenotaPostazione(u1.getId(), p2.getId(), giorno);
        System.out.println("PRENOTATO " + pren1);

    }
}


