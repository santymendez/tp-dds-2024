package models.entities.personas.tarjetas.colaborador;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

/**
 * Clase que representa la tarjeta que posee el Colaborador.
 */

@Getter
public class TarjetaColaborador {
  private final String codigo;
  private final List<UsoTarjetaColaborador> usos;

  public TarjetaColaborador() {
    this.codigo = this.generarCodigoAlfanumerico();
    this.usos = new ArrayList<>();
  }

  private String generarCodigoAlfanumerico() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 11);
  }
}
