package itba.edu.ar.pfExpoBeaconadmin.api.picture.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository  extends JpaRepository<Picture, Integer> {
}
