package models.entities.personas.tarjetas.colaborador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa la tarjeta que posee el Colaborador.
 */

@Getter
@Setter
@Entity
@Table (name = "tarjetas_colaboradores")
public class TarjetaColaborador {
  @Id
  private final String codigo;

  @Column(name = "activo")
  private Boolean activo;

  @Column(name = "fechaAlta", columnDefinition = "DATE")
  private LocalDate fechaAlta;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
  @JoinColumn(name = "uso_id", referencedColumnName = "id")
  private final List<UsoTarjetaColaborador> usos;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  /**
   * Metodo constructor de la tarjeta del colaborador.
   */

  public TarjetaColaborador() {
    this.codigo = this.generarCodigoAlfanumerico();
    this.usos = new ArrayList<>();
    this.fechaAlta = LocalDate.now();
    this.activo = true;
  }

  private String generarCodigoAlfanumerico() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 11);
  }
}
