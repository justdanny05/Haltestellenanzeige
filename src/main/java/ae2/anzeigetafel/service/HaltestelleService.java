// HaltestelleService.java
package ae2.anzeigetafel.service;

import ae2.anzeigetafel.dto.AbfahrtDTO;
import ae2.anzeigetafel.dto.HaltestelleInfoDTO;
import ae2.anzeigetafel.dto.StoerungDTO;
import ae2.anzeigetafel.entity.Abfahrt;
import ae2.anzeigetafel.entity.Haltestelle;
import ae2.anzeigetafel.entity.Stoerung;
import ae2.anzeigetafel.repository.AbfahrtRepository;
import ae2.anzeigetafel.repository.HaltestelleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service                               // Spring-Service für Geschäftslogik
@RequiredArgsConstructor               // Erstellt automatisch einen Konstruktor für finale Felder
@Transactional(readOnly = true)        // Lese-Transaktionen (sicher & performant)
public class HaltestelleService {

    private final HaltestelleRepository haltestelleRepository; // Zugriff auf Haltestellen
    private final AbfahrtRepository abfahrtRepository;         // Zugriff auf Abfahrten

    public HaltestelleInfoDTO getHaltestelleInfo(Integer haltestelleId, Integer limit) {
        // Haltestelle aus der Datenbank holen oder Fehler werfen
        Haltestelle haltestelle = haltestelleRepository.findById(haltestelleId)
                .orElseThrow(() -> new RuntimeException("Haltestelle nicht gefunden"));

        LocalDateTime now = LocalDateTime.now(); // aktueller Zeitpunkt

        // Alle kommenden Abfahrten dieser Haltestelle laden
        List<Abfahrt> abfahrten = abfahrtRepository.findUpcomingByHaltestelleId(haltestelleId, now);

        // Falls ein Limit angegeben wurde, Liste kürzen
        if (limit != null && limit > 0) {
            abfahrten = abfahrten.stream().limit(limit).collect(Collectors.toList());
        }

        // Jede Abfahrt in ein DTO umwandeln
        List<AbfahrtDTO> abfahrtDTOs = abfahrten.stream()
                .map(a -> mapToAbfahrtDTO(a, now))
                .collect(Collectors.toList());

        // Gesamtinfo über die Haltestelle als DTO zurückgeben
        return new HaltestelleInfoDTO(
                haltestelle.getName(),
                haltestelle.getVerkehrsunternehmen().getName(),
                haltestelle.getVerkehrsunternehmen().getLogoUrl(),
                now,
                abfahrtDTOs
        );
    }

    private AbfahrtDTO mapToAbfahrtDTO(Abfahrt abfahrt, LocalDateTime now) {
        // Effektive Abfahrtszeit: tatsächliche oder geplante
        LocalDateTime abfahrtZeit = abfahrt.getTatsaechlicheAbfahrt() != null
                ? abfahrt.getTatsaechlicheAbfahrt()
                : abfahrt.getGeplantAbfahrt();

        // Minuten bis zur Abfahrt berechnen
        long minutenBis = ChronoUnit.MINUTES.between(now, abfahrtZeit);

        // Störungen dieser Abfahrt in DTOs umwandeln
        List<StoerungDTO> stoerungen = abfahrt.getStoerungen() != null
                ? abfahrt.getStoerungen().stream()
                .map(this::mapToStoerungDTO)
                .collect(Collectors.toList())
                : List.of();

        // AbfahrtDTO erzeugen und zurückgeben
        return new AbfahrtDTO(
                abfahrt.getLinie().getLiniennummer(),
                abfahrt.getLinie().getTyp(),
                abfahrt.getZiel(),
                abfahrt.getBahnsteig().getBeschreibung(),
                abfahrt.getGeplantAbfahrt(),
                abfahrt.getTatsaechlicheAbfahrt(),
                abfahrt.getVerzoegerungMinuten(),
                minutenBis,
                stoerungen
        );
    }

    private StoerungDTO mapToStoerungDTO(Stoerung stoerung) {
        // Wandelt eine Störung in ein einfaches DTO um
        return new StoerungDTO(
                stoerung.getTyp(),
                stoerung.getNachricht()
        );
    }

    public List<Haltestelle> getAllHaltestellen() {
        // Gibt alle Haltestellen zurück
        return haltestelleRepository.findAll();
    }
}
