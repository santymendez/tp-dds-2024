package dtos;

import io.javalin.http.Context;
import java.util.Objects;
import lombok.Data;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.contacto.TipoContacto;

/**
 * Representa el Input de un Colaborador.
 */

@Data
public class ColaboradorInputDto {
  //Persona fisica
  private String nombre;
  private String apellido;
  private String tipoDocumento;
  private Integer numeroDocumento;

  //Persona Juridica
  private String razonSocial;
  private String tipo;
  private String rubro;

  private String contacto;
  private String tipoContacto;

  private String tipoColaborador;

  private String tarjeta;

  /**
   * Crea un objeto ColaboradorInputDto a partir de un array de Strings.
   *
   * @param line Array de Strings.
   * @return ColaboradorInputDto.
   */

  public static ColaboradorInputDto fromCsv(String[] line) {
    ColaboradorInputDto colaboradorInputDto = new ColaboradorInputDto();
    colaboradorInputDto.setTipoDocumento(line[0]);
    colaboradorInputDto.setNumeroDocumento(Integer.valueOf(line[1]));
    colaboradorInputDto.setNombre(line[2]);
    colaboradorInputDto.setApellido(line[3]);
    colaboradorInputDto.setContacto(line[4]);
    colaboradorInputDto.setTipoContacto(TipoContacto.MAIL.toString());
    colaboradorInputDto.setTipoColaborador(TipoColaborador.FISICO.toString());
    return colaboradorInputDto;
  }

  /**
   * Metodo que genera un colaborador dto a partir del contexto.
   *
   * @param context Contexto.
   * @return colaboradorInputDto generado.
   */

  public static ColaboradorInputDto fromContext(Context context) {
    ColaboradorInputDto colaboradorInputDto = new ColaboradorInputDto();

    if (Objects.requireNonNull(context.formParam("tipoColaborador")).equals("JURIDICO")) {
      colaboradorInputDto.setRazonSocial(context.formParam("razonSocial"));
      colaboradorInputDto.setTipo(context.formParam("tipoOrganizacion"));
      colaboradorInputDto.setRubro(context.formParam("rubro"));
    } else {
      colaboradorInputDto.setNombre(context.formParam("nombre"));
      colaboradorInputDto.setApellido(context.formParam("apellido"));
      colaboradorInputDto.setTipoDocumento(context.formParam("tipoDocumento"));
      colaboradorInputDto.setNumeroDocumento(
          Integer.valueOf(Objects.requireNonNull(context.formParam("numeroDocumento")))
      );
    }

    colaboradorInputDto.setContacto(context.formParam("contacto"));
    colaboradorInputDto.setTipoContacto(context.formParam("tipoContacto"));
    colaboradorInputDto.setTipoColaborador(context.formParam("tipoColaborador"));
    return colaboradorInputDto;
  }
}
