package models.entities.heladera;

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
import models.entities.personas.colaborador.suscripcion.TipoSuscripcion;
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

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "tarjetas_habilitadas")
  private List<TarjetaColaborador> tarjetasHabilitadas;

  @Transient
  private Limitador limitador;

  /**
   * Contructor sin argumentos de la clase heladera.
   */

  public Heladera() {
    this.estaAbierta = false;
    this.viandas = new ArrayList<>();

    this.estadosHeladera = new ArrayList<>();
    this.estadoActual = new Estado(TipoEstado.ACTIVA);
    this.estadoActual.setFechaInicial(LocalDate.now());
    this.estadosHeladera.add(estadoActual);

    this.suscripciones = new ArrayList<>();

    this.tarjetasHabilitadas = new ArrayList<>();
    this.limitador = new Limitador(UnidadTiempo.HORAS, 3L);
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

  /**
   * Metodo para calcular los meses que una heladera estuvo inactiva hasta el momento
   * en que se pide calcular.
   */

  public boolean estuvoActivaElUltimoMes() {
    return estadoActual.getEstado() == TipoEstado.ACTIVA
        && estadoActual.calcularMeses() >= 1;
  }

  //==================================== Almacenamiento ========================================

  /**
   * Se agrega una vianda a la heladera.
   *
   * @param vianda es la vianda que se busca agregar a la heladera.
   */

  public void agregarVianda(Vianda vianda) {
    if (!this.tieneEspacio()) {
      System.out.println("No hay espacio en la heladera.");
      return;
    }

    this.viandas.add(vianda);
    vianda.setEntregada(true);
    this.notificarCantidadViandas();
  }

  /**
   * Se elimina una vianda de la heladera (sirve para la distribución).
   */

  public List<Vianda> removerViandas(Integer cant) {
    if (cant > this.viandas.size()) {
      System.out.println("No hay suficiente espacio en la heladera.");
      return null;
    }

    List<Vianda> viandasRemovidas = new ArrayList<>();
    for (int i = 0; i < cant; i++) {
      viandasRemovidas.add(this.viandas.remove(viandas.size() - 1));
    }

    this.notificarCantidadViandas();

    return viandasRemovidas;
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

  public Boolean estaActiva() {
    return this.estadoActual.getEstado().equals(TipoEstado.ACTIVA);
  }

  //==================================== Aperturas ========================================

  /**
   * Permite intentar abrir la heladera con una tarjeta a partir de su codigo.
   *
   * @param tarjeta de la tarjeta con la que se intenta abrir.
   */

  public Boolean intentarAbrirCon(TarjetaColaborador tarjeta, LocalDateTime fechaHoraApertura) {
    if (tarjetasHabilitadas.contains(tarjeta)) {
      UsoTarjetaColaborador ultimoUsoVigente = tarjeta.ultimoUsoVigenteEn(this, fechaHoraApertura);

      if (ultimoUsoVigente != null) {
        ultimoUsoVigente.getApertura().setFechaApertura(fechaHoraApertura);
        return true;
      }
    }
    return false;
  }

  //==================================== Suscripciones ========================================

  /**
   * Intentara notificar a sus suscriptores.
   * Esta logica va en un controller.
   */

  public void intentarNotificarSuscriptores(TipoSuscripcion tipoSuscripcion) {
    if (!this.suscripciones.isEmpty()) {
      suscripciones.stream()
          .filter(s -> s.getTipo().equals(tipoSuscripcion))
          .toList()
          .parallelStream()
          .forEach(Suscripcion::intentarNotificar);
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

  public Boolean hayEspacioPara(Integer cantidad) {
    return this.consultarEspacioSobrante() >= cantidad;
  }

  private void notificarCantidadViandas() {
    this.intentarNotificarSuscriptores(TipoSuscripcion.QUEDAN_N_VIANDAS);
    this.intentarNotificarSuscriptores(TipoSuscripcion.FALTAN_N_VIANDAS);
  }

  @PrePersist
  protected void onInsert() {
    if (this.fechaDeCreacion == null) {
      this.fechaDeCreacion = LocalDate.now();
    }
  }

}