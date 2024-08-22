package db;

import java.time.LocalDate;
import models.db.EntityManagerHelper;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.heladera.vianda.Comida;
import models.entities.heladera.vianda.Vianda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntityTest {
  Heladera heladeraMedrano;
  Direccion direccionMedrano;
  //Modelo modeloMedrano;
  Vianda vianda1;
  Vianda vianda2;
  Vianda vianda3;
  Comida comida1;
  Comida comida2;
  Comida comida3;

  @BeforeEach
  public void inicializar() {

    this.heladeraMedrano = new Heladera();
    this.direccionMedrano = new Direccion();
    //this.modeloMedrano = new Modelo();

    this.direccionMedrano.setUbicacion("Av. Medrano 951");
    this.direccionMedrano.setLatitud(-34.5983f);
    this.direccionMedrano.setLongitud(-58.4202f);
    this.direccionMedrano.setBarrio(null);
    //this.modeloMedrano.setNombre("M26-06-11B");
    //this.modeloMedrano.setTemperaturaMaxima(35.0f);
    //this.modeloMedrano.setTemperaturaMinima(-4.0f);

    this.heladeraMedrano.setNombre("Heladera Medrano");
    this.heladeraMedrano.setDireccion(this.direccionMedrano);
    //this.heladeraMedrano.setModelo(this.modeloMedrano);
    //this.heladeraMedrano.getModAlmacenamiento().setCapacidadMaximaViandas(100);
    this.heladeraMedrano.setFechaDeCreacion(LocalDate.of(2011, 6, 26));

    this.comida1 = new Comida("Salchichas", LocalDate.of(2025,3,2));
    this.comida2 = new Comida("1Kg de Bergamota", LocalDate.of(2024,9,7));
    this.comida3 = new Comida("Polenta", LocalDate.of(2027,4,12));

    this.vianda1 = new Vianda(this.comida1,
        LocalDate.now(),
        null,
        this.heladeraMedrano,
        1000,
        200.0f
        );

    this.vianda2 = new Vianda(this.comida2,
        LocalDate.of(2024,11,25),
        null,
        this.heladeraMedrano,
        500,
        1000.0f);

    this.vianda3 = new Vianda(this.comida3,
        LocalDate.of(2024,7,22),
        null,
        this.heladeraMedrano,
        300,
        50.0f);

    //this.heladeraMedrano.getModAlmacenamiento().agregarVianda(vianda1);
    //this.heladeraMedrano.getModAlmacenamiento().agregarVianda(vianda2);
    //this.heladeraMedrano.getModAlmacenamiento().agregarVianda(vianda3);

  }

  @Test
  public void persistirHeladera() {

    EntityManagerHelper.beginTransaction();

    EntityManagerHelper.persist(direccionMedrano);

    EntityManagerHelper.persist(heladeraMedrano);

    EntityManagerHelper.commit();
  }

  @Test
  public void recuperarHeladera() {

    /*
    Heladera heladerareal = (Heladera) EntityManagerHelper
        .createQuery("from Heladera where nombre = 'Heladera Medrano'")
        .setMaxResults(1)
        .getSingleResult();
    
    // Las heladeras tienen el mismo nombre
    Assertions.assertEquals(
        this.heladeraMedrano.getNombre(),
        heladerareal.getNombre());

    // Las heladeras tienen la misma direccion
    Assertions.assertEquals(
        this.heladeraMedrano.getDireccion().getUbicacion(),
        heladerareal.getDireccion().getUbicacion());

    // Las heladeras tienen el mismo modelo
    Assertions.assertEquals(
        this.heladeraMedrano.getModelo().getNombre(),
        heladerareal.getModelo().getNombre());

    // Las heladeras tienen la misma cantidad de viandas
    Assertions.assertEquals(
        this.heladeraMedrano.getModAlmacenamiento().getViandas().size(),
        heladerareal.getModAlmacenamiento().getViandas().size());
    */
    // Si esto ocurre hay grandes chances de que sea la misma heladera
  }
}
