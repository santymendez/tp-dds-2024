package modules.domain.tarjeta;

import java.util.Date;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;
import modules.domain.heladera.Heladera;
import modules.domain.personas.colaborador.Colaborador;
import modules.domain.personas.vulnerable.Vulnerable;

/**
 * Representa una tarjeta con un código, cantidad de usos, registros de
 * uso e información de registro.
 */

@Getter
@Setter
public class Tarjeta {
  //TODO Codigo de la Tarjeta
  private String codigo;
  private Integer cantidadDeUsosMaxima;
  private HashSet<RegistroUso> registroUsos;
  private InformacionRegistro informacionRegistro;

  /**
   * Constructor de la clase Tarjeta.
   *
   * @param colaborador Es el colaborador que distribuye la tarjeta y registra al vulnerable.
   * @param vulnerable Es el vulnerable que recibe la tarjeta y es registrado.
   */

  public Tarjeta(Colaborador colaborador, Vulnerable vulnerable) {
    //this.codigo = RandomString(11);
    this.cantidadDeUsosMaxima = 4 + 2 * vulnerable.getMenoresAcargo().size();
    this.informacionRegistro = new InformacionRegistro(colaborador, vulnerable);
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
    if (registroUsos.size() == cantidadDeUsosMaxima) {
      throw new RuntimeException("Llegaste al limite de usos diarios");
    }
    return true;
  }
}
