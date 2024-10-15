package services;

import dtos.HeladeraInputDto;
import java.time.LocalDate;
import java.util.Objects;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.entities.heladera.sensores.movimiento.SensorMovimiento;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;
import models.repositories.imp.GenericRepository;

/**
 * Service para instanciar heladeras a partir de sus DTOs.
 */

public class HeladerasService {

  private final GenericRepository genericRepository;

  public HeladerasService(GenericRepository heladerasRepository) {
    this.genericRepository = heladerasRepository;
  }

  /**
   * Constructor de la clase.
   *
   * @param heladeraInputDto el DTO con los datos de la heladera.
   */

  public Heladera crear(HeladeraInputDto heladeraInputDto, Direccion direccion, Modelo modelo) {
    Heladera heladera = new Heladera();
    heladera.setNombre(heladeraInputDto.getNombre());
    heladera.setFechaDeCreacion(LocalDate.parse(heladeraInputDto.getFechaCreacion()));
    heladera.setModelo(modelo);
    heladera.setDireccion(direccion);

    this.genericRepository.guardar(heladera);

    SensorTemperatura sensorTemperatura = new SensorTemperatura();
    sensorTemperatura.setHeladera(heladera);
    this.genericRepository.guardar(sensorTemperatura);

    SensorMovimiento sensorMovimiento = new SensorMovimiento();
    sensorMovimiento.setHeladera(heladera);
    this.genericRepository.guardar(sensorMovimiento);

    return heladera;
  }

  /** Metodo del service que permite la modificacion de heladeras a partir de un dto.
   *
   * @param heladera heladera a modificar.
   * @param heladeraInputDto dto con los datos moficados.
   */

  public void modificar(Heladera heladera,
                        HeladeraInputDto heladeraInputDto) {

    if (!Objects.equals(heladeraInputDto.getNombre(), "")) {
      heladera.setNombre(heladeraInputDto.getNombre());
    }

    if (!Objects.equals(heladeraInputDto.getFechaCreacion(), "")) {
      heladera.setFechaDeCreacion(LocalDate.parse(heladeraInputDto.getFechaCreacion()));
    }

    this.genericRepository.modificar(heladera);
  }
}
