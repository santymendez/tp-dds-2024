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
    Float longitud = Float.parseFloat(direccionInputDto.getLongitud());
    Float latitud = Float.parseFloat(direccionInputDto.getLatitud());

    Optional<Direccion> posibleDireccion =
        this.direccionesRepository.buscarPorLatLong(latitud, longitud);

    if (posibleDireccion.isPresent()) {
      return posibleDireccion.get();
    }

    Optional<Provincia> provincia =
            this.provinciasRepository.buscarPorId(Long.parseLong(direccionInputDto.getProvincia()), Provincia.class);

    //ASUMIMOS QUE EL QUE CARGA LA INFORMACION NO ES PELOTUDO

    Direccion direccion = FactoryDireccion.crearCon(direccionInputDto, provincia.get());
    this.direccionesRepository.guardar(direccion);
    return direccion;
  }
}
