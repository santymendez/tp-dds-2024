package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase para el output de las ofertas.
 */

@Data
@AllArgsConstructor
public class OfertaDto {
    private String nombre;
    private String puntosNecesarios;
    private String imagenIlustrativa;
    private String descripcion;
    private String ofertante;
}
