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

  /** Permite crear una direccion a partir de un input.
   *
   * @param direccionInputDto input de la direccion.
   * @return direccion creada.
   */


  public static Direccion crearCon(DireccionInputDto direccionInputDto) {
    Direccion nuevaDireccion = new Direccion();
    nuevaDireccion.setNombreUbicacion(direccionInputDto.getNombreUbicacion());
    nuevaDireccion.setLongitud(Float.valueOf(direccionInputDto.getLongitud()));
    nuevaDireccion.setLatitud(Float.valueOf(direccionInputDto.getLatitud()));
    nuevaDireccion.setBarrio(new Barrio(direccionInputDto.getNombreBarrio(),
        direccionInputDto.getCalle(),
        Integer.valueOf(direccionInputDto.getNumero()),
        new Ciudad(direccionInputDto.getNombreCiudad(),
            new Provincia(direccionInputDto.getNombreProvincia()))));
    return nuevaDireccion;
  }
}
