package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;

/**
 * Interfaz repositorio para las tarjetas de los vulnerables.
 */

public interface InterfaceTarjetasVulnerablesRepository {
  void guardar(TarjetaVulnerable... tarjetasVulnerables);

  void guardar(TarjetaVulnerable tarjetaVulnerable);

  void modificar(TarjetaVulnerable tarjetaVulnerable);

  void eliminarFisico(TarjetaVulnerable tarjetaVulnerable);

  void eliminar(TarjetaVulnerable tarjetaVulnerable);

  Optional<TarjetaVulnerable> buscarPorId(Long id);

  List<TarjetaVulnerable> buscarTodos();
}
