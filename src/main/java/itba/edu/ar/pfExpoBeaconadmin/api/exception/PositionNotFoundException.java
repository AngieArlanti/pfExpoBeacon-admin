package itba.edu.ar.pfExpoBeaconadmin.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PositionNotFoundException extends Exception {

    public PositionNotFoundException() {
        super("Position not found");
    }

    public PositionNotFoundException(final String message) {
        super(message);
    }
}
