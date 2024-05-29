package modules.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.security.rules.Regla;

/**
 * Representa un autenticador que verifica si una contraseña cumple con un conjunto de reglas.
 * Cada regla se verifica en orden y si alguna regla no se cumple,
 * la contraseña se considera inválida.
 */
@Setter
@Getter
public class Autenticador {
  private List<Regla> politicas;

  public Autenticador() {
    this.setPoliticas(new ArrayList<>());
  }

  public void agregarPoliticas(Regla... reglas) {
    Collections.addAll(this.getPoliticas(), reglas);
  }

  /**
   * Verifica si una contraseña es válida según las reglas del autenticador.
   *
   * @param unaContrasenia la contraseña a verificar
   * @return true si la contraseña cumple todas las reglas, false en caso contrario
   */

  public Boolean esValida(String unaContrasenia) {

    List<String> mensajesDeError = new ArrayList<>();

    if (this.cumpleLasReglas(unaContrasenia, mensajesDeError)) {
      System.out.println("Contraseña valida.");
    } else {
      System.out.println(this.mostrarMensajesConFormato(mensajesDeError));
    }
    return cumpleLasReglas(unaContrasenia, mensajesDeError);
  }

  public Boolean cumpleLasReglas(String unaContrasenia, List<String> mensajesDeError) {
    return politicas.stream().allMatch(m -> m.esValida(unaContrasenia, mensajesDeError));
  }

  /**
   * Formatea los mensajes de error para su visualización.
   *
   * @param mensajes una lista de mensajes de error
   * @return los mensajes de error formateados como una cadena de texto
   */
  public String mostrarMensajesConFormato(List<String> mensajes) {
    StringBuilder mensajeCompleto = new StringBuilder();

    for (String mensaje : mensajes) {
      mensajeCompleto.append("Error: ").append(mensaje).append(" ");
    }

    return String.valueOf(mensajeCompleto);
  }
}
