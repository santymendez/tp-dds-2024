package utils.security.rules;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una regla para la cantidad mínima de caracteres en una contraseña.
 * La regla se cumple si la longitud de la contraseña es mayor o igual
 * a la cantidad mínima de caracteres.
 */
@Getter
@Setter
public class CantidadMinimaDeCaracteres extends Regla {
  Integer cantidadMinima;

  public CantidadMinimaDeCaracteres(Integer unaCantidadMinima) {
    this.setCantidadMinima(unaCantidadMinima);
    this.setMensajeError("Tamaño de contraseña menor al permitido");
  }

  @Override
  public Boolean cumpleLaRegla(String unaContrasenia) {
    return unaContrasenia.length() >= this.getCantidadMinima();
  }
}
