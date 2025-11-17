package ae2.anzeigetafel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity                                // Diese Klasse ist eine Tabelle in der Datenbank
@Data                                  // Lombok: erstellt automatisch Getter/Setter usw.
@Table(name = "verkehrsunternehmen")   // Tabellenname
public class Verkehrsunternehmen {

    @Id                                 // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;                // Name des Verkehrsunternehmens (z.B. "KVB")

    @Column(name = "logo_url")
    private String logoUrl;             // URL zum Logo (optional für Anzeige)

    @OneToMany(mappedBy = "verkehrsunternehmen")
    private List<Haltestelle> haltestellen;  // Alle Haltestellen dieses Unternehmens

    @OneToMany(mappedBy = "verkehrsunternehmen")
    private List<Linie> linien;             // Alle Linien dieses Unternehmens
}
