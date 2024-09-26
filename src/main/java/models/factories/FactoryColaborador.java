package models.factories;

import dtos.ColaboradorInputDto;
import dtos.DireccionInputDto;
import java.util.ArrayList;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.colaborador.reconocimiento.Reconocimiento;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.contacto.TipoContacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;

/**
 * Clase Factory de los colaboradores.
 */

public class FactoryColaborador {

  /** Genera un colaborador a partir de un DTO.
   *
   * @param colaboradorInputDto DTO con el que se genera un colaborador.
   * @return colaborador creado.
   */

  public static Colaborador crearCon(ColaboradorInputDto colaboradorInputDto) {
    Colaborador nuevoColaborador;

    if (colaboradorInputDto.getTipoColaborador().equals(TipoColaborador.FISICO.toString())) {
      nuevoColaborador = Colaborador.builder()
          .nombre(colaboradorInputDto.getNombre())
          .apellido(colaboradorInputDto.getApellido())
          .tipoColaborador(TipoColaborador.FISICO)
          .contacto(new Contacto(colaboradorInputDto.getContacto(),
              TipoContacto.valueOf(colaboradorInputDto.getTipoContacto())))
          .documento(new Documento(Integer.valueOf(colaboradorInputDto.getNumeroDocumento()),
              TipoDocumento.valueOf(colaboradorInputDto.getTipoDocumento()))
          )
          .reconocimiento(new Reconocimiento())
          .colaboraciones(new ArrayList<>())
          .build();
    } else {
      nuevoColaborador = Colaborador.builder()
          .tipoColaborador(TipoColaborador.JURIDICO)
          .rubro(colaboradorInputDto.getRubro())
          .tipo(colaboradorInputDto.getTipo())
          .razonSocial(colaboradorInputDto.getRazonSocial())
          .contacto(new Contacto(colaboradorInputDto.getContacto(),
              TipoContacto.valueOf(colaboradorInputDto.getTipoContacto())))
          .reconocimiento(new Reconocimiento())
          .colaboraciones(new ArrayList<>())
          .build();
    }
    return nuevoColaborador;
  }
}
