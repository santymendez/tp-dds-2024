package services.security.rules;

import java.util.List;
import lombok.Setter;

/**
 * Representa una regla de seguridad abstracta que puede ser implementada por
 * diferentes tipos de reglas.
 * Cada regla tiene un mensaje de error que se muestra cuando la regla no se cumple.
 */

@Setter
public abstract class Regla {
  private String mensajeError;

  /**
   * Verifica si una contraseña es válida según la regla.
   * Si la regla no se cumple, agrega el mensaje de error a una lista de mensajes.
   *
   * @param unaContrasenia la contraseña a verificar
   * @param mensajes la lista de mensajes a la que se agregará el mensaje de error
   *                 si la regla no se cumple
   * @return true si la contraseña cumple la regla, false en caso contrario
   */

  public Boolean esValida(String unaContrasenia, List<String> mensajes) {
    if (!this.cumpleLaRegla(unaContrasenia)) {
      mensajes.add(this.mensajeError);
    }
    return this.cumpleLaRegla(unaContrasenia);
  }

  public abstract Boolean cumpleLaRegla(String unaContrasenia);
}
