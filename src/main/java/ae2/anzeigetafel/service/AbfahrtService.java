package ae2.anzeigetafel.service;

import ae2.anzeigetafel.dto.AbfahrtDTO;
import ae2.anzeigetafel.dto.StoerungDTO;
import ae2.anzeigetafel.entity.Abfahrt;
import ae2.anzeigetafel.entity.Stoerung;
import ae2.anzeigetafel.repository.AbfahrtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service                                   // Service für Logik rund um Abfahrten
@RequiredArgsConstructor                   // Automatischer Konstruktor für finale Felder
@Transactional(readOnly = true)            // Nur lesender Zugriff auf die DB
public class AbfahrtService {

    private final AbfahrtRepository abfahrtRepository; // Zugriff auf Abfahrten

    public AbfahrtDTO getAbfahrtById(Integer id) {
        // Einzelne Abfahrt laden oder Fehlermeldung werfen
        Abfahrt abfahrt = abfahrtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abfahrt nicht gefunden"));

        return mapToAbfahrtDTO(abfahrt, LocalDateTime.now());
    }

    public List<AbfahrtDTO> getAbfahrtenByLinie(Integer linienId) {
        // Alle Abfahrten laden und nach Linien-ID filtern
        List<Abfahrt> abfahrten = abfahrtRepository.findAll().stream()
                .filter(a -> a.getLinie().getId().equals(linienId))
                .collect(Collectors.toList());

        LocalDateTime now = LocalDateTime.now();

        // Jede Abfahrt in ein DTO umwandeln
        return abfahrten.stream()
                .map(a -> mapToAbfahrtDTO(a, now))
                .collect(Collectors.toList());
    }

    // Wandelt eine Abfahrt in ein AbfahrtDTO um
    private AbfahrtDTO mapToAbfahrtDTO(Abfahrt abfahrt, LocalDateTime now) {

        // Tatsächliche oder geplante Abfahrtszeit bestimmen
        LocalDateTime abfahrtZeit = abfahrt.getTatsaechlicheAbfahrt() != null
                ? abfahrt.getTatsaechlicheAbfahrt()
                : abfahrt.getGeplantAbfahrt();

        // Minuten bis zur Abfahrt berechnen
        long minutenBis = ChronoUnit.MINUTES.between(now, abfahrtZeit);

        // Störungen zur Abfahrt als DTO-Liste
        List<StoerungDTO> stoerungen = abfahrt.getStoerungen() != null
                ? abfahrt.getStoerungen().stream()
                .map(this::mapToStoerungDTO)
                .collect(Collectors.toList())
                : List.of();

        // DTO zurückgeben
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

    // Wandelt eine Störung in ein StörungDTO um
    private StoerungDTO mapToStoerungDTO(Stoerung stoerung) {
        return new StoerungDTO(
                stoerung.getTyp(),
                stoerung.getNachricht()
        );
    }
}