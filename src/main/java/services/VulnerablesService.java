package services;

import dtos.VulnerableInputDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.VulnerablesRepository;

/**
 * Service para los vulnerables.
 */

public class VulnerablesService {

  private final GenericRepository genericRepository;
  private final VulnerablesRepository vulnerablesRepository;

  /** Constructor de la clase.
   *
   * @param genericRepository repositorio generico.
   * @param vulnerablesRepository repositorio de vulnerables.
   */

  public VulnerablesService(
      GenericRepository genericRepository,
      VulnerablesRepository vulnerablesRepository
  ) {
    this.genericRepository = genericRepository;
    this.vulnerablesRepository = vulnerablesRepository;
  }

  /** Crea un vulnerable a partir de un input.
   *
   * @param vulnerableInputDto el input de un vulnerable.
   * @param domicilio domicilio del vulnerable.
   */

  public Vulnerable crear(
      VulnerableInputDto vulnerableInputDto,
      Direccion domicilio,
      Colaborador colaborador
  ) {
    Vulnerable vulnerable = new Vulnerable();
    vulnerable.setNombre(vulnerableInputDto.getNombre());
    vulnerable.setFechaNacimiento(vulnerableInputDto.getFechaNacimiento());

    TipoDocumento tipoDocumento = TipoDocumento.valueOf(vulnerableInputDto.getTipoDocumento());
    Integer nroDocumento = vulnerableInputDto.getNumeroDocumento();
    vulnerable.setDocumento(new Documento(nroDocumento, tipoDocumento));

    vulnerable.setDomicilio(domicilio);
    this.vulnerablesRepository.guardar(vulnerable);

    return vulnerable;
  }

  /** Crea un menor a partir de un input.
   *
   * @param menoresInputDto el input de los menores.
   * @param padre el padre del menor.
   */

  public void crearMenores(
      List<VulnerableInputDto> menoresInputDto,
      Vulnerable padre,
      Colaborador colaborador
  ) {

    List<RegistroVulnerable> registros = new ArrayList<>();

    for (VulnerableInputDto menorInputDto : menoresInputDto) {
      Integer numeroDocumento = menorInputDto.getNumeroDocumento();
      LocalDate fechaNacimiento = menorInputDto.getFechaNacimiento();

      Optional<Vulnerable> posibleMenor = this.vulnerablesRepository
          .buscarPorDocumentoFechaNacimiento(numeroDocumento, fechaNacimiento);

      Vulnerable menor;

      if (posibleMenor.isPresent()) {
        menor = posibleMenor.get();
      } else {
        menor = new Vulnerable();
        menor.setNombre(menorInputDto.getNombre());
        menor.setFechaNacimiento(fechaNacimiento);

        TipoDocumento tipoDocumento = TipoDocumento.valueOf(menorInputDto.getTipoDocumento());
        menor.setDocumento(new Documento(numeroDocumento, tipoDocumento));
        menor.setDomicilio(padre.getDomicilio());

        RegistroVulnerable registroVulnerable =
            new RegistroVulnerable(colaborador, menor, LocalDate.now());
        registros.add(registroVulnerable);
      }
      padre.agregarMenorCargo(menor);
    }

    this.genericRepository.modificar(padre);
    this.genericRepository.guardarColeccion(registros);
  }
}
