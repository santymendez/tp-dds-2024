package modules.domain.direccion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Direccion {
    private String ubicacion;
    private Float longitud;
    private Float latitud;
    private Provincia provincia;
}
