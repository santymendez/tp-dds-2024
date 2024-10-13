package utils.medical;

import java.io.IOException;
import java.util.List;
import utils.medical.adapter.AdapterServicioMedico;
import utils.medical.entities.Barrio;

/**
 * Main para probar que funciona la integracion.
 */

public class MainExample {

  /**
   *  Metodo para probar si puedo traerme los datos de prueba de la API externa.
   *
   * @param args argumentos.
   * @throws IOException si ocurre algun error.
   */

  public static void main(String[] args) throws IOException {
    AdapterServicioMedico service = AdapterServicioMedico.getInstancia();

    List<Barrio> lstBarrio = service.barrios();

    lstBarrio.forEach(Barrio::showData);
  }
}
