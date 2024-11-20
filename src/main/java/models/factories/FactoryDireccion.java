package models.factories;

import dtos.DireccionInputDto;
import models.entities.direccion.Barrio;
import models.entities.direccion.Ciudad;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import java.util.StringJoiner;

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

    if (direccionInputDto.getBarrio() != null || direccionInputDto.getNumero() != null
        || direccionInputDto.getCalle() != null || direccionInputDto.getCiudad() != null) {

      Barrio barrio = new Barrio();

      if (direccionInputDto.getBarrio() != null) {
        barrio.setNombreBarrio(direccionInputDto.getBarrio());
      }

      if (direccionInputDto.getCalle() != null) {
        direccionInputDto.setCalle(direccionInputDto.getCalle());
      }

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

    if (nuevaDireccion.getBarrio() != null) {
      if (nuevaDireccion.getBarrio().getCiudad() != null) {
        nuevaDireccion.getBarrio().getCiudad().setProvincia(provincia);
      } else {
        Ciudad ciudad = new Ciudad();
        ciudad.setProvincia(provincia);
        nuevaDireccion.getBarrio().setCiudad(ciudad);
      }
    }

    return nuevaDireccion;
  }

  private static String crearNombreUbicacion(Barrio barrio) {
    StringJoiner nombreUbicacion = new StringJoiner(", ");

    if (barrio.getCalle() != null && barrio.getNumero() != null) {
      nombreUbicacion.add(barrio.getCalle() + " " + barrio.getNumero());
    }
    if (barrio.getNombreBarrio() != null) {
      nombreUbicacion.add(barrio.getNombreBarrio());
    }
    if (barrio.getCiudad() != null) {
      if (barrio.getCiudad().getNombreCiudad() != null) {
        nombreUbicacion.add(barrio.getCiudad().getNombreCiudad());
      }
      if (barrio.getCiudad().getProvincia() != null) {
        nombreUbicacion.add(barrio.getCiudad().getProvincia().getNombreProvincia());
      }
    }

    return nombreUbicacion.toString();
  }

}
