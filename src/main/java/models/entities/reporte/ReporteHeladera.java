package models.entities.reporte;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Entity
@Table(name = "reportes_heladeras")
@NoArgsConstructor
public class ReporteHeladera extends Persistente {
  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  @Column(name = "fallas")
  private Integer fallas;

  @Column(name = "viandasColocadas")
  private Integer viandasColocadas;

  @Column(name = "viandasRetiradas")
  private Integer viandasRetiradas;

  @OneToMany
  @JoinColumn(name = "viandasPorColaborador_id", referencedColumnName = "id")
  private List<ViandasPorColaborador> viandasPorColaboradores;

  @Column(name = "fecha", columnDefinition = "DATE")
  private LocalDate fecha;

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

  //TODO esto iria en un controller

  //  /**
  //   * Busca el colaborador en la lista de ViandasXColaborador, si lo encuentra le agrega
  //   * las viandas que dono, si no crea un nuevo ViandasXColaborador y lo agrega a la lista.
  //   *
  //   * @param colaborador Colaborador que realiza la donación.
  //   * @param viandas Cantidad de viandas donadas.
  //   */

  //  public void colaboracionRealizada(Colaborador colaborador, Integer viandas) {
  //    Optional<ViandasPorColaborador> viandasPorColaborador =
  //        this.buscarPorColaborador(colaborador).findFirst();
  //
  //    if (viandasPorColaborador.isPresent()) {
  //      viandasPorColaborador.get().agregarViandas(viandas);
  //    } else {
  //      ViandasPorColaborador nuevo = new ViandasPorColaborador(colaborador, viandas);
  //      this.viandasPorColaboradores.add(nuevo);
  //    }
  //  }

  /**
   * Metodo que permite registrar una nueva donacion en el reporte.
   *
   * @param viandasPorColaborador nueva donacion a registrar.
   */

  public void agregarNuevaColaboracion(ViandasPorColaborador viandasPorColaborador) {
    this.viandasPorColaboradores.add(viandasPorColaborador);
  }

  /**
   * Metodo que permite agregar mas viandas a las ya donadas por un colaborador
   * en el reporte.
   *
   * @param colaborador colaborador que dono las viandas.
   * @param viandas cantidad de viandas donadas.
   */

  public void agregarMasDonaciones(Colaborador colaborador, Integer viandas) {
    ViandasPorColaborador viandasPorColaborador = this.buscarColaborador(colaborador);
    viandasPorColaborador.agregarViandas(viandas);
  }

  /**
   * Metodo para buscar a un colaborador que ya haya donado viandas, y
   * sus viandas donadas.
   *
   * @param colaborador colaborador a validar si colaboro.
   * @return la relacion entre el colaborador y sus donaciones.
   */

  public ViandasPorColaborador buscarColaborador(Colaborador colaborador) {
    Optional<ViandasPorColaborador> viandasPorColaborador = this.viandasPorColaboradores.stream()
        .filter(elem -> elem.getColaborador().equals(colaborador)).findFirst();

    if (viandasPorColaborador.isPresent()) {
      return viandasPorColaborador.get();
    } else {
      throw new NoSuchElementException("No se encontro el colaborador");
    }
  }
}
