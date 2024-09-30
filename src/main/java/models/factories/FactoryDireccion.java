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
   * @param provincia la provincia de la direccion.
   * @return la direccion creada.
   */

  public static Direccion crearCon(DireccionInputDto direccionInputDto, Provincia provincia) {
    Direccion nuevaDireccion = new Direccion();
    nuevaDireccion.setNombreUbicacion(direccionInputDto.getNombreUbicacion());
    nuevaDireccion.setLongitud(Float.valueOf(direccionInputDto.getLongitud()));
    nuevaDireccion.setLatitud(Float.valueOf(direccionInputDto.getLatitud()));
    nuevaDireccion.setBarrio(new Barrio(direccionInputDto.getNombreBarrio(),
        direccionInputDto.getCalle(),
        Integer.valueOf(direccionInputDto.getNumero()),
        new Ciudad(direccionInputDto.getNombreCiudad(),
            provincia)));

    return nuevaDireccion;
  }
}
