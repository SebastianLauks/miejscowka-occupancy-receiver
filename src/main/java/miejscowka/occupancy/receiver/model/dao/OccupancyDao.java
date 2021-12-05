package miejscowka.occupancy.receiver.model.dao;


import miejscowka.occupancy.receiver.model.entity.OccupancyEntity;
import miejscowka.occupancy.receiver.model.entity.OccupancyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OccupancyDao extends JpaRepository<OccupancyEntity, OccupancyId> {

    Optional<OccupancyEntity> findByIdPlaceId(Long placeId);

    boolean existsOccupancyEntityById(OccupancyId occupancyId);
}
