// StoerungDTO.java
package ae2.anzeigetafel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                               // Erstellt Getter/Setter automatisch
@NoArgsConstructor                  // Leerer Standard-Konstruktor
@AllArgsConstructor                 // Konstruktor mit allen Feldern
public class StoerungDTO {

    private String typ;             // Art der Störung (z. B. "Signalstörung")
    private String nachricht;       // Beschreibung / Details der Störung
}
