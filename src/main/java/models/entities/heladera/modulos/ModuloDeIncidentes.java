package models.entities.heladera.modulos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;

/**
 * Representa al modulo de la heladera encargado de manejar
 * las funciones relacionadas con los incidentes ocurridos.
 */

@Getter
@Setter
public class ModuloDeIncidentes {
  private final List<Incidente> incidentes;

  /**
   * Metodo constructor del modulo de incidentes de la heladera.
   */

  public ModuloDeIncidentes() {
    this.incidentes = new ArrayList<>();
  }

  /**
   * Reporta un incidente.
   *
   * @param incidente incidente a reportar.
   */

  public void reportarIncidente(Incidente incidente) {
    Heladera heladera = incidente.getHeladera();

    heladera.getModReportes().reportarFalla();
    heladera.getModSuscripciones().intentarNotificarSuscriptores();
    this.incidentes.add(incidente);
  }

  /**
   * Metodo auxiliar con todas los metodos que ejecuta
   * la heladera cuando se reporta una falla tecnica.
   */

  public void reportarFallaTecnica(Incidente incidente) {
    Heladera heladera = incidente.getHeladera();

    heladera.getModEstados().modificarEstado(TipoEstado.INACTIVA_FALLA_TECNICA);
    heladera.getModReportes().reportarFalla();
    heladera.getModSuscripciones().intentarNotificarSuscriptores();
    heladera.getModTecnicos().notificarTecnicos(incidente.getHeladera());
    this.incidentes.add(incidente);
  }
}
