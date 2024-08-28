package models.repositories.interfaces;

import java.util.List;
import java.util.Optional;
import models.entities.reporte.ReporteHeladera;

/**
 * Interfaz para el repositorio de los reportes.
 */

public interface InterfaceReportesRepository {
  void guardar(ReporteHeladera... reportes);

  void guardar(ReporteHeladera reporte);

  void modificar(ReporteHeladera reporte);

  void eliminarFisico(ReporteHeladera reporte);

  void eliminar(ReporteHeladera reporte);

  Optional<ReporteHeladera> buscarPorId(Long id);

  List<ReporteHeladera> buscarTodos();

  ReporteHeladera buscarSemanalPorHeladera(Long id);

  List<ReporteHeladera> buscarTodosUltimaSemana();
}
