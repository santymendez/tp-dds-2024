package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.entities.heladera.Heladera;

/**
 * Clase que representa un uso de la tarjeta de un colaborador.
 */

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class UsoTarjetaColaborador {
  @Id
  @GeneratedValue
  private long id;

  @Embedded
  private Apertura apertura;

  @OneToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  /**
   * Instancia la clase de Uso.
   *
   * @param heladera Heladera solicitada para abrir.
   */

  public UsoTarjetaColaborador(Heladera heladera) {
    this.apertura = new Apertura(LocalDateTime.now());
    this.heladera = heladera;
  }
}
