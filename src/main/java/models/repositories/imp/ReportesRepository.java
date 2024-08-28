package models.repositories.imp;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;
import models.entities.reporte.ReporteHeladera;
import models.repositories.interfaces.InterfaceReportesRepository;

/**
 * Clase que representa a los repositorios de reportes de heladeras.
 */

//TODO todos los metodos
public class ReportesRepository implements InterfaceReportesRepository, WithSimplePersistenceUnit {
  @Override
  public void guardar(ReporteHeladera... reportes) {

  }

  @Override
  public void guardar(ReporteHeladera reporte) {

  }

  @Override
  public void modificar(ReporteHeladera reporte) {

  }

  @Override
  public void eliminarFisico(ReporteHeladera reporte) {

  }

  @Override
  public void eliminar(ReporteHeladera reporte) {

  }

  @Override
  public Optional<ReporteHeladera> buscarPorId(Long id) {
    return Optional.empty();
  }

  @Override
  public List<ReporteHeladera> buscarTodos() {
    return List.of();
  }

  @Override
  public ReporteHeladera buscarSemanalPorHeladera(Long id) {
    return null;
  }

  @Override
  public List<ReporteHeladera> buscarTodosUltimaSemana() {
    return List.of();
  }
}
