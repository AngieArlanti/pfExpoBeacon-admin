package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.exception.BeaconNotFoundException;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconService;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.ResourceNotFoundException;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.StandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * Create new stand
     *
     * @param stand Stand
     * @return new Stand
     * @throws BeaconNotFoundException
     */
    Stand create(final Stand stand) throws BeaconNotFoundException {
        Beacon beacon = beaconService.getOneBeaconNotUsed();
        beaconService.used(beacon);
        stand.setId(beacon.getId());
        return standRepository.save(stand);
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

    void deleteById(final String standId) throws ResourceNotFoundException {
        final Stand stand = getById(standId);
        standRepository.delete(stand);
    }

    Stand edit(final Stand stand) {
        return standRepository.save(stand);
    }
}
