package models.entities.heladera;

import java.time.LocalDate;
import java.util.Objects;
import models.entities.direccion.Direccion;

/**
 * Representa un sensor de movimiento.
 */

public class SensorMovimiento {

  /**
   * Método que recibe la heladera y te activa el sensor si la temperatura no está en el rango.
   */

  public void recibirMedicion(Heladera heladera, Float lat, Float lon) {
    if (!this.mismaLongitud(heladera.getDireccion(), lon)
        || !this.mismaLatitud(heladera.getDireccion(), lat)) {
      this.activarSensor(heladera);
    }
  }

  public void activarSensor(Heladera heladera) {
    this.modificarEstado(heladera, TipoEstado.INACTIVA_TEMPERATURA);
    heladera.generarAlerta();
  }

  public void desactivarSensor(Heladera heladera) {
    this.modificarEstado(heladera, TipoEstado.ACTIVA);
  }

  /**
   * Sets la fecha final del estado anterior y crea el nuevo estado actual.
   */

  public void modificarEstado(Heladera heladera, TipoEstado estado) {
    heladera.getEstadoActual().setFechaFinal(LocalDate.now());
    heladera.setEstadoActual(new Estado(estado));
    heladera.getEstadoActual().setFechaInicial(LocalDate.now());
    heladera.getEstadosHeladera().add(heladera.getEstadoActual());
  }

  public Boolean mismaLatitud(Direccion direccion, Float latitud) {
    return Objects.equals(direccion.getLatitud(), latitud);
  }

  public Boolean mismaLongitud(Direccion direccion, Float longitud) {
    return Objects.equals(direccion.getLongitud(), longitud);
  }
}