package itba.edu.ar.pfExpoBeaconadmin.api.beacon.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.BeaconRepository;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.BeaconNotFoundException;
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
        beacon.setUsed(true);
        beaconRepository.save(beacon);
    }
}
