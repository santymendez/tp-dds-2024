package services;

import dtos.DireccionInputDto;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.factories.FactoryDireccion;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;

/**
 * Service para las Direcciones.
 */

public class DireccionesService {

  public Direccion crear(DireccionInputDto direccionInputDto) {
    return FactoryDireccion.crearCon(direccionInputDto);
  }

  /** Crea una direccion.
   *
   * @param direccionInputDto input de la direccion.
   * @param provincia provincia de la direccion.
   * @return direccion creada.
   */

  public Direccion crear(DireccionInputDto direccionInputDto, Provincia provincia) {
    Direccion direccion = FactoryDireccion.crearCon(direccionInputDto);
    direccion.getBarrio().getCiudad().setProvincia(provincia);
    return direccion;
  }
}
