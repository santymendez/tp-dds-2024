package utils.medical.adapter;

import java.io.IOException;
import java.util.List;
import utils.medical.entities.Barrio;

/**
 * Interfaz Adapter para el Servicio medico.
 */
interface InterfaceAdapterServicioMedico {

  static AdapterServicioMedico getInstancia() {
    return null;
  }

  default List<Barrio> barrios() throws IOException {
    return null;
  }
}
