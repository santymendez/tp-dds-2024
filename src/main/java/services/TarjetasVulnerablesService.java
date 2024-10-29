package services;

import java.time.LocalDate;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;

/**
 * Service para las tarjetas vulnerables.
 */

public class TarjetasVulnerablesService {
  private final TarjetasVulnerablesRepository tarjetasVulnerablesRepository;
  private final GenericRepository genericRepository;

  public TarjetasVulnerablesService(
      TarjetasVulnerablesRepository tarjetasVulnerablesRepository,
      GenericRepository genericRepository
  ) {
    this.tarjetasVulnerablesRepository = tarjetasVulnerablesRepository;
    this.genericRepository = genericRepository;
  }

  /**
   * Crea una tarjeta vulnerable.
   *
   * @param colaborador el colaborador que crea la tarjeta.
   * @param vulnerable el vulnerable al que se le crea la tarjeta.
   * @param uuid el uuid de la tarjeta.
   */

  public TarjetaVulnerable crear(Colaborador colaborador, Vulnerable vulnerable, String uuid) {
    Optional<TarjetaVulnerable> posibleTarjeta =
        this.tarjetasVulnerablesRepository.buscarPorUuid(uuid);


    if (!posibleTarjeta.isEmpty()) {
      return null;
    }

    TarjetaVulnerable tarjetaVulnerable = posibleTarjeta.get();

    RegistroVulnerable registroVulnerable =
        new RegistroVulnerable(colaborador, vulnerable, LocalDate.now());
    this.genericRepository.guardar(registroVulnerable);

    tarjetaVulnerable.setRegistroVulnerable(registroVulnerable);
    tarjetaVulnerable.calcularUsos();

    this.tarjetasVulnerablesRepository.modificar(tarjetaVulnerable);
    return tarjetaVulnerable;
  }
}
