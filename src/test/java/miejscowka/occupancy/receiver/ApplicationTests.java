package miejscowka.occupancy.receiver;

import miejscowka.occupancy.receiver.exception.EntityDoesNotExistException;
import miejscowka.occupancy.receiver.logic.impl.OccupancyLogic;
import miejscowka.occupancy.receiver.logic.mapper.OccupancyMapper;
import miejscowka.occupancy.receiver.logic.to.OccupancyTo;
import miejscowka.occupancy.receiver.model.dao.OccupancyDao;
import miejscowka.occupancy.receiver.model.dao.PlaceDao;
import miejscowka.occupancy.receiver.model.entity.CategoryEntity;
import miejscowka.occupancy.receiver.model.entity.OccupancyEntity;
import miejscowka.occupancy.receiver.model.entity.OccupancyId;
import miejscowka.occupancy.receiver.model.entity.PlaceEntity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicationTests {

	@Inject
	OccupancyLogic occupancyLogic;

	@Inject
	OccupancyMapper occupancyMapper;

	@Mock
	private OccupancyDao occupancyDao;

	@MockBean
	private PlaceDao placeDao;

	@Captor
	ArgumentCaptor<OccupancyEntity> occupancyCaptor;

	@Test
	void contextLoads() {
	}

	@Test
	void testUpdateOccupancyAbsolute() throws EntityDoesNotExistException {


		OccupancyEntity occupancy = new OccupancyEntity();
		Optional<OccupancyEntity> optional = Optional.of(occupancy);
		occupancy.setFalling(false);
		occupancy.setFalling(true);
		occupancy.setNumber_of_people(9);
		PlaceEntity place = new PlaceEntity("name", 10, "desc", "street", "21", "3", Collections.emptySet(), null,Collections.emptyList(), new CategoryEntity("categoryName", "Desc", Collections.emptyList()),Collections.emptyList());
		place.setCapacity(10);
		place.setId(1L);
		place.setCategory(new CategoryEntity("categoryName", "Desc", Collections.emptyList()));
		place.setDescription("desc");
		Optional<PlaceEntity> optionalPlace = Optional.of(place);
		occupancy.setPlace(place);
		occupancy.setPercentage_occupancy(9/10);
		doReturn(optional).when(occupancyDao).findByIdPlaceId(any());
		when(placeDao.findById(anyLong())).thenReturn(optionalPlace);

		OccupancyEntity occupancyNew = new OccupancyEntity();
		occupancyNew.setFalling(false);
		occupancyNew.setFalling(true);
		occupancyNew.setNumber_of_people(10);
		occupancyNew.setPlace(place);
		occupancyNew.setId(new OccupancyId(1L, LocalDateTime.now()));
		OccupancyTo occupancyTo = occupancyMapper.toOccupancyTo(occupancyNew);
		occupancyLogic.updateOccupancyAbsolute(occupancyLogic.toOccupancyTo(occupancyNew));

		verify(occupancyDao).save(occupancyCaptor.capture());
	}

	@Test
	void testUpdateOccupancyRelative() throws EntityDoesNotExistException {


		OccupancyEntity occupancy = new OccupancyEntity();
		Optional<OccupancyEntity> optional = Optional.of(occupancy);
		occupancy.setFalling(false);
		occupancy.setFalling(true);
		occupancy.setNumber_of_people(9);
		PlaceEntity place = new PlaceEntity("name", 10, "desc", "street", "21", "3", Collections.emptySet(), null,Collections.emptyList(), new CategoryEntity("categoryName", "Desc", Collections.emptyList()),Collections.emptyList());
		place.setCapacity(10);
		place.setId(1L);
		place.setCategory(new CategoryEntity("categoryName", "Desc", Collections.emptyList()));
		place.setDescription("desc");
		Optional<PlaceEntity> optionalPlace = Optional.of(place);
		occupancy.setPlace(place);
		occupancy.setPercentage_occupancy(9/10);
		doReturn(optional).when(occupancyDao).findByIdPlaceId(any());
		when(placeDao.findById(anyLong())).thenReturn(optionalPlace);

		OccupancyEntity occupancyNew = new OccupancyEntity();
		occupancyNew.setFalling(false);
		occupancyNew.setFalling(true);
		occupancyNew.setNumber_of_people(10);
		occupancyNew.setPlace(place);
		occupancyNew.setId(new OccupancyId(1L, LocalDateTime.now()));
		OccupancyTo occupancyTo = occupancyMapper.toOccupancyTo(occupancyNew);
		occupancyLogic.updateOccupancyRelative(occupancyLogic.toOccupancyTo(occupancyNew));

		verify(occupancyDao).save(occupancyCaptor.capture());
	}
}
