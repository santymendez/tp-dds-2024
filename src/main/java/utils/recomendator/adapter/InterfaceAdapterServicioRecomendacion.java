package utils.recomendator.adapter;

import java.io.IOException;
import utils.recomendator.entities.ListadoPuntos;

/**
 * Interfaz Adapter para el Servicio de Recomendacion de Puntos.
 */
interface InterfaceAdapterServicioRecomendacion {

  static AdapterServicioRecomendacion getInstancia() {
    return null;
  }

  default ListadoPuntos puntos(String lat, String lon, String rad) throws IOException {
    return null;
  }
}
