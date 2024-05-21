package modules.bulk.load;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import modules.domain.colaboracion.Colaboracion;
import modules.domain.personas.colaborador.Colaborador;
import modules.email.sender.EmailSender;

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
