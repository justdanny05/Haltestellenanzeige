package ae2.anzeigetafel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity                           // Diese Klasse ist eine Tabelle in der Datenbank
@Data                             // Lombok: erstellt Getter/Setter automatisch
@Table(name = "linie")            // Tabellenname in der Datenbank
public class Linie {

    @Id                            // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "liniennummer")
    private String liniennummer;   // Nummer der Linie (z. B. "U6")

    private String typ;            // Art der Linie (z. B. "U-Bahn", "Bus")

    @ManyToOne                     // Eine Linie gehört zu einem Verkehrsunternehmen
    @JoinColumn(name = "verkehrsunternehmen_id")
    private Verkehrsunternehmen verkehrsunternehmen;

    @OneToMany(mappedBy = "linie")
    private List<Abfahrt> abfahrten; // Alle Abfahrten dieser Linie
}
