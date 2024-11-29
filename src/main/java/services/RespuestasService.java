package services;

import dtos.RespuestaInputDto;
import java.util.ArrayList;
import java.util.List;
import models.entities.formulario.Opcion;
import models.entities.formulario.Pregunta;
import models.entities.formulario.Respuesta;
import models.entities.formulario.TipoPregunta;
import models.repositories.imp.GenericRepository;

/**
 * Servicio para crear las respuestas.
 */

public class RespuestasService {

  private final GenericRepository genericRepository;

  public RespuestasService(GenericRepository genericRepository) {
    this.genericRepository = genericRepository;
  }

  /**
   * Crea una lista de respuestas a partir de una lista de dtos.
   *
   * @param respuestasInputDto lista de dtos con los datos de las respuestas.
   * @return lista de respuestas.
   */

  public List<Respuesta> crearCon(List<RespuestaInputDto> respuestasInputDto) {
    List<Respuesta> respuestas = new ArrayList<>();
    respuestasInputDto.forEach(respuestaInputDto -> {
      Respuesta respuesta = new Respuesta();

      respuesta.setPregunta(this.genericRepository
          .buscarPorId(respuestaInputDto.getPreguntaId(), Pregunta.class).get());

      respuesta.setRespuesta(respuestaInputDto.getRespuesta());

      respuesta.setOpciones(new ArrayList<>());

      if (respuesta.getPregunta().getTipoPregunta().equals(TipoPregunta.MULTIPLE_CHOICE)
          || respuesta.getPregunta().getTipoPregunta().equals(TipoPregunta.SINGLE_CHOICE)) {
        respuestaInputDto.getIdOpciones().forEach(idOpcion ->
            respuesta.agregarOpcion(
                this.genericRepository.buscarPorId(idOpcion, Opcion.class).get()
            ));
      }

      respuestas.add(respuesta);
    });

    return respuestas;
  }
}
