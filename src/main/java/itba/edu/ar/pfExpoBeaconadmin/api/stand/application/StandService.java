package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

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

    /**
     * Create new stand
     *
     * @param stand Stand
     * @return new Stand
     */
    Stand create(final Stand stand) {
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
}
