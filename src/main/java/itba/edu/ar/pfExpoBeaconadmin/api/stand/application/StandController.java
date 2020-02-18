package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class StandController {

    @Autowired
    private StandService standService;

    @PostMapping("/stand/add")
    public ResponseEntity<Stand> addStand(final @RequestBody Stand stand) {
        //TODO validate info
        final Stand newStand = standService.create(stand);
        return ResponseEntity.ok(newStand);
    }

    @GetMapping("/stands")
    public List<Stand> getAllStands() {
        return standService.getAll();
    }

    @GetMapping("/stand/{id}")
    public ResponseEntity<Stand> getStandById(final @PathVariable("id") String id) throws ResourceNotFoundException {
        //TODO validate id
        final Stand stand = standService.getById(id);
        return ResponseEntity.ok(stand);
    }
}
