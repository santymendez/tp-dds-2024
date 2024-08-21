package models.entities.heladera.vianda;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateAttributeConverter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Representa una vianda que incluye una comida, fecha de donación, heladera, calorías,
 * peso y estado de entrega.
 */

@Getter
@NoArgsConstructor
@Entity
@Table(name = "viandas")
public class Vianda {
  @Id
  @GeneratedValue
  private Long id;

  @Embedded
  private Comida comida;

  @Column(name = "fechaDonacion")
  @Convert(converter = LocalDateAttributeConverter.class)
  private LocalDate fechaDonacion;

  @ManyToOne
  @JoinColumn(name = "colaborador_id")
  private Colaborador colaborador;

  @ManyToOne
  @JoinColumn(name = "heladera_id")
  private Heladera heladera;

  @Column(name = "calorias")
  private Integer calorias;

  @Column(name = "peso")
  private Float peso;

  @Setter
  @Column(name = "estaEntregada", columnDefinition = "SMALLINT")
  private Boolean entregada;

  /**
   * Constructor para la Vianda.
   */

  public Vianda(Comida comida, LocalDate fechaDonacion, Colaborador colaborador,
                 Heladera heladera, Integer calorias, Float peso) {
    this.comida = comida;
    this.fechaDonacion = fechaDonacion;
    this.colaborador = colaborador;
    this.heladera = heladera;
    this.calorias = calorias;
    this.peso = peso;
    this.entregada = false;
  }

}