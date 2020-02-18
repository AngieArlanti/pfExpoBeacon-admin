package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends Exception {

    ResourceNotFoundException(String message) {
        super(message);
    }
}
