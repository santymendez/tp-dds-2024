package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDateTime;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;
import models.entities.heladera.Heladera;

/**
 * Clase que representa un uso de la tarjeta de un colaborador.
 */

@Getter
@Setter
@Entity
@Table(name = "usos_tarjetas_colaboradores")
@NoArgsConstructor
public class UsoTarjetaColaborador extends Persistente {

  @Embedded
  private Apertura apertura;

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  @ManyToOne
  @JoinColumn(name = "tarjetaColaborador_id", referencedColumnName = "id")
  private TarjetaColaborador tarjetaColaborador;

  /**
   * Instancia la clase de Uso.
   *
   * @param heladera Heladera solicitada para abrir.
   */

  public UsoTarjetaColaborador(Heladera heladera, TarjetaColaborador tarjetaColaborador) {
    this.apertura = new Apertura(LocalDateTime.now());
    this.heladera = heladera;
    this.tarjetaColaborador = tarjetaColaborador;
  }
}
