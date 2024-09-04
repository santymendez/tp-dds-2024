package models.entities.heladera;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;
import models.entities.direccion.Direccion;
import models.entities.heladera.estados.Estado;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.limitador.Limitador;
import models.entities.heladera.limitador.UnidadTiempo;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.suscripcion.Suscripcion;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;

/**
 * Representa una heladera con una dirección, nombre, capacidad máxima de viandas, lista
 * de viandas y fecha de creación.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "heladeras")
public class Heladera extends Persistente {
  @OneToOne
  @JoinColumn(name = "direccion_id", referencedColumnName = "id")
  private Direccion direccion;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "fechaCreacion", columnDefinition = "DATE", nullable = false)
  private LocalDate fechaDeCreacion;

  @Column(name = "estaAbierta")
  private Boolean estaAbierta;

  @Embedded
  private Modelo modelo;

  @Column(name = "capacidadMaximaViandas")
  private Integer capacidadMaximaViandas;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "vianda_id", referencedColumnName = "id")
  //TODO orderBy de vianda segun que criterio?
  private List<Vianda> viandas;

  //TODO
  @Transient
  private List<Suscripcion> suscripciones;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "estado_id", referencedColumnName = "id", nullable = false)
  private Estado estadoActual;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "estadosPrevios_id", referencedColumnName = "id")
  private List<Estado> estadosHeladera;

  @ManyToMany
  @JoinTable(name = "tarjetas_habilitadas")
  private List<TarjetaColaborador> tarjetasHabilitadas;

  @Transient
  private Limitador limitador;

  /**
   * Se inicializa la heladera (Dar de alta).
   *
   * @param direccion              es la direccion actual de la heladera.
   * @param nombre                 es el nombre de la heladera.
   * @param capacidadMaximaViandas es la capacidad maxima de viandas.
   * @param modelo                 es el modelo de la heladera.
   * @param fechaDeCreacion        fecha en la que se colocó la heladera.
   */

  public Heladera(Direccion direccion, String nombre,
                  Integer capacidadMaximaViandas, LocalDate fechaDeCreacion,
                  Modelo modelo) {
    this.direccion = direccion;
    this.nombre = nombre;
    this.fechaDeCreacion = fechaDeCreacion;
    this.modelo = modelo;
    this.estaAbierta = false;
    this.capacidadMaximaViandas = capacidadMaximaViandas;
    this.viandas = new ArrayList<>();

    this.estadosHeladera = new ArrayList<>();
    this.estadoActual = new Estado(TipoEstado.ACTIVA);
    this.estadosHeladera.add(estadoActual);

    this.suscripciones = new ArrayList<>();

    this.tarjetasHabilitadas = new ArrayList<>();
    this.limitador = new Limitador(UnidadTiempo.HORAS, 3);
  }

  //==================================== Calcular meses ========================================

  /**
   * Metodo para calcular los meses que una heladera estuvo activa hasta el momento
   * en que se pide calcular.
   */

  public Integer calcularMesesActiva() {
    return this.estadosHeladera
        .stream()
        .filter(estado -> estado.getEstado() == TipoEstado.ACTIVA)
        .mapToInt(Estado::calcularMeses)
        .sum();
  }

  //==================================== Almacenamiento ========================================

  /**
   * Se agrega una vianda a la heladera.
   *
   * @param vianda es la vianda que se busca agregar a la heladera.
   */

  public void agregarVianda(Vianda vianda) {
    if (!this.tieneEspacio()) {
      throw new RuntimeException("No hay mas espacio en la heladera");
    }

    this.viandas.add(vianda);
    vianda.setEntregada(true);

    Heladera heladera = vianda.getHeladera();
    //heladera.getModReportes().getReporteHeladera().viandaColocada(); Se hace en el controller
    heladera.intentarNotificarSuscriptores(); //Deberia ir a controller
  }

  /**
   * Se elimina una vianda de la heladera (sirve para la distribución).
   */

  public void removerVianda() {
    if (this.viandas.isEmpty()) {
      throw new RuntimeException("No hay viandas para retirar");
    }

    Heladera heladera = this.viandas.get(0).getHeladera();

    //Se retira la vianda
    viandas.remove(0);

    //heladera.getModReportes().getReporteHeladera().viandaRetirada(); en el controller
    heladera.intentarNotificarSuscriptores(); //Deberia ir a controller
  }

  //==================================== Estados ========================================


  /**
   * Setea la fecha final del estado anterior y crea el nuevo estado actual,
   * agregando el anterior a la lista de estados de la heladera.
   *
   * @param estado Nuevo estado de la heladera.
   */

  public void modificarEstado(TipoEstado estado) {
    this.estadoActual.setFechaFinal(LocalDate.now());
    this.estadoActual = new Estado(estado);
    this.estadoActual.setFechaInicial(LocalDate.now());
    this.estadosHeladera.add(this.estadoActual);
  }

  //==================================== Aperturas ========================================

  /**
   * Permite intentar abrir la heladera con una tarjeta a partir de su codigo.
   *
   * @param tarjeta de la tarjeta con la que se intenta abrir.
   */

  public Boolean intentarAbrirCon(TarjetaColaborador tarjeta) {
    if (!tarjetasHabilitadas.contains(tarjeta)) {
      throw new RuntimeException("No posees los permisos necesarios para abrir la heladera.");
    }

    UsoTarjetaColaborador ultimoUso = this.ultimoUsoDe(tarjeta);

    if (!this.estaVigente(ultimoUso.getApertura().getFechaSolicitud())) {
      throw new RuntimeException("Tu solicitud ha expirado.");
    }

    ultimoUso.getApertura().setFechaApertura(LocalDateTime.now());
    return true;
  }

  /**
   * Obtiene el último uso de una tarjeta.
   *
   * @param tarjeta de la que quiere obtener el uso.
   * @return UsoTarjetaColaborador el último uso.
   */

  public UsoTarjetaColaborador ultimoUsoDe(TarjetaColaborador tarjeta) {
    List<UsoTarjetaColaborador> usosFiltradosPorHeladera =
        tarjeta.getUsos().stream().filter(uso -> uso.getHeladera() == this).toList();

    if (usosFiltradosPorHeladera.isEmpty()) {
      throw new RuntimeException("No hay tarjetas en la lista, o no está inicializada");
    }
    return usosFiltradosPorHeladera.get(usosFiltradosPorHeladera.size() - 1);
  }

  /**
   * Verifica si una solicitud está vigente.
   *
   * @param ultimaSolicitud Solicitud que se intenta verificar si se encuentra vigente.
   */

  public Boolean estaVigente(LocalDateTime ultimaSolicitud) {
    Duration duration = Duration.between(ultimaSolicitud, LocalDateTime.now());
    return this.limitador.menorAlLimite(duration);
  }

  //==================================== Suscripciones ========================================

  /**
   * Intentara notificar a sus suscriptores.
   * Esta logica va en un controller.
   */

  public void intentarNotificarSuscriptores() {
    if (!this.suscripciones.isEmpty()) {
      this.suscripciones.parallelStream().forEach(Suscripcion::intentarNotificar);
    }
  }

  public void agregarSuscripcion(Suscripcion suscripcion) {
    this.suscripciones.add(suscripcion);
    suscripcion.setHeladera(this);
  }

  public void eliminarSuscripcion(Suscripcion suscripcion) {
    this.suscripciones.remove(suscripcion);
    suscripcion.setHeladera(null);
  }

  //==================================== Métodos auxiliares ========================================

  public Boolean tieneViandas() {
    return !this.viandas.isEmpty();
  }

  public Boolean tieneEspacio() {
    return this.capacidadMaximaViandas > this.viandas.size();
  }

  public Integer consultarStock() {
    return this.viandas.size();
  }

  public Integer consultarEspacioSobrante() {
    return this.capacidadMaximaViandas - this.consultarStock();
  }

  //  /**
  //   * Metodo que junta toda la logica de la heladera ante la ocurrencia de una falla.
  //   * Las alertas, por deffault, solo usan esta logica.
  //   */

  //  public void ocurreFalla() {
  //    this.reportarFalla();
  //    this.modSuscripciones.intentarNotificarSuscriptores();
  //  }

  //  /**
  //   * Metodo que junta toda la logica de la heladera ante la ocurrencia de una falla tecnica.
  //   */

  //  public void ocurreFallaTecnica() {
  //    this.modEstados.modificarEstado(TipoEstado.INACTIVA_FALLA_TECNICA);
  //    this.ocurreFalla();
  //   //this.modTecnicos.notificarTecnicos(this); WAIT FOR THE CONTROLLER !
  //  }
  //
  //  public void reportarFalla() {
  //    this.reporte.ocurrioUnaFalla();
  //  }

}