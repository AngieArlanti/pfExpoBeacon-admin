package itba.edu.ar.pfExpoBeaconadmin.api.picture.application;

import itba.edu.ar.pfExpoBeaconadmin.api.exception.PictureStorageException;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.ResourceNotFoundException;
import itba.edu.ar.pfExpoBeaconadmin.api.picture.model.Picture;
import itba.edu.ar.pfExpoBeaconadmin.api.picture.model.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public Picture storePicture(final MultipartFile file) throws PictureStorageException {
        final String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            final Picture picture = new Picture(fileName, file.getContentType(), file.getBytes());
            return pictureRepository.save(picture);
        } catch (IOException e) {
            throw new PictureStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public Picture getPicture(final int pictureId) throws ResourceNotFoundException {
        return pictureRepository.findById(pictureId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found for this id :: " + pictureId));
    }

    public List<Picture> storePictures(final List<MultipartFile> files) throws PictureStorageException {
        final List<Picture> pictures = new ArrayList<>();
        for (MultipartFile file: files) {
            pictures.add(storePicture(file));
        }
        return pictures;
    }
}
