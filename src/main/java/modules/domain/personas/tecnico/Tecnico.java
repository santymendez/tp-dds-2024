package modules.domain.personas.tecnico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import modules.domain.direccion.Provincia;
import modules.domain.personas.contacto.Contacto;
import modules.domain.personas.documento.Documento;

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
  private Provincia areaDeCobertura;
}
