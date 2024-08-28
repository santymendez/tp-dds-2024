package models.entities.colaboracion;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.converters.LocalDateAttributeConverter;
import models.db.Persistente;
import models.entities.personas.colaborador.Colaborador;


/**
 * Representa una colaboracion dentro del sistema.
 */

@Getter
@Setter
@Entity
@Table(name = "colaboraciones")
public class Colaboracion extends Persistente {

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private TipoColaboracion tipoColaboracion;

  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate fechaColaboracion;

  @ManyToOne
  @JoinColumn(name = "colaborador_id")
  private Colaborador colaborador;

  //TODO ver de embeber las clases

  @Embedded
  private DonacionDinero donacionDinero;

  @Embedded
  private DonacionViandas donacionViandas;

  @Embedded
  private DistribucionViandas distribucionViandas;

  @Embedded
  private DistribucionTarjetas distribucionTarjetas;

  @Embedded
  private ColocacionHeladera colocacionHeladera;

  @Embedded
  private RealizacionOfertas realizarOfertas;

  public Colaboracion() {}
  //Se crea sin datos y se agregan los necesarios con setters

  //============================== Metodos Auxiliares ========================================

  public Integer tiempoActivaHeladera() {
    return this.colocacionHeladera.getHeladera().calcularMesesActiva();
  }

}
