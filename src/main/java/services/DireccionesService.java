package services;

import dtos.DireccionInputDto;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.colaborador.Colaborador;
import models.factories.FactoryDireccion;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.GenericRepository;

/**
 * Service para las Direcciones.
 */

public class DireccionesService {

  private final DireccionesRepository direccionesRepository;
  private final GenericRepository genericRepository;

  public DireccionesService(DireccionesRepository direccionesRepository,
                            GenericRepository genericRepository) {
    this.direccionesRepository = direccionesRepository;
    this.genericRepository = genericRepository;
  }

  /**
   * Crea una direccion.
   *
   * @param direccionInputDto input de la direccion.
   * @return direccion creada.
   */

  public Direccion crear(DireccionInputDto direccionInputDto) {

    if (direccionInputDto.getLongitud() != null
        && direccionInputDto.getLatitud() != null) {
      Float longitud = Float.parseFloat(direccionInputDto.getLongitud());
      Float latitud = Float.parseFloat(direccionInputDto.getLatitud());

      Optional<Direccion> posibleDireccion =
          this.direccionesRepository.buscarPorLatLong(latitud, longitud);

      if (posibleDireccion.isPresent()) {
        return posibleDireccion.get();
      }
    }

    Direccion direccion;

    if (direccionInputDto.getProvincia() != null) {
      Optional<Provincia> provincia =
          this.genericRepository
              .buscarPorId(Long.parseLong(direccionInputDto.getProvincia()), Provincia.class);
      direccion = FactoryDireccion.crearCon(direccionInputDto, provincia.get());
    } else {
      direccion = FactoryDireccion.crearCon(direccionInputDto);
    }

    this.direccionesRepository.guardar(direccion);
    return direccion;
  }

  /**
   * Crea una direccion y se la asigna al colaborador.
   *
   * @param direccionInputDto el dto de la direccion.
   * @param colaborador un colaborador.
   * @return una direccion.
   */

  public Direccion crearAsignar(DireccionInputDto direccionInputDto, Colaborador colaborador) {
    //Se crea la direccion
    Direccion direccion = this.crear(direccionInputDto);

    //Se asigna la direccion
    colaborador.setDireccion(direccion);
    this.genericRepository.modificar(colaborador);

    return direccion;
  }

}
