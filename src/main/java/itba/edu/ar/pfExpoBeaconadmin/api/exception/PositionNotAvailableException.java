package itba.edu.ar.pfExpoBeaconadmin.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PositionNotAvailableException extends Exception {

    public PositionNotAvailableException() {
        super("Position not available");
    }
}
