package modules.domain.personas.colaborador.reconocimiento.formula;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;
import lombok.Setter;
import modules.domain.colaboracion.Colaboracion;
import modules.domain.colaboracion.TipoColaboracion;

/**
 * Representa una formula de cálculo de puntos.
 */

public class Formula {
  @Setter
  private Float coefPesosDonados;
  @Setter
  private Float coefViandasDonadas;
  @Setter
  private Float coefViandasDistribuidas;
  @Setter
  private Float coefTarjetasRepartidas;
  @Setter
  private Float coefHeladerasActivas;

  private final EnumMap<TipoColaboracion, Function<Colaboracion, Float>> mapCalculos;

  /**
   * Constructor para generar el map de cada cálculo de colaboración.
   */

  public Formula() {
    this.mapCalculos = new EnumMap<>(TipoColaboracion.class);
    this.mapCalculos.put(TipoColaboracion.DONAR_DINERO, this::calcPesosDonados);
    this.mapCalculos.put(TipoColaboracion.DONAR_VIANDA, this::calcViandasDonadas);
    this.mapCalculos.put(TipoColaboracion.DISTRIBUIR_VIANDA, this::calcDistribuirViandas);
    this.mapCalculos.put(TipoColaboracion.ENTREGAR_TARJETA, this::calcTarjetasRepartidas);
    this.mapCalculos.put(TipoColaboracion.COLOCAR_HELADERA, this::calcHeladerasActivas);
  }

  public Float calcularPuntosDe(Colaboracion colaboracion) {
    return mapCalculos.get(colaboracion.getTipoColaboracion()).apply(colaboracion);
  }

  private Float calcPesosDonados(Colaboracion colaboracion) {
    return colaboracion.getMonto() * coefPesosDonados;
  }

  private Float calcViandasDonadas(Colaboracion colaboracion) {
    return colaboracion.cantViandasDonadas() * this.coefViandasDonadas;
  }

  private Float calcDistribuirViandas(Colaboracion colaboracion) {
    return colaboracion.getCantViandasDistribuidas() * this.coefViandasDistribuidas;
  }

  private Float calcTarjetasRepartidas(Colaboracion colaboracion) {
    return colaboracion.cantTarjetasEntregadas() * this.coefTarjetasRepartidas;
  }

  private Float calcHeladerasActivas(Colaboracion colaboracion) {
    return colaboracion.tiempoActivaHeladera() * this.coefHeladerasActivas;
  }

  /**
   * Calcula los puntos de cada colaboración que recibe como parámetro.
   *
   * @param colaboracion es la colaboración a la que se le calculara los puntos correspondientes
   * @return Float
   */

  // alternativa 2 = instancia de alguna manera que podamos calcular
  // independientemente del tipo
  public Float calcularPuntosDe_alt(Colaboracion colaboracion) {
    float total = 0f;
    total += colaboracion.getMonto() * this.coefPesosDonados;
    total += colaboracion.cantViandasDonadas() * this.coefViandasDonadas;
    total += colaboracion.getCantViandasDistribuidas() * this.coefViandasDistribuidas;
    total += colaboracion.cantTarjetasEntregadas() * this.coefTarjetasRepartidas;
    total += colaboracion.tiempoActivaHeladera() * this.coefHeladerasActivas;
    return total;
  }

  /**
   * Permite calcular los puntos de varias colaboraciones.
   *
   * @param colaboraciones lista de colaboraciones cuyos puntos se quiere calcular.
   * @return puntos totales.
   */

  //En caso de ser necesario calcular para una lista de colaboraciones.
  public Float calcularPuntosDeLista(List<Colaboracion> colaboraciones) {
    float puntosTotales = 0f;
    colaboraciones.forEach(
        colab -> this.sumarizarPuntos(
            puntosTotales,
            this.calcularPuntosDe_alt(colab)
        )
    );
    return puntosTotales;
  }

  public void sumarizarPuntos(Float puntosTotales, Float unosPuntos) {
    puntosTotales += unosPuntos;
  }
}
