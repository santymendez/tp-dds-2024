package services;

import dtos.ModeloInputDto;
import models.entities.heladera.Modelo;
import models.repositories.ModelosRepository;
import models.repositories.imp.GenericRepository;

/**
 * Servicio de modelos.
 */

public class ModelosService {
  private final ModelosRepository modelosRepository;

  public ModelosService(ModelosRepository modelosRepository) {
    this.modelosRepository = modelosRepository;
  }

  /**
   * Crea un modelo a partir de un DTO.
   *
   * @param modeloInputDto el DTO con los datos del modelo.
   */

  public void crear(ModeloInputDto modeloInputDto) {
    Modelo modelo = new Modelo();
    modelo.setNombre(modeloInputDto.getNombre());
    modelo.setTemperaturaMinima(Float.parseFloat(modeloInputDto.getTemperaturaMinima()));
    modelo.setTemperaturaMaxima(Float.parseFloat(modeloInputDto.getTemperaturaMaxima()));
    modelo.setCapacidadMaximaViandas(Integer.parseInt(modeloInputDto.getCapacidadMaximaViandas()));

    this.modelosRepository.guardar(modelo);
  }
}
