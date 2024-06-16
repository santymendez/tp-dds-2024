package models.entities.personas.colaborador;

import java.awt.Image;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.direccion.Direccion;
import models.entities.formulario.RespuestaFormulario;
import models.entities.heladera.Heladera;
import models.entities.heladera.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.canje.Oferta;
import models.entities.personas.colaborador.reconocimiento.Reconocimiento;
import models.entities.personas.colaborador.suscripcion.Suscripcion;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;
import modules.recomendator.adapter.AdapterServicioRecomendacion;
import utils.security.Usuario;

/**
 * Representa a un colaborador en el sistema.
 */

@Getter
@Setter
public class Colaborador {
  private Usuario usuario;

  //Persona fisica
  private String nombre;
  private String apellido;
  private Documento documento;

  //Persona Juridica
  private String razonSocial;
  private String tipo;
  private String rubro;
  private Direccion direccion;

  //Ambos
  private Contacto contacto;
  private TipoColaborador tipoColaborador;
  private RespuestaFormulario respuestaFormulario;
  private Reconocimiento reconocimiento;

  private AdapterServicioRecomendacion adapterServicioRecomendacion;

  private TarjetaColaborador tarjeta;
  private Suscripcion suscripcion;

  public Colaborador() {
    this.reconocimiento = new Reconocimiento();
  }

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

  /**
   * Crea la solicitud para abrir una heladera.
   *
   * @param heladera Heladera que se busca abrir.
   */

  public void crearSolicitudAperturaDeHeladera(Heladera heladera) {
    UsoTarjetaColaborador nuevoUso = new UsoTarjetaColaborador(heladera);
    this.tarjeta.getUsos().add(nuevoUso);
    heladera.getTarjetasHabilitadas().add(this.tarjeta);
  }

  //TODO Ver que hacer con los incidentes


  /**
   * Reporta un incidente.
   *
   * @param heladera representa el colaborador que reporta la falla.
   * @param descripcion representa la descripcion opcional proporcionada por el colaborador.
   * @param imagen representa la posible imagen.
   */

  public void reportarIncidente(Heladera heladera, String descripcion, Image imagen) {
    Incidente incidente = new Incidente(TipoIncidente.FALLA_TECNICA, heladera);
    incidente.setColaborador(this);
    incidente.setDescripcion(descripcion);
    incidente.setImagen(imagen);
    heladera.modificarEstado(TipoEstado.INACTIVA_FALLA_TECNICA);
    heladera.imprimirAlerta();
    heladera.reportarFalla();
  }

  public void colocarViandas(Heladera heladera, Vianda ... vianda) {
    Integer cantViandas = (int) Arrays.stream(vianda).count();
    heladera.getReporteHeladera().colaboracionRealizada(this, cantViandas);
  }

}
