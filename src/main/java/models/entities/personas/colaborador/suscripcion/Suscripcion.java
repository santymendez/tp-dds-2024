package models.entities.personas.colaborador.suscripcion;

import models.entities.heladera.Heladera;
import modules.sender.SenderInterface;

/**
 * Clase que representa una suscripcion a una heladera.
 */

public class Suscripcion {
  private Heladera heladera;
  private QuedanViandas quedanViandas;
  private FaltanViandas faltanViandas;
  private Desperfecto desperfecto;
  private SenderInterface senderInterface;
}
