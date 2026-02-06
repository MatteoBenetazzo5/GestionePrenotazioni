package matteobenetazzo.gestioneprenotazioni.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // username deve essere unico quindi scrivo unique = true
    // nullable = false, dice che la colonna non può essere vuota
    private String username;

    @Column(name = "nome_completo", nullable = false) // nullable = false, dice che la colonna non può essere vuota
    private String nomeCompleto;

    @Column(nullable = false, unique = true) // univoca anche l'email
    // nullable = false, dice che la colonna non può essere vuota
    private String email;

    // COSTRUTTORE VUOTO
    public Utente() {
    }

    // COSTRUTTORE PER GLI OGGETTI
    public Utente(String username, String nomeCompleto, String email) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
