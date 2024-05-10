package modules.domain.empresa;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa a las empresas asociadas en el sistema.
 */

//TODO me parece que las empresas asociadas son las personas juridicas

@Getter
@Setter
public class EmpresaAsociada {
  private List<Oferta> ofertas;
}
