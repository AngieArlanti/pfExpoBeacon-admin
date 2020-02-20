package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.exception.BeaconNotFoundException;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.ResourceNotFoundException;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class StandController {

    @Autowired
    private StandService standService;

    @PostMapping("/stand/add")
    public ResponseEntity<Stand> addStand(@Valid final @RequestBody Stand stand) throws BeaconNotFoundException {
        //TODO: (ma 2020-2-19) validate info
        final Stand newStand = standService.create(stand);
        return ResponseEntity.ok(newStand);
    }

    @GetMapping("/stands")
    public List<Stand> getAllStands() {
        return standService.getAll();
    }

    @GetMapping("/stand/{id}")
    public ResponseEntity<Stand> getStandById(final @PathVariable("id") String id) throws ResourceNotFoundException {
        final Stand stand = standService.getById(id);
        return ResponseEntity.ok(stand);
    }

    @GetMapping("/stand/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable("id") String id) throws ResourceNotFoundException {
        standService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stand/edit")
    public ResponseEntity<Stand> editStand(@Valid final @RequestBody Stand stand) {
        //TODO: (ma 2020-2-20) validate info
        return ResponseEntity.ok(standService.edit(stand));
    }
}
