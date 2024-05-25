package modules.security.rules;

/**
 * Representa una regla que verifica si una contraseña contiene tanto letras
 * mayúsculas como minúsculas.
 */
public class TieneMayusculasMinusculas extends Regla {
  public TieneMayusculasMinusculas() {
    this.setMensajeError("No tiene letras mayusculas y minusculas");
  }

  @Override
  public Boolean cumpleLaRegla(String unaContrasenia) {
    String regexMayuscula = "^(?=.*[A-Z]).*$";
    String regexMinuscula = "^(?=.*[a-z]).*$";
    return unaContrasenia.matches(regexMayuscula) && unaContrasenia.matches(regexMinuscula);
  }


}
