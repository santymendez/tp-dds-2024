package models.entities.heladera.modulos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import models.entities.heladera.estados.Estado;
import models.entities.heladera.estados.TipoEstado;

/**
 * Representa al modulo de la heladera que contiene su
 * estado y se encarga de sus modificcaciones.
 */

@Getter
public class ModuloDeEstados {
  private Estado estadoActual;
  private List<Estado> estadosHeladera;

  /**
   * Metodo constructor del modulo de estados.
   * Suponemos el estado inicial como activa.
   */

  public ModuloDeEstados() {
    this.estadosHeladera = new ArrayList<>();
    this.estadoActual = new Estado(TipoEstado.ACTIVA);
    this.estadosHeladera.add(estadoActual);
  }

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
}
