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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HaltestelleService {

    private final HaltestelleRepository haltestelleRepository;
    private final AbfahrtRepository abfahrtRepository;

    public HaltestelleInfoDTO getHaltestelleInfo(Integer haltestelleId, Integer limit) {
        Haltestelle haltestelle = haltestelleRepository.findById(haltestelleId)
                .orElseThrow(() -> new RuntimeException("Haltestelle nicht gefunden"));

        LocalDateTime now = LocalDateTime.now();

        List<Abfahrt> abfahrten = abfahrtRepository.findUpcomingByHaltestelleId(haltestelleId, now);

        if (limit != null && limit > 0) {
            abfahrten = abfahrten.stream().limit(limit).toList();
        }

        List<AbfahrtDTO> abfahrtDTOs = abfahrten.stream()
                .map(a -> mapToAbfahrtDTO(a, now))
                .toList();

        return new HaltestelleInfoDTO(
                haltestelle.getName(),
                haltestelle.getVerkehrsunternehmen().getName(),
                haltestelle.getVerkehrsunternehmen().getLogo_url(),
                now,
                abfahrtDTOs
        );
    }

    private AbfahrtDTO mapToAbfahrtDTO(Abfahrt abfahrt, LocalDateTime now) {

        LocalDateTime abfahrtZeit = (abfahrt.getTatsaechliche_abfahrt() != null)
                ? abfahrt.getTatsaechliche_abfahrt()
                : abfahrt.getGeplante_abfahrt();

        long minutenBis = ChronoUnit.MINUTES.between(now, abfahrtZeit);

        List<StoerungDTO> stoerungen = abfahrt.getStoerungen() != null
                ? abfahrt.getStoerungen().stream()
                .map(this::mapToStoerungDTO)
                .toList()
                : List.of();

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

    private StoerungDTO mapToStoerungDTO(Stoerung stoerung) {
        return new StoerungDTO(
                stoerung.getTyp(),
                stoerung.getNachricht()
        );
    }

    public List<Haltestelle> getAllHaltestellen() {
        return haltestelleRepository.findAll();
    }
}
