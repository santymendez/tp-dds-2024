package models.entities.personas.colaborador;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.direccion.Direccion;
import models.entities.formulario.RespuestaFormulario;
import models.entities.heladera.Heladera;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.personas.colaborador.canje.Oferta;
import models.entities.personas.colaborador.reconocimiento.Reconocimiento;
import models.entities.personas.colaborador.suscripcion.InterfazSuscripcion;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
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

  private List<Colaboracion> colaboraciones;

  private AdapterServicioRecomendacion adapterServicioRecomendacion;

  private List<TarjetaColaborador> tarjetas;
  private List<InterfazSuscripcion> suscripciones;

  /**
   * Instancia un Colaborador.
   */

  public Colaborador() {
    this.reconocimiento = new Reconocimiento();
    this.colaboraciones = new ArrayList<>();
    this.suscripciones = new ArrayList<>();
    this.tarjetas = new ArrayList<>();
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

  public void agregarSolicitudApertura(Heladera heladera) {
    heladera.getModAperturas().getTarjetasHabilitadas().add(this.ultimaTarjeta());
  }

  public TarjetaColaborador ultimaTarjeta() {
    return this.tarjetas.get(this.tarjetas.size() - 1);
  }

  public void agregarTarjeta(TarjetaColaborador tarjeta) {
    this.tarjetas.add(tarjeta);
  }

  //Permite registrar las suscripciones a heladeras
  public void agregarSuscripcion(InterfazSuscripcion suscripcion) {
    this.suscripciones.add(suscripcion);
  }

}
