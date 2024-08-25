package models.entities.heladera.incidente;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateTimeAttributeConverter;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa los incidentes en las heladeras.
 */

@Getter
@Setter
@Entity
@Table(name = "incidentes")
@NoArgsConstructor
public class Incidente {
  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoIncidente")
  private TipoIncidente tipo;

  @Convert(converter = LocalDateTimeAttributeConverter.class)
  @Column(name = "momento")
  private LocalDateTime momentoIncidente;

  @JoinColumn(name = "heladera_id")
  @ManyToOne
  private Heladera heladera;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoAlerta")
  private TipoEstado tipoAlerta;

  @ManyToOne
  @JoinColumn(name = "colaborador_id")
  private Colaborador colaborador;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  @Column(name = "imagen")
  private String imagen;

  /**
   * Instancia la clase Incidente.
   *
   * @param tipo Tipo de incidente.
   * @param heladera Heladera afectada por el incidente.
   */

  public Incidente(TipoIncidente tipo, Heladera heladera) {
    this.tipo = tipo;
    this.heladera = heladera;
    this.momentoIncidente = LocalDateTime.now();
  }
}