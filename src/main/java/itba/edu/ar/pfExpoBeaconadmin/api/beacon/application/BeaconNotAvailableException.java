package itba.edu.ar.pfExpoBeaconadmin.api.beacon.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BeaconNotAvailableException extends Exception {

    public BeaconNotAvailableException() {
        super("Beacon not available");
    }
}
