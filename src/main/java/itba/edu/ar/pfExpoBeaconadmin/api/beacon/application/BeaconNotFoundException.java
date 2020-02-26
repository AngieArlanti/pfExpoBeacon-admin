package itba.edu.ar.pfExpoBeaconadmin.api.beacon.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BeaconNotFoundException extends Exception {

    public BeaconNotFoundException() {
        super("no beacon available");
    }
}
