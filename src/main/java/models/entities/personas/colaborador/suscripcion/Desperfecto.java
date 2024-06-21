package models.entities.personas.colaborador.suscripcion;

import java.io.IOException;
import java.util.List;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.searchers.BuscadorHeladerasFrecuentes;
import modules.recomendator.adapter.AdapterServicioRecomendacion;
import modules.recomendator.entities.ListadoDepuntos;
import modules.recomendator.entities.Punto;
import utils.sender.SenderInterface;

/**
 * Clase que representa la notificacion referida
 * a un desperfecto ocurrido en una heladera.
 */

@Setter
public class Desperfecto implements InterfazSuscripcion {
  private Colaborador colaborador;
  private Heladera heladera;
  private SenderInterface senderInterface;
  private BuscadorHeladerasFrecuentes buscadorHeladerasFrecuentes;
  private List<Heladera> heladerasSugeridas;
  
  /**
   * Constructor de la suscripcion en caso de que ocurran fallas.
   *
   * @param colaborador Suscriptor.
   * @param heladera Heladera a la que el suscriptor busca suscribirse.
   */

  public Desperfecto(Colaborador colaborador, Heladera heladera) {
    this.colaborador = colaborador;
    this.heladera = heladera;
    // TODO instanciar el sender
  }
  
  /**
   * Obtiene una lista de heladeras sugeridas.
   *
   * @param lat latitud.
   * @param lon longitud.
   * @param rad radio.
   * @return lista de heladeras.
   */

  // TODO hacer logica en base a patrón repository
  private List<Heladera> obtenerHeladerasSugeridas(int lat, int lon, int rad) throws IOException {
    AdapterServicioRecomendacion recomendador = AdapterServicioRecomendacion.getInstancia();
    ListadoDepuntos listado = recomendador.listadoDePuntos(lat, lon, rad);
    List<Punto> puntos = listado.puntos;

    // List<Heladera> heladeras = HeladeraRepository aca habria que filtrar de todas las
    // heladeras que existen las que tengan esa lat y lon
    // este metodo iria a un controller

    return null;
  }

  /**
   * Enviar notificacion al colaborador.
   */

  public void intentarNotificar() {
    if (!this.heladerasSugeridas.isEmpty()) {
      this.notificar();
    }
  }

  //TODO
  public void notificar() {}
}
