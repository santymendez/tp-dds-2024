package services;

import dtos.DireccionInputDto;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.factories.FactoryDireccion;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;

/**
 * Service para las Direcciones.
 */

//TODO REVISAR
public class DireccionesService {

  private final DireccionesRepository direccionesRepository;
  private final GenericRepository provinciasRepository;

  public DireccionesService(DireccionesRepository direccionesRepository,
                            GenericRepository provinciasRepository) {
    this.direccionesRepository = direccionesRepository;
    this.provinciasRepository = provinciasRepository;
  }

  /**
   * Crea una direccion.
   *
   * @param direccionInputDto input de la direccion.
   * @return direccion creada.
   */

  public Direccion crear(DireccionInputDto direccionInputDto) {
    // Verifica longitud y latitud
    if (direccionInputDto.getLongitud() == null || direccionInputDto.getLatitud() == null) {
      throw new IllegalArgumentException("Longitud y latitud no pueden ser nulos.");
    }

    float longitud;
    float latitud;

    try {
      longitud = Float.parseFloat(direccionInputDto.getLongitud());
      latitud = Float.parseFloat(direccionInputDto.getLatitud());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Formato inválido para longitud o latitud.", e);
    }

    Optional<Direccion> posibleDireccion =
        this.direccionesRepository.buscarPorLatLong(latitud, longitud);

    if (posibleDireccion.isPresent()) {
      return posibleDireccion.get();
    }

    if (direccionInputDto.getProvincia() == null) {
      throw new IllegalArgumentException("La provincia no puede ser nula.");
    }

    Optional<Provincia> provincia =
        this.provinciasRepository
            .buscarPorId(Long.parseLong(direccionInputDto.getProvincia()), Provincia.class);

    if (provincia.isEmpty()) {
      throw new IllegalArgumentException("Provincia no encontrada.");
    }

    Direccion direccion = FactoryDireccion.crearCon(direccionInputDto, provincia.get());
    this.direccionesRepository.guardar(direccion);
    return direccion;
  }

}
