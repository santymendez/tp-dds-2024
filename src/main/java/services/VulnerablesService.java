package services;

import dtos.VulnerableInputDto;
import java.time.LocalDate;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;

/**
 * Service para los vulnerables.
 */

public class VulnerablesService {

  private final GenericRepository genericRepository;
  private final TarjetasVulnerablesRepository tarjetasVulnerablesRepository;

  public VulnerablesService(
      GenericRepository genericRepository,
      TarjetasVulnerablesRepository tarjetasVulnerablesRepository
  ) {
    this.genericRepository = genericRepository;
    this.tarjetasVulnerablesRepository = tarjetasVulnerablesRepository;
  }

  /**
   * Crea un vulnerable a partir de un DTO.
   *
   * @param vulnerableInputDto el DTO con los datos del vulnerable.
   */

  public void crear(VulnerableInputDto vulnerableInputDto, Colaborador colaborador) {
    this.crear(vulnerableInputDto, null, colaborador);
  }

  /** Crea un vulnerable a partir de un input.
   *
   * @param vulnerableInputDto el input de un vulnerable.
   * @param domicilio domicilio del vulnerable.
   */

  public void crear(
      VulnerableInputDto vulnerableInputDto,
      Direccion domicilio,
      Colaborador colaborador
  ) {
    Vulnerable vulnerable = new Vulnerable();
    vulnerable.setNombre(vulnerableInputDto.getNombre());
    vulnerable.setFechaNacimiento(LocalDate.parse(vulnerableInputDto.getFechaNacimiento()));

    TipoDocumento tipoDocumento = TipoDocumento.valueOf(vulnerableInputDto.getTipoDocumento());
    Integer nroDocumento = Integer.parseInt(vulnerableInputDto.getNumeroDocumento());
    vulnerable.setDocumento(new Documento(nroDocumento, tipoDocumento));

    vulnerable.setDomicilio(domicilio);
    this.genericRepository.guardar(vulnerable);

    Optional<TarjetaVulnerable> posibleTarjeta =
        this.tarjetasVulnerablesRepository.buscarPorUuid(vulnerableInputDto.getTarjeta());

    TarjetaVulnerable tarjetaVulnerable = posibleTarjeta.get();

    RegistroVulnerable registroVulnerable =
        new RegistroVulnerable(colaborador, vulnerable, LocalDate.now());
    this.genericRepository.guardar(registroVulnerable);

    tarjetaVulnerable.setRegistroVulnerable(registroVulnerable);
    tarjetaVulnerable.calcularUsos();

    this.tarjetasVulnerablesRepository.modificar(tarjetaVulnerable);
  }
}
