package modules.domain.personas.tecnico;

import modules.domain.direccion.Provincia;
import modules.domain.personas.TipoDocumento;

public class Tecnico {
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer numeroDeDocumento;
    private Integer cuil;
    private String medioContacto; //TODO revisar si queremos enum
    private Provincia areaDeCobertura; //TODO revisar si queremos otro tipo
}
