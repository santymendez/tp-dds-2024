package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.personas.tarjetas.vulnerable.UsoTarjetaVulnerable;
import models.repositories.PersistenciaSimple;

/**
 * Interfaz repositorio para los usos de las tarjetas de los vulnerables.
 */

public interface InterfaceUsosTarjetasVulnerablesRepository extends PersistenciaSimple {

  void guardar(UsoTarjetaVulnerable... usosTarjetasVulnerables);

  void guardar(UsoTarjetaVulnerable usoTarjetaVulnerable);

  void modificar(UsoTarjetaVulnerable usoTarjetaVulnerable);

  void eliminarFisico(UsoTarjetaVulnerable usoTarjetaVulnerable);

  void eliminar(UsoTarjetaVulnerable usoTarjetaVulnerable);

  Optional<UsoTarjetaVulnerable> buscarPorId(Long id);

  List<UsoTarjetaVulnerable> buscarTodos();

  List<UsoTarjetaVulnerable> buscarPorBarrio(String nombreBarrio);
}
