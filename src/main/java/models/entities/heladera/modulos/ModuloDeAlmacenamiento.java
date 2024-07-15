package models.entities.heladera.modulos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
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
  private ModuloDeSuscripciones modSuscripciones;
  private ModuloDeReportes modReportes;

  /**
   * Metodo constructor del modulo de almacenamiento.
   *
   * @param capacidad capacidad maxima de almacenamiento.
   * @param modReportes modulo de reportes de la heladera.
   * @param modSuscripciones modulo de suscripciones de la heladera.
   */

  public ModuloDeAlmacenamiento(Integer capacidad, ModuloDeReportes modReportes,
                                ModuloDeSuscripciones modSuscripciones) {
    this.capacidadMaximaViandas = capacidad;
    this.viandas = new ArrayList<>();
    this.modReportes = modReportes;
    this.modSuscripciones = modSuscripciones;
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
    this.modReportes.getReporteHeladera().viandaColocada();

    modSuscripciones.intentarNotificarSuscriptores();
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
    this.modReportes.getReporteHeladera().viandaRetirada();

    if (!modSuscripciones.getSuscripciones().isEmpty()) {
      modSuscripciones.intentarNotificarSuscriptores();
    }
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
