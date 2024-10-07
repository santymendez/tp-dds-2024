package dtos;

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
  private String numeroDocumento;

  //Persona Juridica
  private String razonSocial;
  private String tipo;
  private String rubro;

  private String contacto;
  private String tipoContacto;

  private String tipoColaborador;

  /**
   * Crea un objeto ColaboradorInputDto a partir de un array de Strings.
   *
   * @param line Array de Strings.
   * @return ColaboradorInputDto.
   */

  public static ColaboradorInputDto fromCsv(String[] line) {
    ColaboradorInputDto colaboradorInputDto = new ColaboradorInputDto();
    colaboradorInputDto.setTipoDocumento(line[0]);
    colaboradorInputDto.setNumeroDocumento(line[1]);
    colaboradorInputDto.setNombre(line[2]);
    colaboradorInputDto.setApellido(line[3]);
    colaboradorInputDto.setContacto(line[4]);
    colaboradorInputDto.setTipoContacto(TipoContacto.MAIL.toString());
    colaboradorInputDto.setTipoColaborador(TipoColaborador.FISICO.toString());
    return colaboradorInputDto;
  }
}
