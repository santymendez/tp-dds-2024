package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.EntidadPersistente;
import models.entities.heladera.Heladera;

import javax.persistence.*;

/**
 * Clase que representa un uso de la tarjeta de un colaborador.
 */

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class UsoTarjetaColaborador extends EntidadPersistente {

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
