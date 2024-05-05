package modules.security.rules;

/**
 * Representa una regla que verifica si una contraseña tiene caracteres especiales.
 * La regla se cumple si la contraseña contiene al menos un carácter especial.
 */
public class TieneCaracteresEspeciales extends Regla {

  public TieneCaracteresEspeciales() {
    this.setMensajeError("No tiene caracteres especiales");
  }

  @Override
  public Boolean cumpleLaRegla(String unaContrasenia) {
    String regexCaracteresEspeciales = "^(?=.*[!@#$%^&*_+\\-.<>]).*$";
    return unaContrasenia.matches(regexCaracteresEspeciales);
  }

}
