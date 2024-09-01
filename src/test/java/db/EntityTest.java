package db;

import java.util.List;

import models.entities.direccion.Ciudad;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.RepositoryLocator;
import models.repositories.imp.TecnicosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntityTest {

  Tecnico tecnico;
  Ciudad cordoba;

  @BeforeEach
  void inicializarObjetos() {

    cordoba = new Ciudad("Cordoba", null);

    tecnico = new Tecnico(
        "Juan",
        "Perez",
        null,
        12235896,
        null,
        cordoba
    );
  }

  @Test
  @DisplayName("Guarda una Heladera")
  void persistirTecnico() {
    TecnicosRepository tecnicosRepository = RepositoryLocator.get("tecnicosRepository", TecnicosRepository.class);

    tecnicosRepository.guardar(tecnico);

    List<Tecnico> tecnicosCordobeses = tecnicosRepository.buscarPorCiudad(cordoba);

    Assertions.assertTrue(tecnicosCordobeses.contains(tecnico));
  }

}
