package models.db;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa a una entidad persistente.
 */

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadPersistente {
  @Id
  @GeneratedValue
  private long id;
}
