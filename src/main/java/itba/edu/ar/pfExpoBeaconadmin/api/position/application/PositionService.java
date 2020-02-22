package itba.edu.ar.pfExpoBeaconadmin.api.position.application;

import itba.edu.ar.pfExpoBeaconadmin.api.exception.PositionNotAvailableException;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.PositionNotFoundException;
import itba.edu.ar.pfExpoBeaconadmin.api.position.model.Position;
import itba.edu.ar.pfExpoBeaconadmin.api.position.model.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    List<Position> getPositionsAvailable() throws PositionNotFoundException {
        final List<Position> positions = positionRepository.findByUsedFalse();
        if (positions.isEmpty()) {
            throw new PositionNotFoundException();
        }
        return positions;
    }

    public Position used(final int positionId) throws PositionNotFoundException,
            PositionNotAvailableException {
        final Position position = getPosition(positionId);
        if (position.isUsed()) {
            throw new PositionNotAvailableException();
        }
        position.used();
        return positionRepository.save(position);
    }

    private Position getPosition(final int positionId) throws PositionNotFoundException {
        return positionRepository.findById(positionId)
                .orElseThrow(() -> new PositionNotFoundException("Position not found for this id :: " + positionId));
    }

    public void available(final double latitude, final double longitude) throws PositionNotFoundException {
        final Position position = positionRepository.findByLatitudeAndLongitude(latitude, longitude)
                .orElseThrow(() -> new PositionNotFoundException("Position not found for this latitude :: " + latitude +
                        "and :: " + longitude));
        position.avaliable();
        positionRepository.save(position);
    }
}
