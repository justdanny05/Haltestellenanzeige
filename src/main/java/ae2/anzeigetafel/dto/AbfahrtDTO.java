package ae2.anzeigetafel.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AbfahrtDTO(
        String liniennummer,
        String linienTyp,
        String ziel,
        String bahnsteigBezeichnung,
        LocalDateTime geplanteAbfahrt,
        LocalDateTime tatsaechlicheAbfahrt,
        Integer verzoegerungMinuten,
        Long minutenBis,
        List<StoerungDTO> stoerungen
) {}
