package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconNotAvailableException;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.*;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconService;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.picture.application.PictureService;
import itba.edu.ar.pfExpoBeaconadmin.api.picture.model.Picture;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.StandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    private PictureService pictureService;

    private StandMapper standMapper = new StandMapper();

    /**
     * Create new stand
     *
     * @param standDTO Stand DTO
     * @return new Stand DTO
     */
    StandDTO create(final @Valid StandDTO standDTO) throws PictureStorageException,
            ResourceNotFoundException, BeaconNotAvailableException {
        final Beacon beacon = beaconService.used(standDTO.getBeaconId());
        standDTO.setBeacon(beacon);
        final List<Picture> pictures = pictureService.storePictures(standDTO.getUploadedFiles());
        standDTO.setPictures(pictures);
        return standMapper.toDto(standRepository.save(standMapper.toModel(standDTO)));
    }

    /**
     * @return list of stand's DTO
     */
    List<StandDTO> getAll() {
        return standRepository.findAll()
                .stream().map(stand -> standMapper.toDto(stand))
                .collect(Collectors.toList());
    }

    /**
     * @param standId String - Stand id
     * @return StandDTO
     * @throws ResourceNotFoundException if stand not found
     */
    StandDTO getById(final String standId) throws ResourceNotFoundException {
        return standMapper.toDto(getStandById(standId));
    }

    private Stand getStandById(final String standId) throws ResourceNotFoundException {
        return standRepository.findById(standId)
                .orElseThrow(() -> new ResourceNotFoundException("Stand not found for this id :: " + standId));
    }

    /**
     * @param standId String - Stand id
     * @throws ResourceNotFoundException if stand not found
     */
    void deleteById(final String standId) throws ResourceNotFoundException {
        final Stand stand = getStandById(standId);
        standRepository.delete(stand);
        beaconService.available(standId);
    }

    // TODO: (ma 2020-02-22) check this method
    StandDTO edit(final String standId, final @Valid StandDTO standDTO)
            throws ResourceNotFoundException, PictureStorageException, BeaconNotAvailableException {
        final Stand oldStand = getStandById(standId);
        if (StringUtils.isEmpty(standDTO.getId())) {
            standDTO.setId(standId);
        }
        if (!oldStand.getId().equalsIgnoreCase(standDTO.getId())) {
            final Beacon newBeacon = beaconService.used(standDTO.getId());
            standDTO.setBeacon(newBeacon);
            beaconService.available(oldStand.getId());
        }
        final List<Picture> pictures = pictureService.storePictures(standDTO.getUploadedFiles());
        standDTO.setPictures(pictures);
        return standMapper.toDto(standRepository.save(standMapper.toModel(standDTO)));
    }
}
