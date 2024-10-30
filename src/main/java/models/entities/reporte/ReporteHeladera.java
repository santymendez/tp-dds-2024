package models.entities.reporte;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa el reporte de una heladera.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reportes_heladeras")
public class ReporteHeladera extends Persistente {
  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id", nullable = false)
  private Heladera heladera;

  @Column(name = "fallas")
  private Integer fallas;

  @Column(name = "viandasColocadas")
  private Integer viandasColocadas;

  @Column(name = "viandasRetiradas")
  private Integer viandasRetiradas;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "reportes_por_viandasPorColaborador",
      joinColumns = @JoinColumn(name = "reporte_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "viandasPorColaboradores_id",
          referencedColumnName = "id"))
  private List<ViandasPorColaborador> viandasPorColaboradores;

  @Column(name = "fecha", columnDefinition = "DATE", nullable = false)
  private LocalDate fecha;

  @Column(name = "reportePath", columnDefinition = "TEXT")
  private String path;

  /**
   * Instancia un nuevo reporte para una heladera.
   *
   * @param heladera Heladera del reporte.
   */

  public ReporteHeladera(Heladera heladera) {
    this.heladera = heladera;
    this.fallas = 0;
    this.viandasColocadas = 0;
    this.viandasRetiradas = 0;
    this.viandasPorColaboradores = new ArrayList<>();
    this.fecha = LocalDate.now();
  }

  //=================================== Metodos contadores =========================================

  public void viandaColocada() {
    this.viandasColocadas++;
  }

  public void viandaRetirada() {
    this.viandasRetiradas++;
  }

  public void ocurrioUnaFalla() {
    this.fallas++;
  }

  /**
   * Metodo que permite registrar una nueva donacion en el reporte.
   *
   * @param viandasPorColaborador nueva donacion a registrar.
   */

  public void agregarNuevaColaboracion(ViandasPorColaborador viandasPorColaborador) {
    this.viandasPorColaboradores.add(viandasPorColaborador);
  }

  /**
   * Metodo para buscar a un colaborador que ya haya donado viandas, y
   * sus viandas donadas.
   *
   * @param colaborador colaborador a validar si colaboro.
   * @return la relacion entre el colaborador y sus donaciones.
   */

  public Optional<ViandasPorColaborador> buscarPorColaborador(Colaborador colaborador) {
    return this.viandasPorColaboradores.stream()
        .filter(elem -> elem.getColaborador().equals(colaborador)).findFirst();
  }
}
