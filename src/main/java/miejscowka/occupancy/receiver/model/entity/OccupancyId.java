package miejscowka.occupancy.receiver.model.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class OccupancyId implements Serializable {
    private Long placeId;
    private LocalDateTime timeId;

    public OccupancyId() {
    }

    public OccupancyId(Long placeId, LocalDateTime timeId) {
        this.placeId = placeId;
        this.timeId = timeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OccupancyId that = (OccupancyId) o;
        return placeId.equals(that.placeId) && timeId.equals(that.timeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeId, timeId);
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public LocalDateTime getTimeId() {
        return timeId;
    }

    public void setTimeId(LocalDateTime timeId) {
        this.timeId = timeId;
    }
}
