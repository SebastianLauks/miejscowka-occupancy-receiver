package miejscowka.occupancy.receiver.model.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PLACE_IMAGE")
public class PlaceImageEntity extends AbstractApplicationPersistenceEntity {


    public PlaceImageEntity() {
    }

    public PlaceImageEntity(PlaceEntity place, String url) {
        this.place = place;
        this.url = url;
    }

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PLACE_ID", nullable = false, referencedColumnName = "id")
    private PlaceEntity place;

    @NotNull
    @Column(name = "URL", nullable = false)
    private String url;

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlaceImageEntity that = (PlaceImageEntity) o;
        return place.equals(that.place) &&
                url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), place, url);
    }
}
