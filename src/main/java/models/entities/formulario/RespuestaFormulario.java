package models.entities.formulario;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;

/**
 * Representa las respuestas de los contribuidores a la totalidad de un formulario.
 * Tiene un nombre, la descripcion, las respuestas y el formulario al que responde.
 */

@Getter
@Setter
@Entity
@Table(name = "respuestas_formularios")
public class RespuestaFormulario extends Persistente {
  @ManyToOne
  @JoinColumn(name = "formulario_id", referencedColumnName = "id", nullable = false)
  private Formulario formulario;

  @OneToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "respuestaFormulario_id", referencedColumnName = "id", nullable = false)
  private List<Respuesta> respuestas;

  public RespuestaFormulario() {
    this.respuestas = new ArrayList<>();
  }

  public void agregarRespuesta(Respuesta respuesta) {
    this.respuestas.add(respuesta);
  }

}
