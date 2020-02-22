package itba.edu.ar.pfExpoBeaconadmin.api.position.application;

import itba.edu.ar.pfExpoBeaconadmin.api.exception.PositionNotFoundException;
import itba.edu.ar.pfExpoBeaconadmin.api.position.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("stands/available_positions")
    public List<Position> getLPositionsAvailable() throws PositionNotFoundException {
        return positionService.getPositionsAvailable();
    }
}
