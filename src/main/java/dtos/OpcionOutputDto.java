package dtos;

import lombok.Builder;
import lombok.Getter;
import models.entities.formulario.Opcion;

/**
 * Representa un dto de una opcion de las preguntas de formularios.
 */

@Getter
@Builder
public class OpcionOutputDto {
  private String id;
  private String opcion;

  /**
   * Convierte una opcion en un OpcionOutputDto.
   *
   * @param opcion Opcion a convertir.
   * @return OpcionOutputDto convertida.
   */

  public static OpcionOutputDto fromOpcion(Opcion opcion) {
    return OpcionOutputDto.builder()
      .id(opcion.getId().toString())
      .opcion(opcion.getOpcion())
      .build();
  }
}
