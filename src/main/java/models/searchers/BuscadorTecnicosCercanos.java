package models.searchers;

import config.SenderLocator;
import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.imp.TecnicosRepository;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;

/**
 * Busca tecnicos cercanos a una heladera en particular.
 */

public class BuscadorTecnicosCercanos {

  private final TecnicosRepository tecnicosRepository;

  public BuscadorTecnicosCercanos(TecnicosRepository tecnicosRepository) {
    this.tecnicosRepository = tecnicosRepository;
  }

  /**
   * Busca tecnicos cercanos a una heladera.
   *
   * @param heladera Heladera tomada como parametro de busqueda.
   */

  public List<Tecnico> buscarTecnicosCercanosA(Heladera heladera) {
    String ciudad = heladera.getDireccion().getBarrio().getCiudad().getNombreCiudad();

    return this.tecnicosRepository.buscarPorCiudad(ciudad);
  }

  /**
   * Notifica a los técnicos que se encuentran en la lista.
   *
   * @param heladera La heladera a ser reparada.
   */

  public void notificarTecnicos(Heladera heladera) {
    List<Tecnico> tecnicos = this.buscarTecnicosCercanosA(heladera);

    tecnicos.parallelStream().forEach(tecnico -> {
      SenderInterface sender =
          SenderLocator.instanceOf(tecnico.getContacto().getTipoContacto());

      String asunto = "La heladera " + heladera.getNombre() + " ha sufrido una falla.";
      String cuerpo = "¿Podés acercarte a revisarla? Se encuentra en: ";

      if (heladera.getDireccion().getNombreUbicacion().isEmpty()) {
        cuerpo += "Latitud: " + heladera.getDireccion().getLatitud() + "\n"
            + "Longitud: " + heladera.getDireccion().getLongitud() + "\n";
      } else {
        cuerpo += heladera.getDireccion().getNombreUbicacion();
      }

      Mensaje mensaje = new Mensaje(asunto, cuerpo);

      String destinatario = tecnico.getContacto().getInfo();

      sender.enviar(mensaje, destinatario);
    });
  }

}
