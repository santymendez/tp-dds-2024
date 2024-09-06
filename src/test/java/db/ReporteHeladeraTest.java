package db;

import java.time.LocalDate;
import models.entities.direccion.Barrio;
import models.entities.direccion.Ciudad;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
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

  Direccion direccion = new Direccion();
  Barrio barrio = new Barrio();
  Ciudad ciudad = new Ciudad();
  Provincia provincia = new Provincia();

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

    provincia.setNombreProvincia("bsas");
    ciudad.setNombreCiudad("oa");
    ciudad.setProvincia(provincia);
    barrio.setNombreBarrio("barrio");
    barrio.setCiudad(ciudad);

    direccion.setNombreUbicacion("algun lugar");
    direccion.setBarrio(barrio);

    repoGenerico.guardar(provincia);
    repoGenerico.guardar(direccion);

    heladera1.setEstadoActual(estado);
    heladera1.setNombre("Heladera 1");
    heladera1.setFechaDeCreacion(LocalDate.now());
    heladera1.setCapacidadMaximaViandas(10);
    heladera1.setDireccion(direccion);
    heladera1.setModelo(new Modelo());
    heladera1.getModelo().setTemperaturaMaxima(25f);
    heladera1.getModelo().setTemperaturaMinima(20f);

    reporteHeladera = new ReporteHeladera(heladera1);
    reporteHeladera.setPath("path");
  }

  @Test
  public void testRepo() {

    repoGenerico.guardar(heladera1);

    reportesRepository.guardar(reporteHeladera);
  }
}
