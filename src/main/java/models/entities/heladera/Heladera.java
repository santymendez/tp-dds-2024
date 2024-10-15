package models.entities.heladera;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
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
@Entity
@Table(name = "heladeras")
public class Heladera extends Persistente {
  @OneToOne
  @JoinColumn(name = "direccion_id", referencedColumnName = "id", nullable = false)
  private Direccion direccion;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "fechaCreacion", columnDefinition = "DATE")
  private LocalDate fechaDeCreacion;

  @Column(name = "estaAbierta")
  private Boolean estaAbierta;

  @ManyToOne
  @JoinColumn(name = "modelo_id", referencedColumnName = "id", nullable = false)
  private Modelo modelo;

  @OneToMany(mappedBy = "heladera",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private List<Vianda> viandas;

  @OneToMany(mappedBy = "heladera",
          cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private List<Suscripcion> suscripciones;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "estado_id", referencedColumnName = "id", nullable = false)
  private Estado estadoActual;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
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
   * @param modelo                 es el modelo de la heladera.
   * @param fechaDeCreacion        fecha en la que se colocó la heladera.
   */

  public Heladera(Direccion direccion, String nombre,
                  LocalDate fechaDeCreacion,
                  Modelo modelo) {
    this.direccion = direccion;
    this.nombre = nombre;
    this.fechaDeCreacion = fechaDeCreacion;
    this.modelo = modelo;
    this.estaAbierta = false;
    this.viandas = new ArrayList<>();

    this.estadosHeladera = new ArrayList<>();
    this.estadoActual = new Estado(TipoEstado.ACTIVA);
    this.estadosHeladera.add(estadoActual);

    this.suscripciones = new ArrayList<>();

    this.tarjetasHabilitadas = new ArrayList<>();
    this.limitador = new Limitador(UnidadTiempo.HORAS, 3);
  }

  /**
   * Contructor sin argumentos de la clase heladera.
   */

  public Heladera() {
    this.estaAbierta = false;
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

  public void removerVianda(Vianda vianda) {
    if (this.viandas.isEmpty()) {
      throw new RuntimeException("No hay viandas para retirar");
    }

    this.viandas.remove(vianda);

    //heladera.getModReportes().getReporteHeladera().viandaRetirada(); en el controller
    this.intentarNotificarSuscriptores(); //Deberia ir a controller
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
  }

  public void habilitarTarjeta(TarjetaColaborador tarjeta) {
    this.tarjetasHabilitadas.add(tarjeta);
  }

  //==================================== Métodos auxiliares ========================================

  public Boolean tieneViandas() {
    return !this.viandas.isEmpty();
  }

  public Boolean tieneEspacio() {
    return this.modelo.getCapacidadMaximaViandas() > this.viandas.size();
  }

  public Integer consultarStock() {
    return this.viandas.size();
  }

  public Integer consultarEspacioSobrante() {
    return this.modelo.getCapacidadMaximaViandas() - this.consultarStock();
  }

  @PrePersist
  protected void onInsert() {
    if (this.fechaDeCreacion == null) {
      this.fechaDeCreacion = LocalDate.now();
    }
  }

}