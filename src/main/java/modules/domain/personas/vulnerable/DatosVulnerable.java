package modules.domain.personas.vulnerable;

import java.util.Date;
import java.util.HashSet;
import lombok.Getter;
import modules.domain.direccion.Direccion;
import modules.domain.personas.TipoDocumento;

/**
 * Clase auxiliar para la creacion de vulnerables.
 */

@Getter
public class DatosVulnerable {
  private String nombre;
  private Date fechaNacimiento;
  private Direccion domicilio;
  private TipoDocumento tipoDocumento;
  private Integer numeroDeDocumento;
  private HashSet<Vulnerable> menoresAcargo;

  /**
   * Constructor de los datos del vulnerable, sirve que un colaborador
   * pueda registrar un vulnerable.
   */

  public DatosVulnerable(String nombre, Date fechaNacimiento, Direccion domicilio,
                    TipoDocumento tipoDocumento, Integer numeroDeDocumento,
                    HashSet<Vulnerable> menoresAcargo) {
    this.nombre = nombre;
    this.fechaNacimiento = fechaNacimiento;
    this.domicilio = domicilio;
    this.tipoDocumento = tipoDocumento;
    this.numeroDeDocumento = numeroDeDocumento;
    this.menoresAcargo = menoresAcargo;
  }
}
