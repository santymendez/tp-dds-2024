package services.security.rules;

/**
 * Representa una regla que verifica si una contraseña contiene números.
 */
public class TieneNumeros extends Regla {

  public TieneNumeros() {
    this.setMensajeError("No tiene numeros");
  }

  @Override
  public Boolean cumpleLaRegla(String unaContrasenia) {
    String regexNumeros = "^(?=.*[0-9]).*$";
    return unaContrasenia.matches(regexNumeros);
  }

}
