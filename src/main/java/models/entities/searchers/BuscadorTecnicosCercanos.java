package models.entities.searchers;

import java.util.List;
import models.entities.heladera.Heladera;
import models.entities.personas.tecnico.Tecnico;
import models.factories.FactorySender;
import models.repositories.imp.TecnicosRepository;
import utils.sender.Destinatario;
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
          FactorySender.obtenerInstanciaSegun(tecnico.getContacto().getTipoContacto());

      String asunto = "La heladera " + heladera.getNombre() + " ha sufrido una falla tecnica";
      String cuerpo = "Podes acercarte a revisarla, se encuentra en: "
          + heladera.getDireccion().getUbicacion();

      Mensaje mensaje = new Mensaje(asunto, cuerpo);

      Destinatario destinatario = new Destinatario();
      destinatario.agregarMedioDeContacto(tecnico.getContacto().getTipoContacto(),
          tecnico.getContacto().getContacto());

      sender.enviar(mensaje, destinatario);
    });
  }

}
