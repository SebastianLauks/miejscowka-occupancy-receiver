package miejscowka.occupancy.receiver.logic.mapper;

import miejscowka.occupancy.receiver.logic.to.OccupancyTo;
import miejscowka.occupancy.receiver.model.entity.OccupancyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OccupancyMapper {
    OccupancyTo toOccupancyTo(OccupancyEntity occupancyEntity);
}
