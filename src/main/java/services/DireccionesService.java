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

    float longitud = 0f;
    float latitud = 0f;

    if (direccionInputDto.getLongitud() != null && direccionInputDto.getLatitud() != null) {
      longitud = Float.parseFloat(direccionInputDto.getLongitud());
      latitud = Float.parseFloat(direccionInputDto.getLatitud());

      Optional<Direccion> posibleDireccion =
          this.direccionesRepository.buscarPorLatLong(latitud, longitud);

      if (posibleDireccion.isPresent()) {
        return posibleDireccion.get();
      }
    }

    Direccion direccion;

    if (direccionInputDto.getProvincia() != null) {
      Optional<Provincia> provincia =
          this.provinciasRepository
              .buscarPorId(Long.parseLong(direccionInputDto.getProvincia()), Provincia.class);
      direccion = FactoryDireccion.crearCon(direccionInputDto, provincia.get());
    } else {
      direccion = FactoryDireccion.crearCon(direccionInputDto);
    }

    this.direccionesRepository.guardar(direccion);
    return direccion;
  }

}
