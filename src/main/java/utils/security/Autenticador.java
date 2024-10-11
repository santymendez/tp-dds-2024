package utils.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import utils.security.rules.Regla;

/**
 * Representa un autenticador que verifica si una contraseña cumple con un conjunto de reglas.
 * Cada regla se verifica en orden y si alguna regla no se cumple,
 * la contraseña se considera inválida.
 */
@Setter
@Getter
public class Autenticador {
  private List<Regla> politicas;
  private List<String> mensajesParaImprimir;

  public Autenticador() {
    this.politicas = new ArrayList<>();
    this.mensajesParaImprimir = new ArrayList<>();
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
    this.mensajesParaImprimir.clear(); //Para reinciar los mensajes cada vez que valido
    Boolean cumpleReglas = this.cumpleLasReglas(unaContrasenia);

    if (cumpleReglas) {
      System.out.println("Contraseña valida.");
    } else {
      System.out.println(this.mostrarMensajesConFormato());
    }
    return cumpleReglas;
  }

  public Boolean cumpleLasReglas(String unaContrasenia) {
    return politicas.stream().allMatch(m -> m.esValida(unaContrasenia, this.mensajesParaImprimir));
  }

  /**
   * Formatea los mensajes de error para su visualización.
   *
   * @return los mensajes de error formateados como una cadena de texto
   */
  public String mostrarMensajesConFormato() {
    StringBuilder mensajeCompleto = new StringBuilder();

    for (String mensaje : this.mensajesParaImprimir) {
      mensajeCompleto.append("Error: ").append(mensaje).append("\n");
    }

    return String.valueOf(mensajeCompleto);
  }
}
