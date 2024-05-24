package services.security.rules;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una regla para la cantidad máxima de caracteres en una contraseña.
 * La regla se cumple si la longitud de la contraseña es menor o igual
 * a la cantidad máxima de caracteres.
 */

@Getter
@Setter
public class CantidadMaximaDeCaracteres extends Regla {
  Integer cantidadMaxima;

  public CantidadMaximaDeCaracteres(Integer unaCantidadMaxima) {
    this.setCantidadMaxima(unaCantidadMaxima);
    this.setMensajeError("Sobrepaso la cantidad maxima de caracteres");
  }

  @Override
  public Boolean cumpleLaRegla(String unaContrasenia) {
    return unaContrasenia.length() <= this.getCantidadMaxima();
  }
}
