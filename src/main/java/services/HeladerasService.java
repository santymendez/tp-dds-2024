package services;

import dtos.DireccionInputDto;
import dtos.HeladeraInputDto;
import java.time.LocalDate;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;

/**
 * Service para instanciar heladeras a partir de sus DTOs.
 */

public class HeladerasService {

  /**
   * Constructor de la clase.
   *
   * @param heladeraInputDto el DTO con los datos de la heladera.
   * @return la heladera creada.
   */

  public Heladera crear(HeladeraInputDto heladeraInputDto) {
    Heladera heladera = new Heladera();
    heladera.setNombre(heladeraInputDto.getNombre());
    heladera.setFechaDeCreacion(LocalDate.parse(heladeraInputDto.getFechaCreacion()));

    Modelo modelo = new Modelo();
    modelo.setNombre(heladeraInputDto.getModelo());
    modelo.setTemperaturaMaxima(Float.parseFloat(heladeraInputDto.getTemperaturaMax()));
    modelo.setTemperaturaMinima(Float.parseFloat(heladeraInputDto.getTemperaturaMin()));

    heladera.setModelo(modelo);

    heladera
        .setCapacidadMaximaViandas(Integer.valueOf(heladeraInputDto.getCapacidadMaximaViandas()));

    return heladera;
  }
}
