package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.exception.*;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconService;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.picture.application.PictureService;
import itba.edu.ar.pfExpoBeaconadmin.api.picture.model.Picture;
import itba.edu.ar.pfExpoBeaconadmin.api.position.application.PositionService;
import itba.edu.ar.pfExpoBeaconadmin.api.position.model.Position;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.StandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

/**
 * Stand Service
 */
@Service
class StandService {

    @Autowired
    private StandRepository standRepository;

    @Autowired
    private BeaconService beaconService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PictureService pictureService;

    private StandMapper standMapper = new StandMapper();

    /**
     * Create new stand
     *
     * @param standDTO Stand DTO
     * @return new Stand
     * @throws BeaconNotFoundException
     * @throws PositionNotFoundException
     */
    StandDTO create(final @Valid StandDTO standDTO) throws BeaconNotFoundException, PositionNotFoundException,
            PositionNotAvailableException, PictureStorageException {
        final Beacon beacon = beaconService.getBeacon();
        standDTO.setId(beacon.getId());
        final Position position = positionService.used(standDTO.getPositionId());
        standDTO.setPosition(position);
        final List<Picture> pictures = pictureService.storePictures(standDTO.getUploadedFiles());
        standDTO.setPictures(pictures);
        return standMapper.toDto(standRepository.save(standMapper.toModel(standDTO)));
    }

    /**
     * @return list of stands
     */
    List<Stand> getAll() {
        return standRepository.findAll();
    }

    /**
     * @param standId String - Stand id
     * @return Stand
     * @throws ResourceNotFoundException if stand not found
     */
    Stand getById(final String standId) throws ResourceNotFoundException {
        return standRepository.findById(standId)
                .orElseThrow(() -> new ResourceNotFoundException("Stand not found for this id :: " + standId));
    }

    /**
     * @param standId String - Stand id
     * @throws ResourceNotFoundException if stand not found
     */
    void deleteById(final String standId) throws ResourceNotFoundException, PositionNotFoundException {
        final Stand stand = getById(standId);
        standRepository.delete(stand);
        beaconService.available(standId);
        positionService.available(stand.getLatitude(), stand.getLongitude());
    }

    Stand edit(final Stand stand) {
        return standRepository.save(stand);
    }
}
