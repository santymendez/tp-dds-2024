package models.entities.personas.colaborador.canje;

import java.awt.Image;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa las ofertas de las empresas asociadas.
 */

@Getter
@Setter
public class Oferta {
  private String nombre;
  private Float puntosNecesarios;
  private Image imagenIlustrativa;
}
