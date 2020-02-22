package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.exception.*;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class StandController {

    @Autowired
    private StandService standService;

    @PostMapping("/stand/add")
    public ResponseEntity<StandDTO> addStand(@RequestPart(value = "stand") @Valid final StandDTO standDTO,
                                             @RequestPart(value = "files") final List<MultipartFile> uploadedFile)
            throws BeaconNotFoundException, PositionNotFoundException, PositionNotAvailableException,
            PictureStorageException {
        standDTO.setUploadedFiles(uploadedFile);
        return ResponseEntity.ok(standService.create(standDTO));
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
    public ResponseEntity<Void> delete(final @PathVariable("id") String id) throws ResourceNotFoundException,
            PositionNotFoundException {
        standService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stand/edit")
    public ResponseEntity<Stand> editStand(@Valid final @RequestBody Stand stand) {
        return ResponseEntity.ok(standService.edit(stand));
    }
}
