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

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "formulario_id", referencedColumnName = "id", nullable = false)
  private Formulario formulario;

  @OneToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @ManyToMany
  @JoinTable(name = "respuestas_por_respuestasFormularios",
      joinColumns = @JoinColumn(name = "respuesta_id",
          referencedColumnName = "id", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "respuestaFormulario_id",
          referencedColumnName = "id", nullable = false))
  private List<Respuesta> respuestas;

}
