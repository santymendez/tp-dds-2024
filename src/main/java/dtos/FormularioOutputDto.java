package dtos;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import models.entities.formulario.Formulario;

/**
 * Representa al dto de un formulario.
 */

@Getter
@Builder
public class FormularioOutputDto {
  String id;
  String formulario;
  List<PreguntaOutputDto> preguntas;

  /**
   * Convierte un formulario en un FormularioOutputDto.
   *
   * @param formulario Formulario a convertir.
   * @return FormularioOutputDto convertido.
   */

  public static FormularioOutputDto fromFormulario(Formulario formulario) {
    return FormularioOutputDto.builder()
        .id(formulario.getId().toString())
        .formulario(formulario.getNombre())
        .preguntas(formulario.getPreguntas().stream().map(PreguntaOutputDto::fromPregunta).toList())
        .build();
  }
}
