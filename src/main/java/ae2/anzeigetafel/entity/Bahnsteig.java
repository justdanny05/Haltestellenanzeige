// Bahnsteig.java
package ae2.anzeigetafel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity                             // Markiert die Klasse als Datenbank-Entity
@Data                               // Lombok: generiert Getter/Setter/toString/etc.
@Table(name = "bahnsteig")          // Verknüpfung mit der Tabelle "bahnsteig"
public class Bahnsteig {

    @Id                              // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Automatische ID-Erzeugung (IDENTITY = AutoIncrement in SQLite/PostgreSQL)
    private Integer id;

    @ManyToOne                        // Viele Bahnsteige gehören zu einer Haltestelle
    @JoinColumn(name = "haltestelle_id")
    // Fremdschlüssel in der Tabelle "bahnsteig", verweist auf haltestelle.id
    private Haltestelle haltestelle;

    private String beschreibung;      // Name/Bezeichnung des Bahnsteigs (z. B. "Gleis 1")

    @OneToMany(mappedBy = "bahnsteig")
    // Eine Haltestelle → viele Abfahrten, die an diesem Bahnsteig stattfinden
    private List<Abfahrt> abfahrten;
}
