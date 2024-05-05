package modules.domain.personas.vulnerable;

import modules.domain.direccion.Direccion;
import modules.domain.personas.TipoDocumento;
import modules.domain.tarjeta.Tarjeta;

import java.util.Date;
import java.util.Set;

public class Vulnerable {
    private String nombre;
    private Date fechaNacimiento;
    private Date fechaRegistro;
    private Direccion domicilio;
    private TipoDocumento tipoDocumento;
    private Integer numeroDeDocumento;
    private Set<Vulnerable> menoresACargo; //TODO Aca puse set, y esta list en el diagrama, revisar
    private Tarjeta tarjeta;
}
