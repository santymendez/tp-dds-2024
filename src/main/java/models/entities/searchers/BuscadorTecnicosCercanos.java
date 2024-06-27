package models.entities.searchers;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.personas.InterfaceTecnicosRepository;
import models.repositories.personas.TecnicosRepository;
import utils.sender.Mensaje;
import utils.sender.SenderInterface;
import utils.sender.SenderLocator;

/**
 * Busca tecnicos cercanos a una heladera en particular.
 */

public class BuscadorTecnicosCercanos {
  private final InterfaceTecnicosRepository tecnicosRepository;

  public BuscadorTecnicosCercanos(InterfaceTecnicosRepository tecnicosRepository) {
    this.tecnicosRepository = tecnicosRepository;
  }

  /**
   * Busca tecnicos cercanos a una heladera.
   *
   * @param heladera Heladera tomada como parametro de busqueda.
   */

  public void buscarTecnicosCercanosA(Heladera heladera) {
    String nombreCiudad = heladera.getDireccion().getProvincia().getCiudad().getNombreCiudad();
    notificarTecnicos(tecnicosRepository.buscar(nombreCiudad), heladera);
  }

  /**
   * Notifica a los técnicos que se encuentran en la lista.
   *
   * @param tecnicos Lista de técnicos que se encuentran cercanos.
   * @param heladera La heladera a ser reparada.
   */

  public void notificarTecnicos(List<Tecnico> tecnicos, Heladera heladera) {
    tecnicos.parallelStream().forEach(tecnico -> {
      SenderInterface sender =
          SenderLocator.getService(tecnico.getContacto().getTipoContacto());

      String asunto = "La heladera " + heladera.getNombre() + " ha sufrido una falla tecnica";
      String cuerpo = "Podes acercarte a revisarla, se encuentra en: "
          + heladera.getDireccion().getUbicacion();

      Mensaje mensaje = new Mensaje(asunto, cuerpo);

      String destinatario = tecnico.getContacto().getInfo();

      sender.enviar(mensaje, destinatario);
    });
  }

}
