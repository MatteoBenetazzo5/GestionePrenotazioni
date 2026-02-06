package matteobenetazzo.gestioneprenotazioni.entities;

import jakarta.persistence.*;
import lombok.Getter;
import matteobenetazzo.gestioneprenotazioni.enums.TipoPostazione;

@Getter
@Entity
@Table(name = "postazioni")
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codice;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;
    private int maxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id") // edificio_id è connesso con la tabella EDIFICI
    private Edificio edificio;

    // COSTRUTTORE VUOTO
    public Postazione() {
    }

    // COSTRUTTORE PER GLI OGGETTI
    public Postazione(String codice, String descrizione, TipoPostazione tipoPostazione, int maxOccupanti, Edificio edificio) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.tipoPostazione = tipoPostazione;
        this.maxOccupanti = maxOccupanti;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", tipoPostazione=" + tipoPostazione +
                ", maxOccupanti=" + maxOccupanti +
                ", edificio=" + edificio +
                '}';
    }
}
