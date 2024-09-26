package controllers;

import dtos.DireccionInputDto;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.ProvinciasRepository;
import services.DireccionesService;

/**
 * Controller de las direcciones.
 */

public class DireccionesController {
  private final DireccionesService direccionesService;
  private final DireccionesRepository direccionesRepository;
  private final ProvinciasRepository provinciasRepository;

  /**
   * Constructor del controller.
   *
   * @param direccionesService .
   * @param direccionesRepository .
   * @param provinciasRepository .
   */

  public DireccionesController(
      DireccionesService direccionesService,
      DireccionesRepository direccionesRepository,
      ProvinciasRepository provinciasRepository
  ) {
    this.direccionesService = direccionesService;
    this.direccionesRepository = direccionesRepository;
    this.provinciasRepository = provinciasRepository;
  }

  /**
   * Crea una direccion a partir de un input.
   *
   * @param direccionInputDto input de la direccion.
   */

  public Direccion crear(DireccionInputDto direccionInputDto) {
    Optional<Direccion> posibleDireccion = this.direccionesRepository
        .buscarPorLatLong(
            Float.valueOf(direccionInputDto.getLatitud()),
            Float.valueOf(direccionInputDto.getLongitud())
        );

    Optional<Provincia> posibleProvincia =
        this.provinciasRepository.buscarPorNombre(direccionInputDto.getNombreProvincia());

    Direccion direccion;

    if (posibleDireccion.isEmpty() && posibleProvincia.isEmpty()) {
      direccion = this.direccionesService.crear(direccionInputDto);
    } else if (posibleDireccion.isEmpty()) {
      direccion =
          this.direccionesService.crear(direccionInputDto, posibleProvincia.get());
    } else {
      return posibleDireccion.get();
    }

    this.provinciasRepository.guardar(direccion.getBarrio().getCiudad().getProvincia());
    this.direccionesRepository.guardar(direccion);
    return direccion;
  }
}
