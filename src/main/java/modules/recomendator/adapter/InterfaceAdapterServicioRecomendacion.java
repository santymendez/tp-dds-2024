package modules.recomendator.adapter;

import java.io.IOException;
import modules.recomendator.entities.ListadoDepuntos;

/**
 * AAAA.
 */

public interface InterfaceAdapterServicioRecomendacion {
  static AdapterServicioRecomendacion getInstancia() {
    return null;
  }

  default ListadoDepuntos listadoDePuntos(int lat, int lon, int rad) throws IOException {
    return null;
  }
}
