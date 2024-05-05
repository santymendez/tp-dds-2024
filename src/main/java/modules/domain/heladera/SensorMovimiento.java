package modules.domain.heladera;

import lombok.Getter;
import lombok.Setter;
import modules.domain.direccion.Direccion;

/**
 * Representa un sensor de movimiento con la dirección actual.
 */

@Getter
@Setter
public class SensorMovimiento {
  private Direccion ubicacionActual;

  /**
   * Crea una nueva regla que verifica si una contraseña pertenece a un archivo.
   *
   * @param direccion el nombre del archivo a verificar
   * @return valor de si está activa o no.
   */
  public Boolean estaActiva(Direccion direccion) {
    if (!this.getUbicacionActual().equals(direccion)) {
      this.alertarAlSistema();
    }
    return this.getUbicacionActual().equals(direccion);
  }

  public void alertarAlSistema() {
    System.out.println("La heladera fue o esta siendo robada");
  }
}