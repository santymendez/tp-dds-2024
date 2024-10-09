package models.entities.personas.tarjetas.vulnerable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.entities.heladera.Heladera;

/**
 * Representa una tarjeta con un código, cantidad de usos, registros de
 * uso e información de registro.
 */

@Getter
@Setter
@Entity
@Table(name = "tarjetas_vulnerables")
public class TarjetaVulnerable {
  @Id
  @Column(name = "id", nullable = false, unique = true)
  private String codigo;

  @Column(name = "activo")
  private Boolean activo;

  @Column(name = "cantUsosMaxima")
  private Integer cantidadDeUsosMaxima;

  @OneToMany(mappedBy = "tarjetaVulnerable",
          cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
          fetch = FetchType.EAGER)
  private List<UsoTarjetaVulnerable> usosTarjetaVulnerables;

  @OneToOne
  @JoinColumn(name = "registroVulnerable_id", referencedColumnName = "id")
  private RegistroVulnerable registroVulnerable;

  /**
   * Constructor de la clase Tarjeta.
  */

  public TarjetaVulnerable(RegistroVulnerable registro) {
    this.registroVulnerable = registro;
    this.cantidadDeUsosMaxima = this.calcularUsos();
    this.codigo = this.generarCodigoAlfanumerico();
    this.usosTarjetaVulnerables = new ArrayList<>();
    this.activo = true;
  }

  /**
   * Constructor vacio de la clase Tarjeta.
   */

  public TarjetaVulnerable() {
    this.codigo = this.generarCodigoAlfanumerico();
    this.usosTarjetaVulnerables = new ArrayList<>();
    this.activo = true;
  }

  /**
   * Metodo que comprueba si la tarjeta puede utilizarse.
   *
   * @param heladera Es la heladera donde quiere utilizarse la tarjeta.
   * @return Si la tarjeta puede utilizarse.
   */

  public boolean puedeUtilizarse(Heladera heladera) {
    if (!heladera.tieneViandas()) {
      throw new RuntimeException("La heladera no tiene viandas");
    }
    if (usosTarjetaVulnerables.size() == cantidadDeUsosMaxima) {
      throw new RuntimeException("Llegaste al limite de usos diarios");
    }
    return true;
  }

  // =========================== Metodos Auxiliares ===========================

  /**
   * Calcula la cantidad de usos que puede tener la tarjeta.
   *
   * @return la cantidad de usos.
   */

  public Integer calcularUsos() {
    if (!this.registroVulnerable.getVulnerable().getMenoresAcargo().isEmpty()) {
      return 4 + 2 * this.registroVulnerable.getVulnerable().getMenoresAcargo().size();
    } else {
      return 4;
    }
  }

  private String generarCodigoAlfanumerico() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 11);
  }

  public void agregarUso(UsoTarjetaVulnerable u) {
    this.usosTarjetaVulnerables.add(u);
    u.setTarjetaVulnerable(this);
  }
}

