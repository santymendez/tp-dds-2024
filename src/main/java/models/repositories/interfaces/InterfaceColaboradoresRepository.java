package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.Colaborador;

/**
 * Interfaz Repositorio para los Colaboradores.
 */

public interface InterfaceColaboradoresRepository {

  void guardar(Colaborador... colaboradores);

  void guardar(Colaborador colaborador);

  void modificar(Colaborador colaborador);

  void eliminarFisico(Colaborador colaborador);

  void eliminar(Colaborador colaborador);

  Optional<Colaborador> buscarPorId(Long id);

  Optional<Colaborador> buscarPorDocumento(Integer nroDocumento);

  List<Colaboracion> buscarTodos();
}
