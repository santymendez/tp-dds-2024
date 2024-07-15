package models.entities.heladera.estados;

/**
 * Estados posibles de la heladera.
 */

public enum TipoEstado {
  ACTIVA,
  INACTIVA_FUNCIONAL,
  INACTIVA_TEMPERATURA,
  INACTIVA_FRAUDE,
  INACTIVA_FALLA_CONEXION,
  INACTIVA_FALLA_TECNICA
}
