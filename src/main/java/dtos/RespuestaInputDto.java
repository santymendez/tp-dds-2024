package dtos;

import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para recibir las respuestas de un formulario.
 */

@Setter
@Getter
public class RespuestaInputDto {
  private Long preguntaId;
  private String respuesta;
  private List<Long> idOpciones;

  /**
   * Crea una lista de RespuestaInputDto a partir de un contexto.
   *
   * @param ctx contexto de la peticion.
   * @return lista de RespuestaInputDto.
   */

  public static List<RespuestaInputDto> listFromContext(Context ctx) {
    List<RespuestaInputDto> dtos = new ArrayList<>();
    Map<String, List<String>> params = ctx.formParamMap();
    params.forEach((key, value) -> {
      if (key.startsWith("campo_")) {
        RespuestaInputDto dto = new RespuestaInputDto();
        dto.setPreguntaId(Long.valueOf(key.split("_")[1]));
        if (value.size() > 1) {
          dto.setIdOpciones(value.stream().map(Long::valueOf).toList()); // si es un multiple choice
        } else {
          dto.setRespuesta(value.get(0));
          try {
            dto.setIdOpciones(value.stream().map(Long::valueOf).toList());
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        dtos.add(dto);
      }
    });
    return dtos;

  }
}