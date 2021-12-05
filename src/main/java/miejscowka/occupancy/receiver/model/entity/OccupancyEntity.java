package miejscowka.occupancy.receiver.model.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "OCCUPANCY")
public class OccupancyEntity {

    @EmbeddedId
    private OccupancyId id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "PLACE_ID", nullable = false, referencedColumnName = "id")
    @MapsId("placeId")
    private PlaceEntity place;

    @NotNull
    @Column(name = "NUMBER_OF_PEOPLE", nullable = false)
    private int number_of_people;

    @Column(name = "PERCENTAGE")
    private int percentage_occupancy;

    public OccupancyEntity() {
    }

    public OccupancyId getId() {
        return id;
    }

    public void setId(OccupancyId id) {
        this.id = id;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public int getNumber_of_people() {
        return number_of_people;
    }

    public void setNumber_of_people(int number_of_people) {
        this.number_of_people = number_of_people;
    }

    public int getPercentage_occupancy() {
        return percentage_occupancy;
    }

    public void setPercentage_occupancy(int percentage_occupancy) {
        this.percentage_occupancy = percentage_occupancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OccupancyEntity that = (OccupancyEntity) o;
        return number_of_people == that.number_of_people &&
                percentage_occupancy == that.percentage_occupancy &&
                Objects.equals(id, that.id) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, number_of_people, percentage_occupancy);
    }
}
