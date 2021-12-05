package miejscowka.occupancy.receiver.model.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PERMISSION")
public class PermissionEntity extends AbstractApplicationPersistenceEntity {
  private static final long serialVersionUID = 1L;

  public PermissionEntity() {
  }

  public PermissionEntity(ApplicationPermissions name, String description) {
    this.name = name;
    this.description = description;
  }

  @NotNull
  @Column(name = "NAME", nullable = false, unique = true)
  @Enumerated(EnumType.STRING)
  private ApplicationPermissions name;

  @Column(name = "DESCRIPTION")
  private String description;

  public ApplicationPermissions getName() {
    return name;
  }

  public void setName(ApplicationPermissions name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PermissionEntity)) return false;
    if (!super.equals(o)) return false;
    PermissionEntity that = (PermissionEntity) o;
    return name == that.name &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description);
  }
}
