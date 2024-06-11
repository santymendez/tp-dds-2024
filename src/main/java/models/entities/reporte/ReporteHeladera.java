package models.entities.reporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa el reporte de una heladera.
 */

@Getter
@Setter
public class ReporteHeladera {
  private Heladera heladera;
  private Integer fallas;
  private Integer viandasColocadas;
  private Integer viandasRetiradas;
  private List<ViandasPorColaborador> viandasPorColaboradores;

  /**
   * Instancia un nuevo reporte para una heladera.
   *
   * @param heladera Heladera del reporte.
   */

  public ReporteHeladera(Heladera heladera) {
    this.heladera = heladera;
    this.viandasPorColaboradores = new ArrayList<>();
    this.nuevoReporteSemanal();
  }

  //TODO Cronjob para realizarlo una vez por semana
  public void imprimirReporte() {
    //IMPRIMIR
    this.nuevoReporteSemanal();
  }

  /**
   * Método que reinicia los datos cada semana.
   */

  public void nuevoReporteSemanal() {
    this.setFallas(0);
    this.setViandasColocadas(0);
    this.setViandasRetiradas(0);
    this.viandasPorColaboradores.clear();
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
   * Busca el colaborador en la lista de ViandasXColaborador, si lo encuentra le agrega
   * las viandas que dono, si no crea un nuevo ViandasXColaborador y lo agrega a la lista.
   *
   * @param colaborador Colaborador que realiza la donación.
   * @param viandas Cantidad de viandas donadas.
   */

  public void colaboracionRealizada(Colaborador colaborador, Integer viandas) {
    Optional<ViandasPorColaborador> viandasPorColaborador =
        this.buscarPorColaborador(colaborador).findFirst();

    if (viandasPorColaborador.isPresent()) {
      viandasPorColaborador.get().agregarViandas(viandas);
    } else {
      ViandasPorColaborador nuevo = new ViandasPorColaborador(colaborador, viandas);
      this.viandasPorColaboradores.add(nuevo);
    }
  }

  public Stream<ViandasPorColaborador> buscarPorColaborador(Colaborador colaborador) {
    return this.viandasPorColaboradores.stream()
        .filter(elem -> elem.getColaborador().equals(colaborador));
  }

}
