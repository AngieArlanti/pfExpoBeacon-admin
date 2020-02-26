package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconNotAvailableException;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class StandController {

    @Autowired
    private StandService standService;

    @PostMapping("/stand/add")
    public ResponseEntity<StandDTO> addStand(@RequestBody @Valid final StandDTO standDTO)
            throws ResourceNotFoundException, BeaconNotAvailableException {
        return ResponseEntity.ok(standService.create(standDTO));
    }

    @GetMapping("/stands")
    public List<StandDTO> getAllStands() {
        return standService.getAll();
    }

    @GetMapping("/stand/{id}")
    public ResponseEntity<StandDTO> getStandById(@PathVariable("id") final String id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(standService.getById(id));
    }

    @GetMapping("/stand/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String id) throws ResourceNotFoundException {
        standService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stand/edit/{id}")
    public ResponseEntity<StandDTO> editStand(@PathVariable("id") final String id,
                                              @RequestBody @Valid final StandDTO standDTO)
            throws ResourceNotFoundException, BeaconNotAvailableException {
        if (!StringUtils.isEmpty(standDTO.getId()) && !id.equalsIgnoreCase(standDTO.getId())) {
            //TODO: (ma 2020-02-22) check message
            throw new ValidationException("Invalid stand id");
        }
        return ResponseEntity.ok(standService.edit(id, standDTO));
    }
}
