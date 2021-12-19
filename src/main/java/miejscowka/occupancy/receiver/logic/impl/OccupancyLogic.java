package miejscowka.occupancy.receiver.logic.impl;

import miejscowka.occupancy.receiver.exception.EntityDoesNotExistException;
import miejscowka.occupancy.receiver.logic.to.OccupancyTo;
import miejscowka.occupancy.receiver.model.dao.OccupancyDao;
import miejscowka.occupancy.receiver.model.dao.PlaceDao;
import miejscowka.occupancy.receiver.model.entity.OccupancyEntity;
import miejscowka.occupancy.receiver.model.entity.OccupancyId;
import miejscowka.occupancy.receiver.model.entity.PlaceEntity;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class OccupancyLogic {
    private static final Logger LOG = LoggerFactory.getLogger(OccupancyLogic.class);
    private static final String UPDATE_OCCUPANCY_LOG = "Update Occupancy with placeId {} in database.";
    private static final String UPDATE_OCCUPANCY_CHANGE_LOG = "Update Occupancy change with placeId {} in database.";

    @Value("${app.service.domain}")
    private String serviceDomain ;
    @Value("${app.service.port}")
    private int servicePort;
    @Value("${app.service.api.path.prefix}")
    private String serviceApiPathPrefix;
    @Value("${app.service.api.path}")
    private String serviceApiPath;

    @Inject
    private OccupancyDao occupancyDao;

    @Inject
    private PlaceDao placeDao;

    RestTemplate restTemplate = new RestTemplate();

    public Optional<OccupancyTo> updateOccupancyRelative(OccupancyTo occupancyTo) throws EntityDoesNotExistException {

        LOG.debug(UPDATE_OCCUPANCY_CHANGE_LOG, occupancyTo.getPlaceId());

        PlaceEntity placeEntity = placeDao.findById(occupancyTo.getPlaceId()).orElseThrow(() -> new EntityDoesNotExistException("Place with id " + occupancyTo.getPlaceId() + "does not exist"));

        Optional<OccupancyEntity> occupancyEntityOpt = occupancyDao.findByIdPlaceId(occupancyTo.getPlaceId());

        OccupancyEntity occupancyEntity;

        if(occupancyEntityOpt.isPresent()){
            occupancyEntity = occupancyEntityOpt.get();
            occupancyDao.delete(occupancyEntity);

            int newOccupancy = occupancyEntity.getNumber_of_people() + occupancyTo.getNumberOfPeople();

            occupancyEntity.getId().setTimeId(occupancyTo.getTime());
            occupancyEntity.setNumber_of_people(newOccupancy);
            occupancyEntity.setPercentage_occupancy(calculatePercentageOccupancy(newOccupancy, placeEntity.getCapacity()));
        }else {
            occupancyEntity = createNewOccupancy(occupancyTo.getPlaceId(), occupancyTo.getNumberOfPeople(), occupancyTo.getTime(), placeEntity);
        }
        occupancyDao.save(occupancyEntity);
        notifyOccupancyModelingMicroservice(toOccupancyTo(occupancyEntity));

        return Optional.of(toOccupancyTo(occupancyEntity));
    }

    public Optional<OccupancyTo> updateOccupancyAbsolute(OccupancyTo occupancyTo) throws EntityDoesNotExistException {

        LOG.debug(UPDATE_OCCUPANCY_LOG, occupancyTo.getPlaceId());

        PlaceEntity placeEntity = placeDao.findById(occupancyTo.getPlaceId()).orElseThrow(() -> new EntityDoesNotExistException("Place with id " + occupancyTo.getPlaceId() + "does not exist"));

        Optional<OccupancyEntity> occupancyEntityOpt = occupancyDao.findByIdPlaceId(occupancyTo.getPlaceId());

        OccupancyEntity occupancyEntity;

        if(occupancyEntityOpt.isPresent()){
            occupancyEntity = occupancyEntityOpt.get();
            occupancyDao.delete(occupancyEntity);

            occupancyEntity.getId().setTimeId(occupancyTo.getTime());
            occupancyEntity.setNumber_of_people(occupancyTo.getNumberOfPeople());
            occupancyEntity.setPercentage_occupancy(calculatePercentageOccupancy(occupancyTo.getNumberOfPeople(), placeEntity.getCapacity()));
        }else {
            occupancyEntity = createNewOccupancy(occupancyTo.getPlaceId(), occupancyTo.getNumberOfPeople(), occupancyTo.getTime(), placeEntity);
        }

        occupancyDao.save(occupancyEntity);
        notifyOccupancyModelingMicroservice(toOccupancyTo(occupancyEntity));

        return Optional.of(toOccupancyTo(occupancyEntity));
    }

    private OccupancyEntity createNewOccupancy(Long placeId, Integer occupancyChange, LocalDateTime time, PlaceEntity placeEntity) {
        OccupancyEntity occupancyEntity;
        OccupancyId occupancyId = new OccupancyId();
        occupancyId.setPlaceId(placeId);
        occupancyId.setTimeId(time);

        occupancyEntity = new OccupancyEntity();
        occupancyEntity.setId(occupancyId);
        occupancyEntity.setPlace(placeEntity);
        occupancyEntity.setNumber_of_people(occupancyChange);
        occupancyEntity.setPercentage_occupancy(calculatePercentageOccupancy(occupancyChange, placeEntity.getCapacity()));
        return occupancyEntity;
    }

    private int calculatePercentageOccupancy(Integer numberOfPeople, Integer capacity){
        return (int) ((numberOfPeople * 100) / (capacity));
    }

    private void notifyOccupancyModelingMicroservice(OccupancyTo occupancyTo){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
            map.add("numberOfPeople", occupancyTo.getNumberOfPeople());
            map.add("time", occupancyTo.getTime());

            String url = "http://" + serviceDomain + ":" + servicePort + "/" + serviceApiPathPrefix + "/" + occupancyTo.getPlaceId() + "/" + serviceApiPath;

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class);
        } catch (Exception e){
            LOG.info("There was a problem with notifying occupancy modeling microservice");
        }
    }

    private OccupancyTo toOccupancyTo(OccupancyEntity occupancyEntity){
        OccupancyTo occupancyTo = new OccupancyTo();

        occupancyTo.setNumberOfPeople(occupancyEntity.getNumber_of_people());
        occupancyTo.setTime(occupancyEntity.getId().getTimeId());
        occupancyTo.setPlaceId(occupancyEntity.getId().getPlaceId());
        return  occupancyTo;
    }

}
