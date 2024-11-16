package models.entities.reporte;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.converters.LocalDateAttributeConverter;
import models.db.Persistente;

/**
 * Clase Reporte Semanal, en la cual se tiene todos los reportes de las heladeras.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reportes_semanales")
public class ReporteSemanal extends Persistente {
  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "reporteSemanal_id", referencedColumnName = "id")
  private List<ReporteHeladera> reportesHeladeras;

  @Column(name = "pathReporte", columnDefinition = "TEXT")
  private String path;

  @Column(name = "nombre")
  private String nombre;

  @Convert(converter = LocalDateAttributeConverter.class)
  @Column(name = "fecha")
  private LocalDate fecha;

  /**
   * Reporte de la Semana.
   *
   * @param path la ruta del reporte.
   * @param reportesHeladeras los reportes de cada heladera de dicha semana.
   */

  public ReporteSemanal(String path, List<ReporteHeladera> reportesHeladeras) {
    this.reportesHeladeras = reportesHeladeras;
    this.path = path;
    this.fecha = LocalDate.now();
    this.nombre = "reporte-semana-" + this.fecha;
  }
}
