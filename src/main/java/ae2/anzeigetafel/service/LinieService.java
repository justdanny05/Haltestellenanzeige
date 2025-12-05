// LinieService.java
package ae2.anzeigetafel.service;

import ae2.anzeigetafel.entity.Linie;
import ae2.anzeigetafel.entity.Verkehrsunternehmen;
import ae2.anzeigetafel.repository.LinieRepository;
import ae2.anzeigetafel.repository.VerkehrsunternehmenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service                                // Service-Klasse für Logik rund um Linien
@RequiredArgsConstructor                // Erstellt einen Konstruktor für die final-Felder
@Transactional(readOnly = true)         // Nur lesender Zugriff auf die Datenbank
public class LinieService {

    private final LinieRepository linieRepository;                       // Zugriff auf Linien
    private final VerkehrsunternehmenRepository verkehrsunternehmenRepository; // Zugriff auf Verkehrsunternehmen

    public List<Linie> getAllLinien() {
        // Gibt alle Linien zurück
        return linieRepository.findAll();
    }

    public Linie getLinieById(Integer id) {
        // Holt eine Linie per ID oder wirft einen Fehler, wenn sie nicht existiert
        return linieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Linie nicht gefunden"));
    }

    public List<Linie> getLinienByVerkehrsunternehmen(Integer verkehrsunternehmenId) {
        // Holt das Verkehrsunternehmen oder wirft einen Fehler
        Verkehrsunternehmen vu = verkehrsunternehmenRepository.findById(verkehrsunternehmenId)
                .orElseThrow(() -> new RuntimeException("Verkehrsunternehmen nicht gefunden"));

        // Gibt alle Linien dieses Unternehmens zurück
        return vu.getLinie();
    }
}
