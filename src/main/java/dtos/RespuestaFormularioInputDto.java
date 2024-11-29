package dtos;

import io.javalin.http.Context;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

/**
 * Representa al dto de un formulario.
 */

@Builder
@Getter
public class RespuestaFormularioInputDto {
  private Long formularioId;
  private List<RespuestaInputDto> respuestaInputDto;

  /**
   * Crea un RespuestaFormularioInputDto a partir de un contexto.
   *
   * @param context contexto de la peticion.
   * @return RespuestaFormularioInputDto.
   */

  public static RespuestaFormularioInputDto fromContext(Context context) {
    return RespuestaFormularioInputDto.builder()
        .formularioId(Long.parseLong(Objects.requireNonNull(context.formParam("formularioId"))))
        .respuestaInputDto(RespuestaInputDto.listFromContext(context))
        .build();
  }
}
