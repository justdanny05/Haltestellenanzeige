package ae2.anzeigetafel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity                          // Diese Klasse ist eine Tabelle in der Datenbank
@Data                            // Erstellt automatisch Getter/Setter usw.
@Table(name = "haltestelle")     // Name der Tabelle in der Datenbank
public class Haltestelle {

    @Id                           // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer haltestelle_id;

    private String name;          // Name der Haltestelle

    @ManyToOne                    // Eine Haltestelle gehört zu einem Verkehrsunternehmen
    @JoinColumn(name = "verkehrsunternehmen_id")
    private Verkehrsunternehmen verkehrsunternehmen;

    @OneToMany(mappedBy = "haltestelle")
    private List<Bahnsteig> bahnsteig; // Alle Bahnsteige dieser Haltestelle
}
