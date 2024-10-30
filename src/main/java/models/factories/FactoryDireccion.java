package models.factories;

import dtos.DireccionInputDto;
import models.entities.direccion.Barrio;
import models.entities.direccion.Ciudad;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;

/**
 * Clase Factory de direcciones.
 */

public class FactoryDireccion {

  /**
   * Crea una direccion con una provincia ya existente.
   *
   * @param direccionInputDto el input de la direccion.
   * @return la direccion creada.
   */

  public static Direccion crearCon(DireccionInputDto direccionInputDto) {
    Direccion nuevaDireccion = new Direccion();

    if (direccionInputDto.getLongitud() != null) {
      nuevaDireccion.setLongitud(direccionInputDto.getLongitud());
    }
    if (direccionInputDto.getLatitud() != null) {
      nuevaDireccion.setLatitud(direccionInputDto.getLatitud());
    }

    if (direccionInputDto.getBarrio() != null
        || direccionInputDto.getNumero() != null
        || direccionInputDto.getCalle() != null
        || direccionInputDto.getCiudad() != null) {
      Barrio barrio = new Barrio();
      barrio.setNombreBarrio(direccionInputDto.getBarrio());
      barrio.setCalle(direccionInputDto.getCalle());
      if (direccionInputDto.getNumero() != null) {
        barrio.setNumero(direccionInputDto.getNumero());
      }
      if (direccionInputDto.getCiudad() != null) {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombreCiudad(direccionInputDto.getCiudad());
        barrio.setCiudad(ciudad);
      }
      nuevaDireccion.setBarrio(barrio);

      if (direccionInputDto.getNombreUbicacion() != null) {
        nuevaDireccion.setNombreUbicacion(direccionInputDto.getNombreUbicacion());
      } else {
        nuevaDireccion.setNombreUbicacion(crearNombreUbicacion(barrio));
      }
      
    }

    return nuevaDireccion;
  }

  /**
   * Crea una direccion con una provincia ya existente.
   *
   * @param direccionInputDto el input de la direccion.
   * @param provincia la provincia de la direccion.
   * @return la direccion creada.
   */

  public static Direccion crearCon(DireccionInputDto direccionInputDto, Provincia provincia) {
    Direccion nuevaDireccion = crearCon(direccionInputDto);
    nuevaDireccion.getBarrio().getCiudad().setProvincia(provincia);

    return nuevaDireccion;
  }

  private static String crearNombreUbicacion(Barrio barrio) {
    return barrio.getCalle() + " " + barrio.getNumero() + ", " + barrio.getNombreBarrio() + ", "
            + barrio.getCiudad().getNombreCiudad();
  }

}
