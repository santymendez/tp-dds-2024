package models.entities.personas.vulnerable;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateAttributeConverter;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.personas.documento.Documento;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;

import javax.persistence.*;

/**
 * Representa una persona vulnerable con nombre, fecha de nacimiento, fecha de registro,
 * domicilio, tipo de documento, número de documento, menores a cargo y tarjeta.
 */

@Getter
@Entity
@NoArgsConstructor
@Table(name = "vulnerables")
public class Vulnerable {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Convert(converter = LocalDateAttributeConverter.class)
  @Column(name = "fechaNacimiento")
  private LocalDate fechaNacimiento;

  @Convert(converter = LocalDateAttributeConverter.class)
  @Column(name = "fechaRegistro")
  private LocalDate fechaRegistro;

  @Transient
  private Direccion domicilio;

  @Transient
  private Documento documento;

  @OneToMany
  @Column(name = "menoresAcargo")
  private List<Vulnerable> menoresAcargo;

  @Setter
  @Transient
  private TarjetaVulnerable tarjeta;

  /**
   * Constructor de vulnerable, sirve para dar de alta.
   */

  public Vulnerable(String nombre, LocalDate fechaNacimiento, Direccion domicilio,
                    Documento documento, List<Vulnerable> menoresAcargo) {
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
