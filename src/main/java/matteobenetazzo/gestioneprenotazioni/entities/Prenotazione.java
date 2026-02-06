package matteobenetazzo.gestioneprenotazioni.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // giorno della prenotazione
    @Column(nullable = false) // nullable = false, dice che la colonna non può essere vuota
    private LocalDate giorno;

    // una prenotazione è fatta da 1 utente
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false) // nullable = false, dice che la colonna non può essere vuota
    // utente_id è connesso con la tabella UTENTI
    private Utente utente;

    // una prenotazione riguarda 1 postazione
    @ManyToOne
    @JoinColumn(name = "postazione_id", nullable = false) // nullable = false, dice che la colonna non può essere vuota
    // postazione_id è connesso con la tabella POSTAZIONI
    private Postazione postazione;

    // COSTRUTTORE VUOTO
    public Prenotazione() {
    }

    // COSTRUTTORE PER GLI OGGETTI
    public Prenotazione(LocalDate giorno, Utente utente, Postazione postazione) {
        this.giorno = giorno;
        this.utente = utente;
        this.postazione = postazione;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", giorno=" + giorno +
                ", utente=" + utente.getUsername() +
                ", postazione=" + postazione.getCodice() +
                '}';
    }
}



