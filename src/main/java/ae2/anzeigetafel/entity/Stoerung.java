// Stoerung.java
package ae2.anzeigetafel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity                          // Diese Klasse ist eine Tabelle in der Datenbank
@Data                            // Lombok: erstellt Getter/Setter automatisch
@Table(name = "stoerung")        // Tabellenname
public class Stoerung {

    @Id                           // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne                    // Eine Störung gehört zu einer Abfahrt
    @JoinColumn(name = "abfahrt_id")
    private Abfahrt abfahrt;

    private String typ;           // Art der Störung (z. B. "Signalstörung")

    private String nachricht;     // Beschreibung/Details zur Störung

    private LocalDateTime zeitstempel; // Zeitpunkt, wann die Störung gemeldet wurde
}
