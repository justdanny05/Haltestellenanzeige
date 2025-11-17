// BahnsteigController.java
package ae2.anzeigetafel.controller;

import ae2.anzeigetafel.entity.Bahnsteig;
import ae2.anzeigetafel.service.BahnsteigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bahnsteige")
@RequiredArgsConstructor
public class BahnsteigController {

    private final BahnsteigService bahnsteigService;

    @GetMapping
    public List<Bahnsteig> getAllBahnsteige() {
        // Gibt alle Bahnsteige zurück
        return bahnsteigService.getAllBahnsteige();
    }

    @GetMapping("/{id}")
    public Bahnsteig getBahnsteigById(@PathVariable Integer id) {
        // Gibt einen Bahnsteig per ID zurück
        return bahnsteigService.getBahnsteigById(id);
    }

    @GetMapping("/haltestelle/{haltestelleId}")
    public List<Bahnsteig> getBahnsteigeByHaltestelle(@PathVariable Integer haltestelleId) {
        // Gibt alle Bahnsteige einer bestimmten Haltestelle zurück
        return bahnsteigService.getBahnsteigeByHaltestelle(haltestelleId);
    }
}
