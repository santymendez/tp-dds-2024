package models.entities.personas.colaborador;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;
import models.entities.colaboracion.Colaboracion;
import models.entities.direccion.Direccion;
import models.entities.formulario.RespuestaFormulario;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.canje.Oferta;
import models.entities.personas.colaborador.reconocimiento.Reconocimiento;
import models.entities.personas.colaborador.suscripcion.InterfazSuscripcion;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import utils.recomendator.adapter.AdapterServicioRecomendacion;
import utils.security.Usuario;


/**
 * Representa a un colaborador en el sistema.
 */

@Getter
@Setter
@Entity
@Table(name = "colaboradores")
public class Colaborador extends Persistente {

  @Transient
  private Usuario usuario;

  //Persona fisica
  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Embedded
  private Documento documento;

  //Persona Juridica
  @Column(name = "razonSocial")
  private String razonSocial;

  @Column(name = "tipo")
  private String tipo;

  @Column(name = "rubro")
  private String rubro;

  @OneToOne
  @JoinColumn(name = "direccion_id", referencedColumnName = "id")
  private Direccion direccion;

  //Ambos
  @Embedded
  private Contacto contacto;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoColaborador")
  private TipoColaborador tipoColaborador;

  @Embedded
  private Reconocimiento reconocimiento;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "colaboracion_id", referencedColumnName = "id")
  private List<Colaboracion> colaboraciones;

  @Transient
  private AdapterServicioRecomendacion adapterServicioRecomendacion;

  @Transient //TODO cuando veamos persistencia de interfaces
  private List<InterfazSuscripcion> suscripciones;

  /**
   * Instancia un Colaborador.
   */

  public Colaborador() {
    this.reconocimiento = new Reconocimiento();
    this.colaboraciones = new ArrayList<>();
    this.suscripciones = new ArrayList<>();
  }

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

  public void agregarSolicitudApertura(Heladera heladera, TarjetaColaborador tarjeta) {
    heladera.getTarjetasHabilitadas().add(tarjeta);
  }

  public void agregarSuscripcion(InterfazSuscripcion suscripcion) {
    this.suscripciones.add(suscripcion);
  }

}
