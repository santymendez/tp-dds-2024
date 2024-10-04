package models.entities.personas.tecnico;

import javax.persistence.*;

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
import models.entities.personas.users.Usuario;

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

  @OneToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "apellido", nullable = false)
  private String apellido;

  @Embedded
  private Documento documento;

  @Column(name = "cuil", nullable = false, unique = true)
  private String cuil;

  @Embedded
  private Contacto contacto;

  @Embedded
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
