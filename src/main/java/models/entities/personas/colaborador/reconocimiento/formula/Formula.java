package models.entities.personas.colaborador.reconocimiento.formula;

import java.util.EnumMap;
import java.util.function.Function;
import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;

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
}
