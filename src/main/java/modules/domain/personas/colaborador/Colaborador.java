package modules.domain.personas.colaborador;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import modules.domain.colaboracion.Colaboracion;
import modules.domain.direccion.Direccion;
import modules.domain.empresa.Oferta;
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

  //Persona Juridica
  private String razonSocial;
  private String tipo;
  private String rubro;
  private Direccion direccion;

  //Ambos
  private String medioContacto;
  private TipoColaborador tipoColaborador;
  private RespuestaFormulario respuestaFormulario;
  private Reconocimiento reconocimiento;
  private List<Colaboracion> colaboraciones;

  /* //Constructor dependiendo del tipo de colaborador;

  public Colaborador(TipoColaborador tipoColaborador,
   RespuestaFormulario respuestaFormulario) {

    this.tipoColaborador = tipoColaborador;
    this.respuestaFormulario = respuestaFormulario;
    this.rellenarDatosConRespuestas(tipoColaborador, respuestaFormulario);
    this.reconocimiento = new Reconocimiento();
  }

  public void rellenarDatosConRespuestas(TipoColaborador tipoColaborador,
   RespuestaFormulario respuestaFormulario) {

    this.medioContacto = respuestaFormulario.getNombre();
    switch(tipoColaborador) {
      case FISICO -> {
        this.nombre = respuestaFormulario.respuestaNombre();
        this.apellido = respuestaFormulario.getNombre();
      }
      case JURIDICO -> {
        this.razonSocial = respuestaFormulario.getNombre();
        this.tipo = respuestaFormulario.getNombre();
        this.rubro = respuestaFormulario.getNombre();
        //this.direccion = respuestaFormulario.getNombre();
      }
    }
  } */

  /**
   * El colaborador usa puntos para comprar una oferta si tiene suficientes.
   *
   * @param oferta La oferta es lo que el colabrador desea comprar con puntos.
   */

  public Boolean puedeCanjear(Oferta oferta) {
    return reconocimiento.getPuntosPorColaborar() >= oferta.getPuntosNecesarios();
  }

  public void aumentarReconocimiento(Colaboracion colaboracion) {
    this.reconocimiento.sumarPuntos(colaboracion);
  }

}
