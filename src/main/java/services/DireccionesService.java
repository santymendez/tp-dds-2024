package services;

import dtos.DireccionInputDto;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.factories.FactoryDireccion;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.ProvinciasRepository;

/**
 * Service para las Direcciones.
 */

//TODO REVISAR
public class DireccionesService {

  private final DireccionesRepository direccionesRepository;
  private final ProvinciasRepository provinciasRepository;

  public DireccionesService(DireccionesRepository direccionesRepository,
                            ProvinciasRepository provinciasRepository) {
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

    //Provincia
    String nombreProvincia = direccionInputDto.getNombreProvincia();

    Optional<Direccion> posibleDireccion =
        this.direccionesRepository.buscarPorLatLong(latitud, longitud);

    if (posibleDireccion.isPresent()) {
      return posibleDireccion.get();
    }

    Optional<Provincia> posibleProvincia =
        this.provinciasRepository.buscarPorNombre(nombreProvincia);

    if (posibleProvincia.isEmpty() && nombreProvincia != null) {
      posibleProvincia = Optional.of(new Provincia(nombreProvincia));
      this.provinciasRepository.guardar(posibleProvincia.get());
      return FactoryDireccion.crearCon(direccionInputDto, posibleProvincia.get());
    }

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
