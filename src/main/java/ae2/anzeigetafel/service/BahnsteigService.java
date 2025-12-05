// BahnsteigService.java
package ae2.anzeigetafel.service;

import ae2.anzeigetafel.entity.Bahnsteig;
import ae2.anzeigetafel.entity.Haltestelle;
import ae2.anzeigetafel.repository.BahnsteigRepository;
import ae2.anzeigetafel.repository.HaltestelleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service                                   // Service für Logik rund um Bahnsteige
@RequiredArgsConstructor                   // Erstellt automatisch einen Konstruktor
@Transactional(readOnly = true)            // Nur lesender Datenbankzugriff
public class BahnsteigService {

    private final BahnsteigRepository bahnsteigRepository;       // Zugriff auf Bahnsteige
    private final HaltestelleRepository haltestelleRepository;   // Zugriff auf Haltestellen

    public List<Bahnsteig> getAllBahnsteige() {
        // Gibt alle Bahnsteige zurück
        return bahnsteigRepository.findAll();
    }

    public Bahnsteig getBahnsteigById(Integer id) {
        // Holt einen Bahnsteig per ID oder wirft Fehler
        return bahnsteigRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bahnsteig nicht gefunden"));
    }

    public List<Bahnsteig> getBahnsteigeByHaltestelle(Integer haltestelleId) {
        // Holt Haltestelle
        Haltestelle haltestelle = haltestelleRepository.findById(haltestelleId)
                .orElseThrow(() -> new RuntimeException("Haltestelle nicht gefunden"));

        // Gibt alle Bahnsteige dieser Haltestelle zurück
        return haltestelle.getBahnsteig();
    }
}
