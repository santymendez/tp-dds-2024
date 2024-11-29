package dtos;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import models.entities.formulario.Pregunta;

/**
 * Representa una pregunta de los formularios.
 */

@Data
@Builder
public class PreguntaOutputDto {
  private String id;
  private String pregunta;
  private List<OpcionOutputDto> opciones;
  private String tipoPregunta;
  private Boolean esObligatoria;

  /**
   * Convierte una pregunta en un PreguntaOutputDto.
   *
   * @param pregunta Pregunta a convertir.
   * @return PreguntaOutputDto convertida.
   */

  public static PreguntaOutputDto fromPregunta(Pregunta pregunta) {
    return PreguntaOutputDto.builder()
      .id(pregunta.getId().toString())
      .pregunta(pregunta.getPregunta())
      .opciones(pregunta.getOpciones().stream().map(OpcionOutputDto::fromOpcion)
          .toList())
      .tipoPregunta(pregunta.getTipoPregunta().name())
      .esObligatoria(pregunta.getObligatoria())
      .build();
  }
}
