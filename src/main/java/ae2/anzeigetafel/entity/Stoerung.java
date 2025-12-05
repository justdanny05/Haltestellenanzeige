package ae2.anzeigetafel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "stoerung")
public class Stoerung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stoerung_id")
    private Integer stoerung_id;

    @Column(name = "typ")
    private String typ;

    @Column(name = "nachricht")
    private String nachricht;

    @Column(name = "zeitstempel")
    private LocalDateTime zeitstempel;

    @ManyToOne
    @JoinColumn(name = "abfahrt_id", nullable = false)
    private Abfahrt abfahrt;
}
