package itba.edu.ar.pfExpoBeaconadmin.api.position.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    List<Position> findByUsedFalse();

    Optional<Position> findByLatitudeAndLongitude(final double latitude, final double longitude);
}
