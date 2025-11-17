// HaltestelleInfoDTO.java
package ae2.anzeigetafel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data                                   // Erstellt automatisch Getter/Setter usw.
@NoArgsConstructor                      // Leerer Standard-Konstruktor
@AllArgsConstructor                     // Konstruktor mit allen Feldern
public class HaltestelleInfoDTO {

    private String haltestelle;         // Name der Haltestelle
    private String verkehrsunternehmen; // Name des Verkehrsunternehmens
    private String logoUrl;             // Logo des Verkehrsunternehmens

    private LocalDateTime aktuelleZeit; // Zeitpunkt der Abfrage (z. B. für Anzeige)

    private List<AbfahrtDTO> abfahrten; // Liste aller kommenden Abfahrten
}
