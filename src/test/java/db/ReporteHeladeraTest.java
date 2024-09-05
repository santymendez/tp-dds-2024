package db;

import java.time.LocalDate;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.Estado;
import models.entities.heladera.estados.TipoEstado;
import models.entities.reporte.ReporteHeladera;
import models.repositories.RepositoryLocator;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReporteHeladeraTest {

  Estado estado = new Estado();

  Heladera heladera1 = new Heladera();

  ReporteHeladera reporteHeladera;

  ReportesRepository reportesRepository =
      RepositoryLocator.get("reportesRepository", ReportesRepository.class);

  GenericRepository repoGenerico =
      RepositoryLocator.get("genericRepository", GenericRepository.class);

  @BeforeEach
  public void setUp() {

    estado.setFechaInicial(LocalDate.now());
    estado.setFechaFinal(LocalDate.of(2025,8,14));
    estado.setEstado(TipoEstado.ACTIVA);

    heladera1.setEstadoActual(estado);
    heladera1.setNombre("Heladera 1");
    heladera1.setFechaDeCreacion(LocalDate.now());

    reporteHeladera = new ReporteHeladera(heladera1);
    reporteHeladera.setPath("path");
  }

  @Test
  public void testRepo() {

    repoGenerico.guardar(heladera1);

    //reportesRepository.guardar(reporteHeladera);
  }
}
