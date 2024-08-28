import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDate;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.repositories.RepositoryLocator;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;

/**
 * Main de prueba.
 */

public class MainExample implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    MainExample instance = new MainExample();
    instance.guardarColaboracion();
  }

  private void guardarColaboracion() {
    withTransaction(() -> {
      Colaboracion colaboracion = new Colaboracion();
      colaboracion.setTipoColaboracion(TipoColaboracion.DONAR_VIANDA);
      colaboracion.setFechaColaboracion(LocalDate.of(2024, 1, 11));

      Colaborador colaborador = new Colaborador();
      colaborador.setTipoColaborador(TipoColaborador.FISICO);
      colaboracion.setColaborador(colaborador);
      RepositoryLocator.get("colaboradoresRepository", ColaboradoresRepository.class)
          .guardar(colaborador);

      RepositoryLocator.get("colaboracionesRepository", ColaboracionesRepository.class)
          .guardar(colaboracion);
    });
  }
}
