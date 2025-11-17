// HaltestelleController.java
package ae2.anzeigetafel.controller;

import ae2.anzeigetafel.dto.HaltestelleInfoDTO;
import ae2.anzeigetafel.entity.Haltestelle;
import ae2.anzeigetafel.service.HaltestelleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                         // Stellt REST-Endpunkte bereit
@RequestMapping("/api/haltestellen")    // Basis-URL für alle Haltestellen-Endpunkte
@RequiredArgsConstructor
public class HaltestelleController {

    private final HaltestelleService haltestelleService;

    @GetMapping
    public List<Haltestelle> getAllHaltestellen() {
        // Gibt alle Haltestellen zurück
        return haltestelleService.getAllHaltestellen();
    }

    @GetMapping("/{id}/info")
    public HaltestelleInfoDTO getHaltestelleInfo(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer limit
    ) {
        // Gibt Infos zu einer Haltestelle inkl. Abfahrten zurück
        // Optional: limit = Anzahl der Abfahrten
        return haltestelleService.getHaltestelleInfo(id, limit);
    }
}
