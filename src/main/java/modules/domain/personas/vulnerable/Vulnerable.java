package modules.domain.personas.vulnerable;

import java.time.LocalDate;
import java.util.Date;
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
@Setter //TODO me parece que podemos poner setter solo para tarjeta
public class Vulnerable {
  private String nombre;
  private Date fechaNacimiento;
  private LocalDate fechaRegistro;
  private Direccion domicilio;
  private Documento documento;
  private HashSet<Vulnerable> menoresAcargo;
  private Tarjeta tarjeta;

  /**
   * Constructor de vulnerable, sirve para dar de alta.
   */

  public Vulnerable(String nombre, Date fechaNacimiento, Direccion domicilio,
                    Documento documento, HashSet<Vulnerable> menoresAcargo) {
    this.nombre = nombre;
    this.fechaNacimiento = fechaNacimiento;
    this.fechaRegistro = LocalDate.now();
    this.domicilio = domicilio;
    this.documento = documento;
    this.menoresAcargo = menoresAcargo;
    this.tarjeta = null;
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
