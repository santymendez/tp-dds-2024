package cronjobs;

import config.RepositoryLocator;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.ColaboradoresRepository;
import java.util.List;

public class Prueba {
  public static void main(String[] args) {
    List<Colaborador> colaboradores = RepositoryLocator.instanceOf(ColaboradoresRepository.class).buscarTodos();
    colaboradores.forEach(c -> c.setNombre("MATIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII"));
    colaboradores.forEach(c -> RepositoryLocator.instanceOf(ColaboradoresRepository.class).guardar(c));

    colaboradores.forEach(c -> System.out.println(c.getNombre()));
  }
}
