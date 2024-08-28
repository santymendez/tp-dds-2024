package models.entities.personas.tarjetas.colaborador;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

/**
 * Clase que representa la tarjeta que posee el Colaborador.
 */

@Getter
@Entity
@Table (name = "tarjetas_colaboradores")
public class TarjetaColaborador {
  @Id
  private final String codigo;
  //TODO este no hereda por que ya tiene un id
  // habria que tambien ponerle para la baja logica y la fecha ???

  @OneToMany
  @JoinColumn(name = "uso_id")
  private final List<UsoTarjetaColaborador> usos;

  public TarjetaColaborador() {
    this.codigo = this.generarCodigoAlfanumerico();
    this.usos = new ArrayList<>();
  }

  private String generarCodigoAlfanumerico() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 11);
  }
}
