package itba.edu.ar.pfExpoBeaconadmin.api.beacon.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.BeaconRepository;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.BeaconNotFoundException;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeaconService {

    @Autowired
    private BeaconRepository beaconRepository;

    public Beacon getOneBeaconNotUsed() throws BeaconNotFoundException {
        return beaconRepository.findFirstByUsedFalse().orElseThrow(BeaconNotFoundException::new);
    }

    public void used(final Beacon beacon) {
        beacon.used();
        beaconRepository.save(beacon);
    }

    private Beacon getById(final String beaconId) throws ResourceNotFoundException {
        return beaconRepository.findById(beaconId)
                .orElseThrow(() -> new ResourceNotFoundException("Beacon not found for this id :: " + beaconId));
    }

    public void available(final String beaconId) throws ResourceNotFoundException {
        final Beacon beacon = getById(beaconId);
        beacon.avaliable();
        beaconRepository.save(beacon);
    }

    public Beacon getBeacon() throws BeaconNotFoundException {
        final Beacon beacon = getOneBeaconNotUsed();
        used(beacon);
        return beacon;
    }
}
