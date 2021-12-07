package miejscowka.occupancy.receiver.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import miejscowka.occupancy.receiver.logic.to.OccupancyTo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public interface OccupancyRestService {

    @ApiOperation(value = "Set relative occupancy",
            tags = {"occupancy"},
            response = OccupancyTo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PostMapping(value = "occupancy/relative",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OccupancyTo> setRelativeOccupancy(@Validated @RequestBody OccupancyTo occupancyTo);

    @ApiOperation(value = "Set absolute occupancy",
            tags = {"occupancy"},
            response = OccupancyTo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 422, message = "Could not process entity"),
            @ApiResponse(code = 429, message = "Too many requests"),
    })
    @PostMapping(value = "occupancy/absolute",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OccupancyTo> setAbsoluteOccupancy(@Validated @RequestBody OccupancyTo occupancyTo);

}
