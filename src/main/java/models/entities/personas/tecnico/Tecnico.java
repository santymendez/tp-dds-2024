package models.entities.personas.tecnico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.entities.direccion.Ciudad;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.documento.Documento;

/**
 * Representa a un técnico en el sistema.
 * Un técnico tiene un nombre, apellido, tipo y número de documento, CUIL, medio de contacto
 * y área de cobertura.
 */

@Getter
@Setter //Modificacion de tecnicos.
@AllArgsConstructor //Dar de alta tecnicos.
public class Tecnico {
  private String nombre;
  private String apellido;
  private Documento documento;
  private Integer cuil;
  private Contacto contacto;
  private Ciudad areaDeCobertura;

  
}
