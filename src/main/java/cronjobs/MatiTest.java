package cronjobs;

import config.RepositoryLocator;
import java.util.List;
import models.db.PersistenceUnitSwitcher;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.ColaboradoresRepository;

/**
 * Clase para probar los repositorios.
 */

public class MatiTest {

  /**
   * Main para probar los repositorios.
   *
   * @param args argumentos que no se usan.
   */

  public static void main(String[] args) {
    PersistenceUnitSwitcher.switchPersistenceUnit("cronjob-persistence-unit");

    List<Colaborador> colaboradores = RepositoryLocator
        .instanceOf(ColaboradoresRepository.class).buscarTodos();

    colaboradores.forEach(c -> c.setNombre("MATIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII"));

    colaboradores.forEach(c -> RepositoryLocator
        .instanceOf(ColaboradoresRepository.class).guardar(c));

    colaboradores.forEach(c -> System.out.println(c.getNombre()));
  }
}
