// Abfahrt.java
package ae2.anzeigetafel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity                      // Markiert diese Klasse als JPA-Entity (Datenbanktabelle)
@Data                        // Lombok: Generiert Getter, Setter, toString, equals, hashCode
@Table(name = "abfahrt")     // Verknüpft die Entity mit der Tabelle "abfahrt"
public class Abfahrt {

    @Id                      // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID wird automatisch von der Datenbank generiert (AUTO_INCREMENT / IDENTITY)
    private Integer abfahrt_id;

    @ManyToOne               // Viele Abfahrten gehören zu einer Linie
    @JoinColumn(name = "linie_id")  // Fremdschlüssel in der abfahrt-Tabelle
    private Linie linie;

    @ManyToOne               // Viele Abfahrten gehören zu einer Haltestelle
    @JoinColumn(name = "haltestelle_id") // FK zu haltestelle.id
    private Haltestelle haltestelle;

    @ManyToOne               // Jede Abfahrt hat genau einen Bahnsteig
    @JoinColumn(name = "bahnsteig_id") // FK zu bahnsteig.id
    private Bahnsteig bahnsteig;

    private String ziel;     // Fahrtziel (z. B. "Köln Hbf")

    @Column(name = "geplante_abfahrt")
    private LocalDateTime geplante_abfahrt;   // Offiziell geplante Abfahrtszeit

    @Column(name = "tatsaechliche_abfahrt")
    private LocalDateTime tatsaechliche_abfahrt;   // Echtzeit-Abfahrtszeit, wenn vorhanden

    @Column(name = "verzoegerung_minuten")
    private Integer verzoegerung_minuten;    // Verzögerung in Minuten

    @OneToMany(mappedBy = "abfahrt")
    private List<Stoerung> stoerungen;


}
