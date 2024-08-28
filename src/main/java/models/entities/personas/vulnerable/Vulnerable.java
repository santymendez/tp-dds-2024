package models.entities.personas.vulnerable;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.db.Persistente;
import models.entities.direccion.Direccion;
import models.entities.personas.documento.Documento;

/**
 * Representa una persona vulnerable con nombre, fecha de nacimiento, fecha de registro,
 * domicilio, tipo de documento, número de documento, menores a cargo y tarjeta.
 */

@Getter
@Entity
@NoArgsConstructor
@Table(name = "vulnerables")
public class Vulnerable extends Persistente {
  @Column(name = "nombre")
  private String nombre;

  @Column(name = "fechaNacimiento", columnDefinition = "DATE", nullable = false)
  private LocalDate fechaNacimiento;

  @Column(name = "fechaRegistro", columnDefinition = "DATE", nullable = false)
  private LocalDate fechaRegistro;

  @Transient
  private Direccion domicilio;

  @Transient
  private Documento documento;

  @OneToMany
  @JoinColumn(name = "menor_id")
  private List<Vulnerable> menoresAcargo;

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

}
