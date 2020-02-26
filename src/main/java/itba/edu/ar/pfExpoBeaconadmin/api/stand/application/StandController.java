package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconNotAvailableException;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class StandController {

    @Autowired
    private StandService standService;

    @PostMapping("/stand/add")
    public ResponseEntity<StandDTO> addStand(@RequestPart(value = "stand") @Valid final StandDTO standDTO,
                                             @RequestPart(value = "files") final List<MultipartFile> uploadedFile)
            throws PictureStorageException, ResourceNotFoundException, BeaconNotAvailableException {
        standDTO.setUploadedFiles(uploadedFile);
        return ResponseEntity.ok(standService.create(standDTO));
    }

    @GetMapping("/stands")
    public List<StandDTO> getAllStands() {
        return standService.getAll();
    }

    @GetMapping("/stand/{id}")
    public ResponseEntity<StandDTO> getStandById(final @PathVariable("id") String id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(standService.getById(id));
    }

    @GetMapping("/stand/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable("id") String id) throws ResourceNotFoundException {
        standService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stand/edit/{id}")
    public ResponseEntity<StandDTO> editStand(final @PathVariable("id") String id,
                                              @RequestPart(value = "stand") @Valid final StandDTO standDTO,
                                              @RequestPart(value = "files") final List<MultipartFile> uploadedFile)
            throws ResourceNotFoundException, PictureStorageException, BeaconNotAvailableException {
        if (!StringUtils.isEmpty(standDTO.getId()) && !id.equalsIgnoreCase(standDTO.getId())) {
            //TODO: (ma 2020-02-22) check message
            throw new ValidationException("Invalid stand id");
        }
        standDTO.setUploadedFiles(uploadedFile);
        return ResponseEntity.ok(standService.edit(id, standDTO));
    }
}
