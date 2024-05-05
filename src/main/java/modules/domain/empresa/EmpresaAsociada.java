package modules.domain.empresa;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa a las empresas asociadas en el sistema.
 */

@Getter
@Setter
public class EmpresaAsociada {
  private List<Oferta> ofertas;
}
