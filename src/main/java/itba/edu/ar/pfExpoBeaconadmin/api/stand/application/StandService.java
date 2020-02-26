package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconNotAvailableException;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.*;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconService;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
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

    private StandMapper standMapper = new StandMapper();

    /**
     * Create new stand
     *
     * @param standDTO Stand DTO
     * @return new Stand DTO
     */
    StandDTO create(final @Valid StandDTO standDTO) throws
            ResourceNotFoundException, BeaconNotAvailableException {
        final Beacon beacon = beaconService.used(standDTO.getBeaconId());
        standDTO.setBeacon(beacon);
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
            throws ResourceNotFoundException, BeaconNotAvailableException {
        final Stand oldStand = getStandById(standId);
        if (!oldStand.getId().equalsIgnoreCase(standDTO.getBeaconId())) {
            final Beacon beacon = beaconService.used(standDTO.getBeaconId());
            standDTO.setBeacon(beacon);
            deleteById(oldStand.getId());
        } else {
            final Beacon beacon = beaconService.getById(oldStand.getId());
            standDTO.setBeacon(beacon);
        }
        return standMapper.toDto(standRepository.save(standMapper.toModel(standDTO)));
    }
}
