// VerkehrsunternehmenService.java
package ae2.anzeigetafel.service;

import ae2.anzeigetafel.entity.Verkehrsunternehmen;
import ae2.anzeigetafel.repository.VerkehrsunternehmenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service                                  // Service für Verkehrsunternehmen
@RequiredArgsConstructor                  // Erstellt einen Konstruktor für die final-Felder
@Transactional(readOnly = true)          // Nur lesender Zugriff auf die Datenbank
public class VerkehrsunternehmenService {

    private final VerkehrsunternehmenRepository verkehrsunternehmenRepository;

    public List<Verkehrsunternehmen> getAllVerkehrsunternehmen() {
        // Gibt alle Verkehrsunternehmen zurück
        return verkehrsunternehmenRepository.findAll();
    }

    public Verkehrsunternehmen getVerkehrsunternehmenById(Integer id) {
        // Holt ein Verkehrsunternehmen per ID oder wirft einen Fehler
        return verkehrsunternehmenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Verkehrsunternehmen nicht gefunden"));
    }
}
