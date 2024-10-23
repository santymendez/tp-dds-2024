package models.entities.personas.vulnerable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;
import models.entities.direccion.Direccion;
import models.entities.personas.documento.Documento;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;

/**
 * Representa una persona vulnerable con nombre, fecha de nacimiento, fecha de registro,
 * domicilio, tipo de documento, número de documento, menores a cargo y tarjeta.
 */

@Setter
@Getter
@Entity
@Table(name = "vulnerables")
public class Vulnerable extends Persistente {
  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "fechaNacimiento", columnDefinition = "DATE", nullable = false)
  private LocalDate fechaNacimiento;

  @ManyToOne
  @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
  private Direccion domicilio;

  @Embedded
  private Documento documento;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "menores_por_vulnerables",
      joinColumns = @JoinColumn(name = "vulnerable_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "menor_id", referencedColumnName = "id"))
  private List<Vulnerable> menoresAcargo;

  /**
   * Constructor de vulnerable, sirve para dar de alta.
   */

  public Vulnerable(String nombre, LocalDate fechaNacimiento, Direccion domicilio,
                    Documento documento, List<Vulnerable> menoresAcargo) {
    this.nombre = nombre;
    this.fechaNacimiento = fechaNacimiento;
    this.domicilio = domicilio;
    this.documento = documento;
    this.menoresAcargo = new ArrayList<>();
    if (menoresAcargo != null) {
      this.menoresAcargo.addAll(menoresAcargo);
    }
  }

  public Vulnerable() {
    this.menoresAcargo = new ArrayList<>();
  }

  public void agregarMenorCargo(Vulnerable menor) {
    this.menoresAcargo.add(menor);
  }
}
