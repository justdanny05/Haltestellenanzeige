// LinieController.java
package ae2.anzeigetafel.controller;

import ae2.anzeigetafel.entity.Linie;
import ae2.anzeigetafel.service.LinieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linien")
@RequiredArgsConstructor
public class LinieController {

    private final LinieService linieService;

    @GetMapping
    public List<Linie> getAllLinien() {
        // Gibt alle Linien zurück
        return linieService.getAllLinien();
    }

    @GetMapping("/{id}")
    public Linie getLinieById(@PathVariable Integer id) {
        // Gibt eine Linie per ID zurück
        return linieService.getLinieById(id);
    }

    @GetMapping("/verkehrsunternehmen/{vuId}")
    public List<Linie> getLinienByVerkehrsunternehmen(@PathVariable("vuId") Integer verkehrsunternehmenId) {
        // Gibt alle Linien eines Verkehrsunternehmens zurück
        return linieService.getLinienByVerkehrsunternehmen(verkehrsunternehmenId);
    }
}
