package ae2.anzeigetafel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "bahnsteig")
public class Bahnsteig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bahnsteig_id")
    private Integer bahnsteig_id;

    // STRING-Feld – keine Beziehung!
    @Column(name = "bezeichnung")
    private String bezeichnung;

    // FK zu Haltestelle
    @ManyToOne
    @JoinColumn(name = "haltestelle_id", nullable = false)
    private Haltestelle haltestelle;

    // Alle Abfahrten, die an diesem Bahnsteig stattfinden
    @OneToMany(mappedBy = "bahnsteig")
    private List<Abfahrt> bahnsteig;
}
