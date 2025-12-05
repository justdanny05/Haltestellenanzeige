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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AbfahrtService {

    private final AbfahrtRepository abfahrtRepository;

    // ─────────────────────────────────────────────────────
    //   EINZELNE ABFAHRT LADEN
    // ─────────────────────────────────────────────────────
    public AbfahrtDTO getAbfahrtById(Integer id) {
        Abfahrt abfahrt = abfahrtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abfahrt nicht gefunden"));

        return mapToAbfahrtDTO(abfahrt, LocalDateTime.now());
    }

    // ─────────────────────────────────────────────────────
    //   ALLE ABFAHRTEN NACH LINIEN-ID LADEN
    // ─────────────────────────────────────────────────────
    public List<AbfahrtDTO> getAbfahrtenByLinie(Integer linienId) {
        LocalDateTime now = LocalDateTime.now();

        return abfahrtRepository.findAll().stream()
                .filter(a -> a.getLinie().getLinie_id().equals(linienId))
                .map(a -> mapToAbfahrtDTO(a, now))
                .toList();
    }

    // ─────────────────────────────────────────────────────
    //   ANZAHL DER ABFAHRTEN (für /test)
    // ─────────────────────────────────────────────────────
    public long count() {
        return abfahrtRepository.count();
    }

    // ─────────────────────────────────────────────────────
    //   ALLE ABFAHRTEN LADEN (Debug)
    // ─────────────────────────────────────────────────────
    public List<AbfahrtDTO> findAllAbfahrten() {
        LocalDateTime now = LocalDateTime.now();

        return abfahrtRepository.findAll().stream()
                .map(a -> mapToAbfahrtDTO(a, now))
                .toList();
    }

    // ─────────────────────────────────────────────────────
    //   MAPPING: ENTITY → DTO
    // ─────────────────────────────────────────────────────
    private AbfahrtDTO mapToAbfahrtDTO(Abfahrt abfahrt, LocalDateTime now) {

        // Tatsächliche Abfahrtszeit oder geplante
        LocalDateTime abfahrtZeit =
                abfahrt.getTatsaechliche_abfahrt() != null
                        ? abfahrt.getTatsaechliche_abfahrt()
                        : abfahrt.getGeplante_abfahrt();

        // Minuten bis Abfahrt berechnen
        long minutenBis = ChronoUnit.MINUTES.between(now, abfahrtZeit);

        // Störungen als DTO-Liste
        List<StoerungDTO> stoerungen = abfahrt.getStoerungen() != null
                ? abfahrt.getStoerungen().stream()
                .map(this::mapStoerung)
                .toList()
                : List.of();

        // DTO zurückgeben
        return new AbfahrtDTO(
                abfahrt.getLinie().getLiniennummer(),
                abfahrt.getLinie().getTyp(),
                abfahrt.getZiel(),
                abfahrt.getBahnsteig().getBezeichnung(),
                abfahrt.getGeplante_abfahrt(),
                abfahrt.getTatsaechliche_abfahrt(),
                abfahrt.getVerzoegerung_minuten(),
                minutenBis,
                stoerungen
        );
    }

    // ─────────────────────────────────────────────────────
    //   STÖRUNG MAPPEN
    // ─────────────────────────────────────────────────────
    private StoerungDTO mapStoerung(Stoerung stoerung) {
        return new StoerungDTO(
                stoerung.getTyp(),
                stoerung.getNachricht()
        );
    }
}