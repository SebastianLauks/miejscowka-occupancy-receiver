package miejscowka.occupancy.receiver.api;

import miejscowka.occupancy.receiver.exception.EntityDoesNotExistException;
import miejscowka.occupancy.receiver.logic.impl.OccupancyLogic;
import miejscowka.occupancy.receiver.logic.to.OccupancyTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.inject.Inject;

@RestController
public class OccupancyRestServiceImpl implements OccupancyRestService{

    @Inject
    private OccupancyLogic occupancyLogic;
    @Override
    public ResponseEntity<OccupancyTo> setRelativeOccupancy(OccupancyTo occupancyTo) {
        try {
            return ResponseEntity
                    .ok()
                    .body(occupancyLogic.updateOccupancyRelative(occupancyTo).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
        } catch (EntityDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<OccupancyTo> setAbsoluteOccupancy(OccupancyTo occupancyTo) {
        try {
            return ResponseEntity
                    .ok()
                    .body(occupancyLogic.updateOccupancyAbsolute(occupancyTo).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
        } catch (EntityDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
