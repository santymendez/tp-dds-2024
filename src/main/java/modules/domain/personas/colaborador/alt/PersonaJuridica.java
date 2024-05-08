package modules.domain.personas.colaborador.alt;

import lombok.Getter;
import lombok.Setter;
import modules.domain.form.RespuestaFormulario;
import modules.domain.personas.contacto.TipoContacto;
//import modules.domain.personas.colaborador.reconocimiento.Reconocimiento;

/**
 * Representa a una Persona juridica que desee colaborar.
 */

@Getter
@Setter //(Modificar un colaborador)
public class PersonaJuridica extends ColaboradorAlt {
  private String razonSocial;
  private String tipo;
  private String rubro;

  /**
   * Constructor de la clase PersonaJuridica (Dar de alta).
   *
   * @param razonSocial nombre de la empresa.
   * @param tipo apellido de la empresa.
   * @param rubro apellido de la empresa.
   * @param medioContacto medio de contacto de la empresa.
   * @param respuestaFormulario respuesta del formulario de la empresa.
   */

  public PersonaJuridica(String razonSocial, String tipo, String rubro,
                         TipoContacto medioContacto, RespuestaFormulario respuestaFormulario) {
    this.razonSocial = razonSocial;
    this.tipo = tipo;
    this.rubro = rubro;
    this.medioContacto = medioContacto;
    this.respuestaFormulario = respuestaFormulario;
    this.reconocimiento = null; //new Reconocimiento(); //TODO
  }
}