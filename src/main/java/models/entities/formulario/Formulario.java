package models.entities.formulario;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa un formulario. Tiene un listado de preguntas.
 */

@Getter
@Setter
@Entity
@Table(name = "formularios")
public class Formulario extends Persistente {
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "formulario_id", referencedColumnName = "id")
  private List<Pregunta> preguntas;

  @Column(name = "nombre", nullable = false)
  private String nombre;
}
