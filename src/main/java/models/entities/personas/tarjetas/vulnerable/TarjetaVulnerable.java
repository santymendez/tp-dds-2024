package models.entities.personas.tarjetas.vulnerable;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.vulnerable.Vulnerable;

/**
 * Representa una tarjeta con un código, cantidad de usos, registros de
 * uso e información de registro.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tarjetas_vulnerables")
public class TarjetaVulnerable {
  @Id
  private String codigo;

  //TODO este no hereda por que ya tiene un id
  // habria que tambien ponerle para la baja logica y la fecha ???

  @Column(name = "cantUsosMaxima")
  private Integer cantidadDeUsosMaxima;

  @OneToMany
  @JoinColumn(name = "registroUsos_id")
  private List<UsoTarjetaVulnerable> usoTarjetaVulnerables;

  @Transient
  private InformacionRegistro informacionRegistro;

  @ManyToOne
  @JoinColumn(name = "vulnerable_id", referencedColumnName = "id")
  private Vulnerable vulnerable;

  /**
   * Constructor de la clase Tarjeta.
   *
   * @param colaborador Es el colaborador que distribuye la tarjeta y registra al vulnerable.
   * @param vulnerable Es el vulnerable que recibe la tarjeta y es registrado.
   */

  public TarjetaVulnerable(Colaborador colaborador, Vulnerable vulnerable) {
    this.vulnerable = vulnerable;
    this.cantidadDeUsosMaxima = this.calcularUsos();
    this.informacionRegistro = new InformacionRegistro(colaborador, vulnerable);
    this.codigo = this.generarCodigoAlfanumerico();
  }

  /**
   * Metodo que comprueba si la tarjeta puede utilizarse.
   *
   * @param heladera Es la heladera donde quiere utilizarse la tarjeta.
   * @return Si la tarjeta puede utilizarse.
   */

  public boolean puedeUtilizarse(Heladera heladera) {
    if (!heladera.getModAlmacenamiento().tieneViandas()) {
      throw new RuntimeException("La heladera no tiene viandas");
    }
    if (usoTarjetaVulnerables.size() == cantidadDeUsosMaxima) {
      throw new RuntimeException("Llegaste al limite de usos diarios");
    }
    return true;
  }

  // =========================== Metodos Auxiliares ===========================

  private Integer calcularUsos() {
    return 4 + 2 * this.vulnerable.getMenoresAcargo().size();
  }

  private String generarCodigoAlfanumerico() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 11);
  }
}

