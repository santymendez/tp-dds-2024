package entities;

import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;
import models.entities.personas.colaborador.Colaborador;
import models.entities.reporte.ReporteHeladera;
import models.entities.reporte.ViandasPorColaborador;
import models.entities.reporte.generador.GeneradorReporte;
import models.repositories.heladera.HeladerasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestGeneradorReporte {

  @BeforeEach
  public void setUp() {
    Colaborador santi = new Colaborador();
    santi.setNombre("Santi");
    Colaborador mati = new Colaborador();
    mati.setNombre("Mati");
    HeladerasRepository heladerasRepository = new HeladerasRepository();

    Heladera liam = new Heladera(
        new Direccion(), "Liam", 5, LocalDate.now(), new Modelo(), new SensorMovimiento()
    );
    liam.getModReportes().setReporteHeladera(new ReporteHeladera(liam));
    liam.getModReportes().getReporteHeladera().setFallas(50);
    liam.getModReportes().getReporteHeladera().setViandasColocadas(20);
    liam.getModReportes().getReporteHeladera().setViandasRetiradas(10);
    liam.getModReportes().getReporteHeladera().setViandasPorColaboradores(new ArrayList<>());
    liam.getModReportes().getReporteHeladera().getViandasPorColaboradores().add(new ViandasPorColaborador(santi, 10));
    liam.getModReportes().getReporteHeladera().getViandasPorColaboradores().add(new ViandasPorColaborador(mati, 10));
    heladerasRepository.guardar(liam);

    Heladera augusto = new Heladera(
        new Direccion(), "Augusto", 5, LocalDate.now(), new Modelo(), new SensorMovimiento()
    );
    augusto.getModReportes().setReporteHeladera(new ReporteHeladera(liam));
    augusto.getModReportes().getReporteHeladera().setFallas(100);
    augusto.getModReportes().getReporteHeladera().setViandasColocadas(30);
    augusto.getModReportes().getReporteHeladera().setViandasRetiradas(20);
    augusto.getModReportes().getReporteHeladera().setViandasPorColaboradores(new ArrayList<>());
    augusto.getModReportes().getReporteHeladera().getViandasPorColaboradores().add(new ViandasPorColaborador(santi, 15));
    augusto.getModReportes().getReporteHeladera().getViandasPorColaboradores().add(new ViandasPorColaborador(mati, 15));
    heladerasRepository.guardar(augusto);

    Heladera iniaki = new Heladera(
        new Direccion(), "Iñaki", 5, LocalDate.now(), new Modelo(), new SensorMovimiento()
    );
    iniaki.getModReportes().setReporteHeladera(new ReporteHeladera(liam));
    iniaki.getModReportes().getReporteHeladera().setFallas(150);
    iniaki.getModReportes().getReporteHeladera().setViandasColocadas(40);
    iniaki.getModReportes().getReporteHeladera().setViandasRetiradas(25);
    iniaki.getModReportes().getReporteHeladera().setViandasPorColaboradores(new ArrayList<>());
    iniaki.getModReportes().getReporteHeladera().getViandasPorColaboradores().add(new ViandasPorColaborador(santi, 20));
    iniaki.getModReportes().getReporteHeladera().getViandasPorColaboradores().add(new ViandasPorColaborador(mati, 20));
    heladerasRepository.guardar(iniaki);

    GeneradorReporte generadorReporte = new GeneradorReporte(heladerasRepository);
  }

  @Test
  @DisplayName("Se genera un reporte en formato PDF, con 3 paginas, cada pagina con la informacion de una heladera distinta")
  public void testMain() {
    GeneradorReporte.main(null);
  }

}
