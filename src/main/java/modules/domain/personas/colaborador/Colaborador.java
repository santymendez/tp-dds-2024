package modules.domain.personas.colaborador;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.domain.colaboracion.Colaboracion;
import modules.domain.direccion.Direccion;
import modules.domain.form.RespuestaFormulario;
import modules.domain.personas.colaborador.reconocimiento.Reconocimiento;

/**
 * Representa a un colaborador en el sistema.
 */

@Getter
@Setter
public class Colaborador {
  //Persona fisica
  private String nombre;
  private String apellido;
  private String medioContacto;

  //Persona Juridica
  private String razonSocial;
  private String tipo;
  private String rubro;
  private Direccion direccion;

  //Ambos
  private TipoColaborador tipoColaborador;
  private RespuestaFormulario respuestaFormulario;
  private Reconocimiento reconocimiento;
  private List<Colaboracion> colaboraciones;

  public void realizarColaboracion(Colaboracion colaboracion) {
    colaboraciones.add(colaboracion);
  }
}
