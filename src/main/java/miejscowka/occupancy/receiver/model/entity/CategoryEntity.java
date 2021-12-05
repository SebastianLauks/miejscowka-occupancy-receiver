package miejscowka.occupancy.receiver.model.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity extends AbstractApplicationPersistenceEntity {

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, unique = true)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<PlaceEntity> places;

    public CategoryEntity() {
    }

    public CategoryEntity(String name, String description, List<PlaceEntity> places) {
        this.name = name;
        this.description = description;
        this.places = places;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PlaceEntity> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceEntity> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CategoryEntity that = (CategoryEntity) o;
        return name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(places, that.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, places);
    }
}
