package modules.domain.personas.vulnerable;

import java.util.Date;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;
import modules.domain.direccion.Direccion;
import modules.domain.personas.TipoDocumento;
import modules.domain.tarjeta.Tarjeta;

/**
 * Representa una persona vulnerable con nombre, fecha de nacimiento, fecha de registro,
 * domicilio, tipo de documento, número de documento, menores a cargo y tarjeta.
 */

@Getter
@Setter
public class Vulnerable {
  private String nombre;
  private Date fechaNacimiento;
  private Date fechaRegistro;
  private Direccion domicilio;
  private TipoDocumento tipoDocumento;
  private Integer numeroDeDocumento;
  private HashSet<Vulnerable> menoresAcargo; //TODO Set, y esta list en el diagrama, revisar
  private Tarjeta tarjeta;


}
