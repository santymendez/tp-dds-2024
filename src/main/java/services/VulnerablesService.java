package services;

import config.ServiceLocator;
import dtos.DireccionInputDto;
import dtos.VulnerableInputDto;
import java.time.LocalDate;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.vulnerable.Vulnerable;

/**
 * Service para los vulnerables.
 */

//TODO REVISAR
public class VulnerablesService {

  /**
   * Crea un vulnerable a partir de un DTO.
   *
   * @param vulnerableInputDto el DTO con los datos del vulnerable.
   * @return el vulnerable creado.
   */

  public Vulnerable crear(VulnerableInputDto vulnerableInputDto) {
    Vulnerable vulnerable = new Vulnerable();
    vulnerable.setNombre(vulnerableInputDto.getNombre());
    vulnerable.setFechaNacimiento(LocalDate.parse(vulnerableInputDto.getFechaNacimiento()));

    TipoDocumento tipoDocumento = TipoDocumento.valueOf(vulnerableInputDto.getTipoDocumento());
    Integer nroDocumento = Integer.parseInt(vulnerableInputDto.getNumeroDocumento());
    vulnerable.setDocumento(new Documento(nroDocumento, tipoDocumento));

    String nombreUbicacion = vulnerableInputDto.getCalle() + " " + vulnerableInputDto.getNumero()
        + ", " + vulnerableInputDto.getBarrio() + ", " + vulnerableInputDto.getCiudad()
        + ", " + vulnerableInputDto.getProvincia();

    //TODO estan como 0 por ahora, pensar que dato usar
    DireccionInputDto direccionInputDto = DireccionInputDto.builder()
        .nombreUbicacion(nombreUbicacion)
        .latitud(String.valueOf(0)) // falta
        .longitud(String.valueOf(0)) // falta
        .provincia(vulnerableInputDto.getProvincia())
        .ciudad(vulnerableInputDto.getCiudad())
        .barrio(vulnerableInputDto.getBarrio())
        .calle(vulnerableInputDto.getCalle())
        .numero(vulnerableInputDto.getNumero())
        .build();

    vulnerable.setDomicilio(ServiceLocator.instanceOf(DireccionesService.class)
        .crear(direccionInputDto));

    return vulnerable;
  }
}
