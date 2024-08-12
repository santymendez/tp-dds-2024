package models.entities.heladera.modulos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.vianda.Vianda;

/**
 * Clase que representa al modulo de almacenamiento que controla todas
 * las funciones relacionadas con el espacio disponible en la heladera.
 */

@Setter
@Getter
public class ModuloDeAlmacenamiento {
  private Integer capacidadMaximaViandas;
  private List<Vianda> viandas;

  /**
   * Metodo constructor del modulo de almacenamiento.
   *
   * @param capacidad capacidad maxima de almacenamiento.
   */

  public ModuloDeAlmacenamiento(Integer capacidad) {
    this.capacidadMaximaViandas = capacidad;
    this.viandas = new ArrayList<>();
  }

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
    heladera.getModReportes().getReporteHeladera().viandaColocada();
    heladera.getModSuscripciones().intentarNotificarSuscriptores();
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

    heladera.getModReportes().getReporteHeladera().viandaRetirada();
    heladera.getModSuscripciones().intentarNotificarSuscriptores();
  }

  /**
   * Metodos Auxiliares.
   */

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
}
