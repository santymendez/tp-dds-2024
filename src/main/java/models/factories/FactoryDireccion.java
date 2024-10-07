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
    if (direccionInputDto.getNombreUbicacion() != null) {
      nuevaDireccion.setNombreUbicacion(direccionInputDto.getNombreUbicacion());
    }
    if (direccionInputDto.getLongitud() != null) {
      nuevaDireccion.setLongitud(Float.valueOf(direccionInputDto.getLongitud()));
    }
    if (direccionInputDto.getLatitud() != null) {
      nuevaDireccion.setLatitud(Float.valueOf(direccionInputDto.getLatitud()));
    }

    if (direccionInputDto.getBarrio() != null || direccionInputDto.getNumero() != null
        || direccionInputDto.getCalle() != null
        || direccionInputDto.getCiudad() != null) {
      Barrio barrio = new Barrio();
      barrio.setNombreBarrio(direccionInputDto.getBarrio());
      barrio.setCalle(direccionInputDto.getCalle());
      barrio.setNumero(Integer.parseInt(direccionInputDto.getNumero()));
      if (direccionInputDto.getCiudad() != null) {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombreCiudad(direccionInputDto.getCiudad());
        barrio.setCiudad(ciudad);
      }
      nuevaDireccion.setBarrio(barrio);
    }

    return nuevaDireccion;
  }

  public static Direccion crearCon(DireccionInputDto direccionInputDto, Provincia provincia) {
    Direccion nuevaDireccion = new Direccion();
    if (direccionInputDto.getNombreUbicacion() != null) {
      nuevaDireccion.setNombreUbicacion(direccionInputDto.getNombreUbicacion());
    }
    if (direccionInputDto.getLongitud() != null) {
      nuevaDireccion.setLongitud(Float.valueOf(direccionInputDto.getLongitud()));
    }
    if (direccionInputDto.getLatitud() != null) {
      nuevaDireccion.setLatitud(Float.valueOf(direccionInputDto.getLatitud()));
    }

    if (direccionInputDto.getBarrio() != null || direccionInputDto.getNumero() != null
        || direccionInputDto.getCalle() != null
        || direccionInputDto.getCiudad() != null) {
      Barrio barrio = new Barrio();
      barrio.setNombreBarrio(direccionInputDto.getBarrio());
      barrio.setCalle(direccionInputDto.getCalle());
      barrio.setNumero(Integer.parseInt(direccionInputDto.getNumero()));
      if (direccionInputDto.getCiudad() != null) {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombreCiudad(direccionInputDto.getCiudad());
        barrio.setCiudad(ciudad);
      }
      barrio.getCiudad().setProvincia(provincia);
      nuevaDireccion.setBarrio(barrio);
    }

    return nuevaDireccion;
  }
}
