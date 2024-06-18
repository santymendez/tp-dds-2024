package models.entities.heladera;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.direccion.Direccion;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.sensores.SensorMovimiento;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.suscripcion.InterfazSuscripcion;
import models.entities.personas.tarjetas.colaborador.SolicitudApertura;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;
import models.entities.reporte.ReporteHeladera;

/**
 * Representa una heladera con una dirección, nombre, capacidad máxima de viandas, lista
 * de viandas y fecha de creación.
 */

@Getter
@Setter //(Modificacion de heladeras)
public class Heladera {
  private Direccion direccion;
  private String nombre;
  private Integer capacidadMaximaViandas;
  private List<Vianda> viandas;
  private LocalDate fechaDeCreacion;

  private Modelo modelo;
  private SensorMovimiento sensorMovimiento;

  private Estado estadoActual;
  private List<Estado> estadosHeladera;

  private List<TarjetaColaborador> tarjetasHabilitadas;
  private Float limiteDeTiempo;

  private Boolean estaAbierta;

  private ReporteHeladera reporteHeladera;

  private List<InterfazSuscripcion> suscripciones;

  /**
   * Se inicializa la heladera (Dar de alta).
   *
   * @param direccion              es la direccion actual de la heladera.
   * @param nombre                 es el nombre de la heladera.
   * @param capacidadMaximaViandas es la capacidad maxima de viandas.
   * @param sensorMovimiento       es el sensor de movimiento propio de la heladera.
   * @param modelo                 es el modelo de la heladera.
   * @param fechaDeCreacion        fecha en la que se colocó la heladera.
   */

  public Heladera(Direccion direccion, String nombre,
                  Integer capacidadMaximaViandas, LocalDate fechaDeCreacion,
                  Modelo modelo, SensorMovimiento sensorMovimiento) {
    this.direccion = direccion;
    this.nombre = nombre;
    this.capacidadMaximaViandas = capacidadMaximaViandas;
    this.viandas = new ArrayList<>();
    this.fechaDeCreacion = fechaDeCreacion;
    this.modelo = modelo;
    this.sensorMovimiento = sensorMovimiento;
    this.estadosHeladera = new ArrayList<>();
    this.estadoActual = new Estado(TipoEstado.ACTIVA);
    this.estadosHeladera.add(estadoActual);
    this.tarjetasHabilitadas = new ArrayList<>();
    this.limiteDeTiempo = 3.0f; //Su valor original es 3
    this.estaAbierta = false;
    this.reporteHeladera = new ReporteHeladera(this);
  }

  //================================== Movimiento de viandas ======================================

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
    reporteHeladera.viandaColocada();

    this.intentarNotificarSuscriptores();
  }

  /**
   * Se elimina una vianda de la heladera (sirve para la distribución).
   */

  public void removerVianda() {
    if (this.viandas.isEmpty()) {
      throw new RuntimeException("No hay viandas para retirar");
    }

    //Se retira la vianda
    viandas.remove(0);
    reporteHeladera.viandaRetirada();

    if (!this.suscripciones.isEmpty()) {
      this.intentarNotificarSuscriptores();
    }
  }

  //==================================== Calcular meses ========================================

  /**
   * Método para calcular los meses que una heladera estuvo activa hasta el momento
   * en que se pide calcular.
   */

  public Integer calcularMesesActiva() {
    return estadosHeladera
        .stream()
        .filter(estado -> estado.getEstado() == TipoEstado.ACTIVA)
        .mapToInt(Estado::calcularMeses)
        .sum();
  }

  //==================================== incidentes ========================================

  /**
   * Reporta un incidente.
   *
   * @param tipoAlerta representa el tipo de alerta.
   */

  public void reportarIncidente(TipoEstado tipoAlerta) {
    Incidente incidente = new Incidente(TipoIncidente.ALERTA, this);
    incidente.setTipoAlerta(tipoAlerta);
    this.reportarFalla();
    this.imprimirAlerta();
    this.intentarNotificarSuscriptores();
  }

  /**
   * Genera una alerta a partir del estado actual.
   */

  public void imprimirAlerta() {
    switch (estadoActual.getEstado()) {
      case INACTIVA_FRAUDE ->
          System.out.println("LA HELADERA ESTA SUFRIENDO FRAUDE");
      case INACTIVA_TEMPERATURA ->
          System.out.println("LA TEMPERATURA SALIO DEL RANGO DE TEMPERATURA RECOMENDADO");
      case INACTIVA_FALLA_CONEXION ->
          System.out.println("FALLO EN LA CONEXION CON EL SENSOR DE TEMPERATURA");
      default ->
          System.out.println("FALSA ALARMA, HELADERA ACTIVA");
    }
  }

  /**
   * Verifica si la heladera puede ser abierta con una tarjeta en particular.
   *
   * @param tarjeta Es la tarjeta con la que se desea abrir la heladera.
   */

  //================================= Tarjetas de colaborador =====================================

  public Boolean intentarAbrirCon(TarjetaColaborador tarjeta) {
    if (!tarjetasHabilitadas.contains(tarjeta)) {
      throw new RuntimeException("No posees los permisos necesarios para abrir la heladera.");
    }

    UsoTarjetaColaborador ultimoUso = this.hallarUltimoUso(tarjeta);

    if (!this.estaVigente(ultimoUso.getSolicitud())) {
      throw new RuntimeException("Tu solicitud ha expirado.");
    }

    ultimoUso.getApertura().setFechaApertura(LocalDateTime.now());
    return true;
  }

  //==================================== Suscripciones ========================================

  /**
   * Intentara notificar a sus suscriptores.
   */

  public void intentarNotificarSuscriptores() {
    if (!this.suscripciones.isEmpty()) {
      this.suscripciones.parallelStream().forEach(InterfazSuscripcion::intentarNotificar);
    }
  }

  public void agregarSuscripcion(InterfazSuscripcion suscripcion) {
    this.getSuscripciones().add(suscripcion);
  }

  public void eliminarSuscripcion(InterfazSuscripcion suscripcion) {
    this.getSuscripciones().remove(suscripcion);
  }

  //==================================== Métodos auxiliares ========================================

  public Boolean tieneViandas() {
    return !this.viandas.isEmpty();
  }

  public Boolean tieneEspacio() {
    return this.capacidadMaximaViandas > this.viandas.size();
  }

  /**
   * Setea la fecha final del estado anterior y crea el nuevo estado actual.
   *
   * @param estado Nuevo estado de la heladera.
   */

  public void modificarEstado(TipoEstado estado) {
    this.estadoActual.setFechaFinal(LocalDate.now());
    this.estadoActual = new Estado(estado);
    this.estadoActual.setFechaInicial(LocalDate.now());
    this.estadosHeladera.add(this.estadoActual);
  }

  /**
   * Obtiene el último uso de una tarjeta.
   *
   * @param tarjeta de la que quiere obtener el uso.
   * @return UsoTarjetaColaborador el último uso.
   */

  public UsoTarjetaColaborador hallarUltimoUso(TarjetaColaborador tarjeta) {
    List<UsoTarjetaColaborador> usosFiltradosPorHeladera =
        tarjeta.getUsos().stream().filter(uso -> uso.getHeladera() == this).toList();
    return usosFiltradosPorHeladera.get(usosFiltradosPorHeladera.size() - 1);
  }

  /**
   * Verifica si una solicitud está vigente.
   *
   * @param ultimaSolicitud Solicitud que se intenta verificar si se encuentra vigente.
   */

  public Boolean estaVigente(SolicitudApertura ultimaSolicitud) {
    Duration duration = Duration.between(ultimaSolicitud.getFechaSolicitud(), LocalDateTime.now());
    float horas = duration.toHours();
    return horas < limiteDeTiempo;
  }

  public void reportarFalla() {
    this.reporteHeladera.ocurrioUnaFalla();
  }

  public Integer consultarStock() {
    return this.viandas.size();
  }

  public Integer consultarEspacioSobrante() {
    return this.capacidadMaximaViandas - this.consultarStock();
  }

}
