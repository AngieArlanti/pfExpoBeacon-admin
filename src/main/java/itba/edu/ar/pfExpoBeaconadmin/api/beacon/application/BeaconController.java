package itba.edu.ar.pfExpoBeaconadmin.api.beacon.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class BeaconController {

    @Autowired
    private BeaconService beaconService;

    @GetMapping("stands/available_beacons")
    public List<Beacon> getBeaconsAvailable() {
        return beaconService.getBeaconAvailable();
    }
}
