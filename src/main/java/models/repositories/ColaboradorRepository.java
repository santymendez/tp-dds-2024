package models.repositories;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase de Colaborador para cargar los datos del CSV.
 */
@Getter
@Setter
public class ColaboradorRepository {
  //TODO hacer logica
  private String tipoDocumento;
  private String numeroDocumento;
  private String nombre;
  private String apellido;
  private String email;
  private String fecha;
  private String tipoColaboracion;
  private Integer cantidad;


}
