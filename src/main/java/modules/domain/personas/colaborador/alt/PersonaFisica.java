package modules.domain.personas.colaborador.alt;

import lombok.Getter;
import lombok.Setter;
import modules.domain.form.RespuestaFormulario;
import modules.domain.personas.contacto.TipoContacto;
//import modules.domain.personas.colaborador.reconocimiento.Reconocimiento;

/**
 * Representa a una Persona Fisica que desee colaborar.
 */

@Getter
@Setter //(Modificar un colaborador)
public class PersonaFisica extends ColaboradorAlt {
  private String nombre;
  private String apellido;

  /**
   * Constructor de la clase PersonaFisica (Dar de alta).
   *
   * @param nombre nombre de la persona.
   * @param apellido apellido de la persona.
   * @param medioContacto medio de contacto de la persona.
   * @param respuestaFormulario respuesta del formulario de la persona.
   */
  
  public PersonaFisica(String nombre, String apellido,
                       TipoContacto medioContacto, RespuestaFormulario respuestaFormulario) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.medioContacto = medioContacto;
    this.respuestaFormulario = respuestaFormulario;
    this.reconocimiento = null; //new Reconocimiento(); //TODO
  }
}
