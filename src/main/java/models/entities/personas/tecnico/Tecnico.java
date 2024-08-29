package models.entities.personas.tecnico;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;
import models.entities.direccion.Ciudad;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.documento.Documento;

/**
 * Representa a un técnico en el sistema.
 * Un técnico tiene un nombre, apellido, tipo y número de documento, CUIL, medio de contacto
 * y área de cobertura.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tecnicos")
public class Tecnico extends Persistente {

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "apellido", nullable = false)
  private String apellido;

  @Embedded
  private Documento documento;

  @Column(name = "cuil", nullable = false)
  private Integer cuil;

  @Embedded
  private Contacto contacto;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
  private Ciudad areaDeCobertura;

  /**
   * Metodo que permite al técnico registrar una visita a una heladera.
   *
   * @param heladera Heladera visitada para arreglar.
   */

  public Boolean puedeVisitar(Heladera heladera) {
    return !heladera.getEstadoActual().getEstado().equals(TipoEstado.ACTIVA);
  }

}
