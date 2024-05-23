package modules.domain.personas.vulnerable;

import java.time.LocalDate;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;
import modules.domain.direccion.Direccion;
import modules.domain.heladera.Heladera;
import modules.domain.personas.documento.Documento;
import modules.domain.tarjeta.Tarjeta;

/**
 * Representa una persona vulnerable con nombre, fecha de nacimiento, fecha de registro,
 * domicilio, tipo de documento, número de documento, menores a cargo y tarjeta.
 */

@Getter
public class Vulnerable {
  private final String nombre;
  private final LocalDate fechaNacimiento;
  private final LocalDate fechaRegistro;
  private final Direccion domicilio;
  private final Documento documento;
  private final HashSet<Vulnerable> menoresAcargo;
  @Setter
  private Tarjeta tarjeta;

  /**
   * Constructor de vulnerable, sirve para dar de alta.
   */

  public Vulnerable(String nombre, LocalDate fechaNacimiento, Direccion domicilio,
                    Documento documento, HashSet<Vulnerable> menoresAcargo) {
    this.nombre = nombre;
    this.fechaNacimiento = fechaNacimiento;
    this.fechaRegistro = LocalDate.now();
    this.domicilio = domicilio;
    this.documento = documento;
    this.menoresAcargo = menoresAcargo;
  }

  /**
   * Metodo que permite al vulnerable utilizar su tarjeta.
   *
   * @param heladera Es la heladera donde se quiere utilizar la tarjeta.
   */

  public boolean puedeUsarTarjeta(Heladera heladera) {
    return this.tarjeta.puedeUtilizarse(heladera);
  }

}
