package models.entities.heladera;

import java.time.LocalDate;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import models.entities.direccion.Direccion;
import models.entities.heladera.estados.Estado;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.modulos.ModuloDeAlmacenamiento;
import models.entities.heladera.modulos.ModuloDeEstados;
import models.entities.heladera.modulos.ModuloDeIncidentes;
import models.entities.heladera.modulos.ModuloDeReportes;
import models.entities.heladera.modulos.ModuloDeSuscripciones;
import models.entities.heladera.modulos.ModuloDeTecnicos;
import models.entities.heladera.modulos.aperturas.Limitador;
import models.entities.heladera.modulos.aperturas.ModuloDeAperturas;
import models.entities.heladera.modulos.aperturas.UnidadTiempo;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;

/**
 * Representa una heladera con una dirección, nombre, capacidad máxima de viandas, lista
 * de viandas y fecha de creación.
 */

@Getter
@Setter
public class Heladera {
  private Direccion direccion;
  private String nombre;
  private LocalDate fechaDeCreacion;

  private Modelo modelo;
  private SensorMovimiento sensorMovimiento;

  private Boolean estaAbierta;

  private ModuloDeAlmacenamiento modAlmacenamiento;
  private ModuloDeSuscripciones modSuscripciones;
  private ModuloDeReportes modReportes;
  private ModuloDeIncidentes modIncidentes;
  private ModuloDeEstados modEstados;
  private ModuloDeTecnicos modTecnicos;
  private ModuloDeAperturas modAperturas;

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
    this.fechaDeCreacion = fechaDeCreacion;
    this.modelo = modelo;
    this.sensorMovimiento = sensorMovimiento;
    this.estaAbierta = false;

    this.modReportes = new ModuloDeReportes(this);
    this.modSuscripciones = new ModuloDeSuscripciones();
    this.modAlmacenamiento =
        new ModuloDeAlmacenamiento(capacidadMaximaViandas, modReportes, modSuscripciones);
    this.modEstados = new ModuloDeEstados();
    this.modTecnicos = new ModuloDeTecnicos();
    this.modIncidentes =
        new ModuloDeIncidentes(modReportes, modSuscripciones, modEstados, modTecnicos);
    this.modAperturas = new ModuloDeAperturas(this, new Limitador(UnidadTiempo.HORAS, 3));
  }

  //==================================== Calcular meses ========================================

  /**
   * Metodo para calcular los meses que una heladera estuvo activa hasta el momento
   * en que se pide calcular.
   */

  public Integer calcularMesesActiva() {
    return modEstados.getEstadosHeladera()
        .stream()
        .filter(estado -> estado.getEstado() == TipoEstado.ACTIVA)
        .mapToInt(Estado::calcularMeses)
        .sum();
  }

  //==================================== Métodos auxiliares ========================================

  /**
   * Busca la ultima falla de la heladera.
   *
   * @return La ultima falla tecnica.
   */
  //TODO y esto para que esta?
  public Incidente ultimaFallaTecnica() {
    Optional<Incidente> ultimoIncidente = this.modIncidentes.getIncidentes().stream()
        .filter(incidente -> incidente.getTipo() == TipoIncidente.FALLA_TECNICA)
        .findFirst();

    return ultimoIncidente.orElse(null);
  }
}
