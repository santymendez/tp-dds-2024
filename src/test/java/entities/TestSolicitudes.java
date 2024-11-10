package entities;

import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestSolicitudes {
  Colaborador colaborador;
  Heladera heladera1;
  Heladera heladera2;
  TarjetaColaborador tarjeta;

  @BeforeEach
  public void inicializar(){
    this.colaborador = new Colaborador();
    this.tarjeta = new TarjetaColaborador();
    this.tarjeta.setColaborador(this.colaborador);

    Direccion direccion1 = new Direccion();
    Direccion direccion2 = new Direccion();

    Modelo modelo1 = new Modelo();
    Modelo modelo2 = new Modelo();

    this.heladera1 = new Heladera();
    this.heladera1.setDireccion(direccion1);
    this.heladera1.setNombre("Heladera 1");
    this.heladera1.setFechaDeCreacion(LocalDate.now());
    this.heladera1.setModelo(modelo1);

    this.heladera2 = new Heladera();
    this.heladera2.setDireccion(direccion2);
    this.heladera2.setNombre("Heladera 2");
    this.heladera2.setFechaDeCreacion(LocalDate.now());
    this.heladera2.setModelo(modelo2);

    tarjeta.agregarUso(new UsoTarjetaColaborador(), heladera1);
    this.heladera1.habilitarTarjeta(this.tarjeta);

    this.heladera1.habilitarTarjeta(this.tarjeta);
    this.heladera2.habilitarTarjeta(this.tarjeta);
  }

  @Test
  @DisplayName("Un colaborador puede abrir una heladera")
  public void test01() {

    Assertions.assertTrue(this.heladera1.intentarAbrirCon(this.tarjeta, LocalDateTime.now()));
  }

  @Test
  @DisplayName("Un colaborador no puede abrir una heladera porque la solicitud ha expirado")
  public void test02() {
    LocalDateTime fechaConHora = LocalDateTime.of(2024, 6, 5, 12, 30, 0);
    this.tarjeta.getUsos().get(0).getApertura().setFechaSolicitud(fechaConHora);
    Assertions.assertFalse(heladera1.intentarAbrirCon(this.tarjeta, LocalDateTime.now()));
  }
}