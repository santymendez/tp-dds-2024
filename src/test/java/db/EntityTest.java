package db;

import java.util.List;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.tecnico.Tecnico;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.TecnicosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.javalin.Initializer;

public class EntityTest {

  @Test
  @DisplayName("Se pueden guardar y recuperar las entidades en la base de datos")
  void persistirTodo() {

    Initializer.init("simple-persistence-unit");

    TecnicosRepository tecnicosRepository = Initializer.getTecnicosRepository();


    // Recupero al tecnico sin ningun problema

    Optional<Tecnico> tecnico = tecnicosRepository.buscarPorId(Initializer.getLiam().getId());

    Assertions.assertTrue(tecnico.isPresent());

    // Recupero al colaborador sin ningun problema

    Optional<Colaborador> colaborador = Initializer.getColaboradoresRepository()
        .buscarPorDocumento(Initializer.getAugusto().getDocumento().getNroDocumento());

    Assertions.assertEquals(colaborador.get().getNombre(), "Augusto");

    Assertions.assertEquals(colaborador.get().getDocumento().getTipoDocumento(),
        TipoDocumento.DNI);

    // Recupero al vulnerable sin ningun problema

    Optional<Vulnerable> vulnerable = Initializer.getRepoGenerico().buscarPorId(Initializer.getEze().getId(), Vulnerable.class);

    Assertions.assertTrue(vulnerable.isPresent());

    // Recupero la heladera sin ningun problema

    Optional<Heladera> heladera = Initializer.getRepoGenerico().buscarPorId(Initializer.getHeladera1().getId(), Heladera.class);

    Assertions.assertTrue(heladera.isPresent());

    // Recupero la direccion sin ningun problema

    Optional<Direccion> direccion = Initializer.getRepoGenerico().buscarPorId(Initializer.getDireccion1().getId(), Direccion.class);

    Assertions.assertTrue(direccion.isPresent());

    // Recupero la provincia sin ningun problema

    Optional<Provincia> provincia = Initializer.getRepoGenerico().buscarPorId(Initializer.getBuenosAires().getId(), Provincia.class);

    // Recupero una colaboracion sin ningun problema

    List<Colaboracion> colaboraciones =
        Initializer.getColaboracionesRepository().buscarPorTipo(TipoColaboracion.HACERSE_CARGO_HELADERA);

    Colaboracion colaboracion = colaboraciones.get(0);

    Assertions.assertEquals(colaboracion.getTipoColaboracion(),
        TipoColaboracion.HACERSE_CARGO_HELADERA);

    Assertions.assertEquals(colaboracion.getColaborador().getNombre(), "Augusto");

    Assertions.assertEquals(colaboracion.getFechaColaboracion(),
        Initializer.getColocarHeladera().getFechaColaboracion());

  }
}
