package modules.domain.direccion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ciudad {
    private String nombreCiudad;
    private Barrio barrio;
}
