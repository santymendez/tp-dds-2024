package models.entities.heladera.incidente;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateTimeAttributeConverter;
import models.db.Persistente;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.TipoEstado;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa los incidentes en las heladeras.
 */

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "incidentes")
public class Incidente extends Persistente {

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoIncidente", nullable = false)
  private TipoIncidente tipo;

  @Convert(converter = LocalDateTimeAttributeConverter.class)
  @Column(name = "momento")
  private LocalDateTime momentoIncidente;

  @ManyToOne
  @JoinColumn(name = "heladera_id", nullable = false)
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

  @PrePersist
  protected void onInsert() {
    if (this.momentoIncidente == null) {
      this.momentoIncidente = LocalDateTime.now();
    }
  }
}