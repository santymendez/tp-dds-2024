package models.entities.personas.tarjetas.vulnerable;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
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
public class TarjetaVulnerable {
  private String codigo;
  private Integer cantidadDeUsosMaxima;
  private List<RegistroUso> registroUsos;
  private InformacionRegistro informacionRegistro;
  private Vulnerable vulnerable;

  /**
   * Constructor de la clase Tarjeta.
   *
   * @param colaborador Es el colaborador que distribuye la tarjeta y registra al vulnerable.
   * @param vulnerable Es el vulnerable que recibe la tarjeta y es registrado.
   */

  public TarjetaVulnerable(Colaborador colaborador, Vulnerable vulnerable) {
    this.cantidadDeUsosMaxima = this.calcularUsosPara(vulnerable);
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
    if (registroUsos.size() == cantidadDeUsosMaxima) {
      throw new RuntimeException("Llegaste al limite de usos diarios");
    }
    return true;
  }

  // =========================== Metodos Auxiliares ===========================

  private Integer calcularUsosPara(Vulnerable vulnerable) {
    return 4 + 2 * vulnerable.getMenoresAcargo().size();
  }

  private String generarCodigoAlfanumerico() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 11);
  }
}

