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

  @BeforeEach
  public void inicializar(){
    this.colaborador = new Colaborador();
    this.colaborador.agregarTarjeta(new TarjetaColaborador());

    this.heladera1 = new Heladera(new Direccion(), "Heladera 1", 3, LocalDate.now(), new Modelo());
    this.heladera2 = new Heladera(new Direccion(), "Heladera 2", 4, LocalDate.now(), new Modelo());

    this.colaborador.ultimaTarjeta().getUsos().add(new UsoTarjetaColaborador(this.heladera1));
    this.colaborador.agregarSolicitudApertura(this.heladera1);

    this.colaborador.agregarSolicitudApertura(this.heladera1);
    this.colaborador.agregarSolicitudApertura(this.heladera2);
  }

  @Test
  @DisplayName("Un colaborador puede abrir una heladera")
  public void test01() {
    Assertions.assertTrue(this.heladera1.intentarAbrirCon(this.colaborador.ultimaTarjeta()));
  }

  @Test
  @DisplayName("Un colaborador no puede abrir una heladera porque la solicitud ha expirado")
  public void test02() {
    LocalDateTime fechaConHora = LocalDateTime.of(2024, 6, 5, 12, 30, 0);
    this.colaborador.ultimaTarjeta().getUsos().get(0).getApertura().setFechaSolicitud(fechaConHora);
    Assertions.assertThrows(RuntimeException.class, () -> heladera1.intentarAbrirCon(colaborador.ultimaTarjeta()));
  }
}