package modules.domain.personas.colaborador.alt;

import modules.domain.colaboracion.Colaboracion;
import modules.domain.empresa.Oferta;
import modules.domain.form.RespuestaFormulario;
import modules.domain.personas.colaborador.reconocimiento.Reconocimiento;
import modules.domain.personas.contacto.TipoContacto;

/**
 * Clase abstracta que representa a los colaboradores.
 */

public abstract class ColaboradorAlt {
  protected TipoContacto medioContacto;
  protected RespuestaFormulario respuestaFormulario;
  protected Reconocimiento reconocimiento;

  public void realizarColaboracion(Colaboracion colaboracion) {
    this.reconocimiento.sumarPuntos(colaboracion);
  }

  /**
   * El colaborador usa puntos para comprar una oferta si tiene suficientes.
   *
   * @param oferta La oferta es lo que el colabrador desea comprar con puntos.
   */

  public void usarPuntos(Oferta oferta) {
    if (reconocimiento.getPuntosPorColaborar() >= oferta.getPuntosNecesarios()) {
      this.reconocimiento.restarPuntos(oferta.getPuntosNecesarios());
    } else {
      throw new RuntimeException(
          "No tenes puntos suficientes para comprar esa oferta");
    }
  }
}
