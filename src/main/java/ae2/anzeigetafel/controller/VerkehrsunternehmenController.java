// VerkehrsunternehmenController.java
package ae2.anzeigetafel.controller;

import ae2.anzeigetafel.entity.Verkehrsunternehmen;
import ae2.anzeigetafel.service.VerkehrsunternehmenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verkehrsunternehmen")
@RequiredArgsConstructor
public class VerkehrsunternehmenController {

    private final VerkehrsunternehmenService verkehrsunternehmenService;

    @GetMapping
    public List<Verkehrsunternehmen> getAllVerkehrsunternehmen() {
        // Gibt alle Verkehrsunternehmen zurück
        return verkehrsunternehmenService.getAllVerkehrsunternehmen();
    }

    @GetMapping("/{id}")
    public Verkehrsunternehmen getVerkehrsunternehmenById(@PathVariable Integer id) {
        // Gibt ein Verkehrsunternehmen per ID zurück
        return verkehrsunternehmenService.getVerkehrsunternehmenById(id);
    }
}
