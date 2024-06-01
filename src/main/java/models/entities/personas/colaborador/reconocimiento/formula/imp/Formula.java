package models.entities.personas.colaborador.reconocimiento.formula.imp;

import java.util.EnumMap;
import java.util.function.Function;
import lombok.Setter;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;

/**
 * Representa una formula de cálculo de puntos.
 */

public class Formula
    implements models.entities.personas.colaborador.reconocimiento.formula.Formula {
  @Setter
  private Float coefPesosDonados = 0.5f;
  @Setter
  private Float coefViandasDonadas = 1.5f;
  @Setter
  private Float coefViandasDistribuidas = 1f;
  @Setter
  private Float coefTarjetasRepartidas = 2f;
  @Setter
  private Float coefHeladerasActivas = 5f;

  private final EnumMap<TipoColaboracion, Function<Colaboracion, Float>> mapCalculos;

  /**
   * Constructor para generar el map de cada cálculo de colaboración.
   */

  public Formula() {
    this.mapCalculos = new EnumMap<>(TipoColaboracion.class);
    this.mapCalculos.put(TipoColaboracion.DONAR_DINERO, this::calcPesosDonados);
    this.mapCalculos.put(TipoColaboracion.DONAR_VIANDA, this::calcViandasDonadas);
    this.mapCalculos.put(TipoColaboracion.DISTRIBUIR_VIANDAS, this::calcDistribuirViandas);
    this.mapCalculos.put(TipoColaboracion.ENTREGAR_TARJETA, this::calcTarjetasRepartidas);
    this.mapCalculos.put(TipoColaboracion.COLOCAR_HELADERA, this::calcHeladerasActivas);
  }

  @Override
  public Float calcularPuntosDe(Colaboracion colaboracion) {
    return mapCalculos.get(colaboracion.getTipoColaboracion()).apply(colaboracion);
  }

  private Float calcPesosDonados(Colaboracion colaboracion) {
    return colaboracion.getMonto() * coefPesosDonados;
  }

  private Float calcViandasDonadas(Colaboracion colaboracion) {
    return colaboracion.getCantViandas() * this.coefViandasDonadas;
  }

  private Float calcDistribuirViandas(Colaboracion colaboracion) {
    return colaboracion.getCantViandasDistribuidas() * this.coefViandasDistribuidas;
  }

  private Float calcTarjetasRepartidas(Colaboracion colaboracion) {
    return colaboracion.getCantTarjetasEntregadas() * this.coefTarjetasRepartidas;
  }

  private Float calcHeladerasActivas(Colaboracion colaboracion) {
    return colaboracion.tiempoActivaHeladera() * this.coefHeladerasActivas;
  }
}
