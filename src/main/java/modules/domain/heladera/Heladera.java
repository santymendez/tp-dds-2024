package modules.domain.heladera;

import modules.domain.direccion.Direccion;
import modules.domain.vianda.Vianda;

import java.util.Date;
import java.util.List;

public class Heladera {
    private Direccion direccion;
    private String nombre;
    private Integer capacidadMaximaViandas;
    private List<Vianda> viandas;
    private Date fechaDeCreacion;

    //TODO clases segunda entrega y metodos
    public void agregarVianda(Vianda vianda){
        this.viandas.add(vianda);
    }
}
