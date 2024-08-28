package models.entities.heladera.modulos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.entities.personas.colaborador.suscripcion.InterfazSuscripcion;

/**
 * Clase que representa al modulo de la heladera
 * encargado de controlar las suscripciones.
 */

@Getter
@Embeddable
public class ModuloDeSuscripciones {

  @Transient // TODO
  private final List<InterfazSuscripcion> suscripciones;

  public ModuloDeSuscripciones() {
    this.suscripciones = new ArrayList<>();
  }

  /**
   * Intentara notificar a sus suscriptores.
   */

  public void intentarNotificarSuscriptores() {
    if (!this.suscripciones.isEmpty()) {
      this.suscripciones.parallelStream().forEach(InterfazSuscripcion::intentarNotificar);
    }
  }

  public void agregarSuscripcion(InterfazSuscripcion suscripcion) {
    this.suscripciones.add(suscripcion);
  }

  public void eliminarSuscripcion(InterfazSuscripcion suscripcion) {
    this.suscripciones.remove(suscripcion);
  }
}
