package models.entities.personas.tarjetas.vulnerable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.converters.LocalDateAttributeConverter;
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

  @Convert(converter = LocalDateAttributeConverter.class)
  @Column(name = "fechaAlta")
  private LocalDate fechaAlta;

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
   * Constructor por defecto de la tarjeta vulnerable.
   */

  public TarjetaVulnerable() {
    this.codigo = this.generarCodigoAlfanumerico();
    this.usosTarjetaVulnerables = new ArrayList<>();
    this.activo = true;
    this.fechaAlta = LocalDate.now();
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
   */

  public void calcularUsos() {
    if (!this.registroVulnerable.getVulnerable().getMenoresAcargo().isEmpty()) {
      this.cantidadDeUsosMaxima =
          4 + 2 * this.registroVulnerable.getVulnerable().getMenoresAcargo().size();
    } else {
      this.cantidadDeUsosMaxima = 4;
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

