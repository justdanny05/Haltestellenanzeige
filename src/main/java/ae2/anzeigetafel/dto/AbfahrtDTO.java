// AbfahrtDTO.java
package ae2.anzeigetafel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data                                   // Erstellt Getter/Setter automatisch
@NoArgsConstructor                      // Leerer Standard-Konstruktor
@AllArgsConstructor                     // Konstruktor mit allen Feldern
public class AbfahrtDTO {

    private String liniennummer;        // Nummer der Linie (z. B. "U6")
    private String linientyp;           // Art der Linie (z. B. "U-Bahn" oder "Bus")
    private String ziel;                // Zielort der Fahrt
    private String bahnsteig;           // Bahnsteig-Bezeichnung

    private LocalDateTime geplantAbfahrt;       // Planmäßige Abfahrtszeit
    private LocalDateTime tatsaechlicheAbfahrt; // Reale Abfahrtszeit (falls vorhanden)
    private Integer verzoegerungMinuten;        // Verspätung in Minuten

    private Long minutenBisAbfahrt;             // Minuten bis zur Abfahrt (berechnet im Service)

    private List<StoerungDTO> stoerungen;       // Liste der Störungen zu dieser Abfahrt
}
