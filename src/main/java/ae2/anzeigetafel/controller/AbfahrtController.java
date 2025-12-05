// AbfahrtController.java
package ae2.anzeigetafel.controller;

import ae2.anzeigetafel.dto.AbfahrtDTO;
import ae2.anzeigetafel.service.AbfahrtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abfahrten")
@RequiredArgsConstructor
public class AbfahrtController {

    private final AbfahrtService abfahrtService;

    @GetMapping("/{id}")
    public AbfahrtDTO getAbfahrtById(@PathVariable Integer id) {
        // Gibt eine Abfahrt als DTO zurück
        return abfahrtService.getAbfahrtById(id);
    }



    @GetMapping("/linie/{linienId}")
    public List<AbfahrtDTO> getAbfahrtenByLinie(@PathVariable Integer linienId) {
        // Gibt alle Abfahrten zu einer bestimmten Linie zurück
        return abfahrtService.getAbfahrtenByLinie(linienId);
    }

    @GetMapping("/test")
    public String testDb() {
        long count = abfahrtService.count();
        return "Abfahrten in DB: " + count;
    }

    //TEST
    @GetMapping
    public List<AbfahrtDTO> getAlleAbfahrten() {
        return abfahrtService.findAllAbfahrten();
    }

}
