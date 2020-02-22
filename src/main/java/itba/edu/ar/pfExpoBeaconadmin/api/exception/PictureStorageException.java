package itba.edu.ar.pfExpoBeaconadmin.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PictureStorageException extends Exception {

    public PictureStorageException(final String message, IOException exception) {
        super(message, exception);
    }
}
