package modules.domain.vianda;

import lombok.Getter;
import lombok.Setter;
import modules.domain.heladera.Heladera;

import java.util.Date;

@Getter
@Setter
public class Vianda {
    private Comida comida;
    private Date fechaDonacion;
    //private PersonaFisica colaborador;
    private Heladera heladera;
    private Integer calorias;
    private Float peso;
    private Boolean entregada;
}
