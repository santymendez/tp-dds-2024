package models.entities.formulario;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "descripcion")
  private String descripcion;

  @OneToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @ManyToMany
  @JoinTable(name = "respuestas_a_preguntas")
  private List<Respuesta> respuestas;

  @ManyToOne
  @JoinColumn(name = "formulario_id", referencedColumnName = "id")
  private Formulario formulario;

  /*public String respuestaNombre(){
    return respuestas.stream().findFirst(r -> r.getPregunta().getPregunta() == "Nombre")
    .getRespuestaTextoLibre();
  }
  */
}
