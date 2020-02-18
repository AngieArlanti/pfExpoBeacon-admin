package itba.edu.ar.pfExpoBeaconadmin.api.stand.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Stand Repository
 */
@Repository
public interface StandRepository extends JpaRepository<Stand, String> {
}
