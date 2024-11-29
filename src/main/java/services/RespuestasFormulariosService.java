package services;

import dtos.RespuestaFormularioInputDto;
import models.entities.formulario.Formulario;
import models.entities.formulario.RespuestaFormulario;
import models.repositories.imp.GenericRepository;

/**
 * Servicio para crear las respuestas de los formularios.
 */

public class RespuestasFormulariosService {

  private final GenericRepository genericRepository;

  public RespuestasFormulariosService(GenericRepository genericRepository) {
    this.genericRepository = genericRepository;
  }

  /**
   * Crea una respuesta de formulario a partir de un dto.
   *
   * @param respuestaFormularioInputDto dto con los datos de la respuesta.
   * @return respuesta de formulario.
   */

  public RespuestaFormulario crearCon(RespuestaFormularioInputDto respuestaFormularioInputDto) {
    RespuestaFormulario respuestaFormulario = new RespuestaFormulario();
    respuestaFormulario.setFormulario(
        this.genericRepository.buscarPorId(respuestaFormularioInputDto.getFormularioId(),
            Formulario.class).get());

    return respuestaFormulario;
  }
}
