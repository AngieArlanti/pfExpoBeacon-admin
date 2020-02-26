package itba.edu.ar.pfExpoBeaconadmin.api.beacon.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeaconRepository extends JpaRepository<Beacon, String> {

    Optional<Beacon> findFirstByUsedFalse();

    List<Beacon> findByUsedFalse();
}
