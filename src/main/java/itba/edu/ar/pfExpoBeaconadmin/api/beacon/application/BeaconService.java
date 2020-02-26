package itba.edu.ar.pfExpoBeaconadmin.api.beacon.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.BeaconRepository;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeaconService {

    @Autowired
    private BeaconRepository beaconRepository;

    private Beacon getById(final String beaconId) throws ResourceNotFoundException {
        return beaconRepository.findById(beaconId)
                .orElseThrow(() -> new ResourceNotFoundException("Beacon not found for this id :: " + beaconId));

    }

    public Beacon used(final String beaconId) throws ResourceNotFoundException, BeaconNotAvailableException {
        final Beacon beacon = getById(beaconId);
        if (beacon.isUsed()) {
            throw new BeaconNotAvailableException();
        }
        beacon.used();
        return beaconRepository.save(beacon);
    }

    public void available(final String beaconId) throws ResourceNotFoundException {
        final Beacon beacon = getById(beaconId);
        beacon.avaliable();
        beaconRepository.save(beacon);
    }

    List<Beacon> getBeaconAvailable() {
        return beaconRepository.findByUsedFalse();
    }

}
