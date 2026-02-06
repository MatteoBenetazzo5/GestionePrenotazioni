package matteobenetazzo.gestioneprenotazioni.services;

import matteobenetazzo.gestioneprenotazioni.entities.Postazione;
import matteobenetazzo.gestioneprenotazioni.entities.Prenotazione;
import matteobenetazzo.gestioneprenotazioni.entities.Utente;
import matteobenetazzo.gestioneprenotazioni.enums.TipoPostazione;
import matteobenetazzo.gestioneprenotazioni.repositories.PostazioneRepository;
import matteobenetazzo.gestioneprenotazioni.repositories.PrenotazioneRepository;
import matteobenetazzo.gestioneprenotazioni.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioniService {

    private final PostazioneRepository postazioneRepository;
    private final UtenteRepository utenteRepository;
    private final PrenotazioneRepository prenotazioneRepository;

    public PrenotazioniService(PostazioneRepository postazioneRepository, UtenteRepository utenteRepository, PrenotazioneRepository prenotazioneRepository) {
        this.postazioneRepository = postazioneRepository;
        this.utenteRepository = utenteRepository;
        this.prenotazioneRepository = prenotazioneRepository;
    }

    // 1) cerco le postazioni per tipo e città
    public List<Postazione> cercaPostazioni(TipoPostazione tipoPostazione, String citta) {
        return postazioneRepository.findByTipoPostazioneAndEdificio_Citta(tipoPostazione, citta);
    }

    // 2) prenotare una postazione
    public Prenotazione prenotaPostazione(Long idUtente, Long idPostazione, LocalDate giorno) {

        // prendo utente e postazione dal DB
        Utente utente = utenteRepository.findById(idUtente).orElse(null);
        Postazione postazione = postazioneRepository.findById(idPostazione).orElse(null);

        // se uno dei due non esiste, ritorno null
        if (utente == null || postazione == null) {
            return null;
        }

        // a) un UTENTE non può prenotare due volte nello stesso giorno
        boolean utenteGiaPrenotato = prenotazioneRepository.existsByUtenteAndGiorno(utente, giorno);
        if (utenteGiaPrenotato) {
            return null;
        }

        // b) una POSTAZIONE non può essere prenotata due volte nello stesso giorno
        boolean postazioneGiaPrenotata = prenotazioneRepository.existsByPostazioneAndGiorno(postazione, giorno);
        if (postazioneGiaPrenotata) {
            return null;
        }

        Prenotazione prenotazione = new Prenotazione(giorno, utente, postazione);
        return prenotazioneRepository.save(prenotazione);
    }
}


