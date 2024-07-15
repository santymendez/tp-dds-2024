package models.entities.heladera.modulos;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;

/**
 * Representa al modulo de la heladera encargado de manejar
 * las funciones relacionadas con los incidentes ocurridos.
 */

@Getter
@Setter
public class ModuloDeIncidentes {
  private final List<Incidente> incidentes;
  private ModuloDeReportes modReportes;
  private ModuloDeSuscripciones modSuscripciones;
  private ModuloDeEstados modEstados;
  private ModuloDeTecnicos modTecnicos;

  /**
   * Metodo constructor del modulo de incidentes de la heladera.
   *
   * @param modReportes modulo de reportes de la heladera.
   * @param modSuscripciones modulo de suscripciones de la heladera.
   * @param modEstados modulo de estados de la heladera.
   * @param modTecnicos modulo de tecnicos de la heladera.
   */

  public ModuloDeIncidentes(ModuloDeReportes modReportes,
                            ModuloDeSuscripciones modSuscripciones,
                            ModuloDeEstados modEstados,
                            ModuloDeTecnicos modTecnicos) {
    this.incidentes = new ArrayList<>();
    this.modReportes = modReportes;
    this.modSuscripciones = modSuscripciones;
    this.modEstados = modEstados;
    this.modTecnicos = modTecnicos;
  }

  /**
   * Reporta un incidente.
   *
   * @param tipoAlerta representa el tipo de alerta.
   */

  public void reportarIncidente(TipoEstado tipoAlerta, Heladera heladera) {
    Incidente incidente = new Incidente(TipoIncidente.ALERTA, heladera);
    incidente.setTipoAlerta(tipoAlerta);
    this.modReportes.reportarFalla();
    this.imprimirAlerta();
    this.modSuscripciones.intentarNotificarSuscriptores();
    this.incidentes.add(incidente);
  }

  /**
   * Metodo auxiliar con todas los metodos que ejecuta
   * la heladera cuandp se reporta una falla tecnica.
   */

  public void reportarFallaTecnica(Incidente incidente, Heladera heladera) {
    this.modEstados.modificarEstado(TipoEstado.INACTIVA_FALLA_TECNICA);
    this.imprimirAlerta();
    this.modReportes.reportarFalla();
    this.modSuscripciones.intentarNotificarSuscriptores();
    this.modTecnicos.notificarTecnicos(heladera);
    this.incidentes.add(incidente);
  }

  /**
   * Genera una alerta a partir del estado actual.
   */

  public void imprimirAlerta() {
    switch (modEstados.getEstadoActual().getEstado()) {
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
}
