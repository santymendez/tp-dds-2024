package modules.domain.personas.tecnico;

import lombok.Getter;
import lombok.Setter;
import modules.domain.direccion.Provincia;
import modules.domain.personas.TipoDocumento;
import modules.domain.personas.contacto.MedioContacto;

/**
 * Representa a un técnico en el sistema.
 * Un técnico tiene un nombre, apellido, tipo y número de documento, CUIL, medio de contacto
 * y área de cobertura.
 */

@Getter
@Setter
public class Tecnico {
  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private Integer numeroDeDocumento;
  private Integer cuil;
  private MedioContacto medioContacto;
  private Provincia areaDeCobertura;
}
