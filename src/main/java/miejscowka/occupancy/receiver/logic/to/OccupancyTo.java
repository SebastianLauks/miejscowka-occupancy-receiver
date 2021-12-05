package miejscowka.occupancy.receiver.logic.to;

import java.time.LocalDateTime;

public class OccupancyTo {

    //in this logic it can be current number of people in the place
    //or number which indicates how many people came to the place or went out of it
    //depending on which endpoint will be used
    private int numberOfPeople;
    private Long placeId;
    private LocalDateTime time;

    public OccupancyTo() {
    }

    public OccupancyTo(int numberOfPeople, Long placeId, LocalDateTime time) {
        this.numberOfPeople = numberOfPeople;
        this.placeId = placeId;
        this.time = time;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}


