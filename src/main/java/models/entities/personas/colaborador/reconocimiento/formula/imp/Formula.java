package models.entities.personas.colaborador.reconocimiento.formula.imp;

import java.util.EnumMap;
import java.util.function.Function;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.personas.colaborador.reconocimiento.formula.InterfazFormula;
import models.entities.personas.colaborador.reconocimiento.formula.imp.calculos.CalculoHeladerasActivas;
import models.entities.personas.colaborador.reconocimiento.formula.imp.calculos.CalculoPesosDonados;
import models.entities.personas.colaborador.reconocimiento.formula.imp.calculos.CalculoTarjetasRepartidas;
import models.entities.personas.colaborador.reconocimiento.formula.imp.calculos.CalculoViandasDistribuidas;
import models.entities.personas.colaborador.reconocimiento.formula.imp.calculos.CalculoViandasDonadas;

/**
 * Representa una formula de cálculo de puntos.
 */

public class Formula
    implements InterfazFormula {

  private final EnumMap<TipoColaboracion, Function<Colaboracion, Float>> mapCalculos;

  /**
   * Constructor para generar el map de cada cálculo de colaboración.
   */

  public Formula() {
    this.mapCalculos = new EnumMap<>(TipoColaboracion.class);

    CalculoPesosDonados calculoPesosDonados = new CalculoPesosDonados();
    this.mapCalculos.put(TipoColaboracion.DONAR_DINERO,
        calculoPesosDonados::calcularPuntosDe);

    CalculoViandasDonadas calculoViandasDonadas = new CalculoViandasDonadas();
    this.mapCalculos.put(TipoColaboracion.DONAR_VIANDA,
        calculoViandasDonadas::calcularPuntosDe);

    CalculoViandasDistribuidas calculoViandasDistribuidas = new CalculoViandasDistribuidas();
    this.mapCalculos.put(TipoColaboracion.DISTRIBUIR_VIANDAS,
        calculoViandasDistribuidas::calcularPuntosDe);

    CalculoTarjetasRepartidas calculoTarjetasRepartidas = new CalculoTarjetasRepartidas();
    this.mapCalculos.put(TipoColaboracion.ENTREGAR_TARJETA,
        calculoTarjetasRepartidas::calcularPuntosDe);

    CalculoHeladerasActivas calculoHeladerasActivas = new CalculoHeladerasActivas();
    this.mapCalculos.put(TipoColaboracion.COLOCAR_HELADERA,
        calculoHeladerasActivas::calcularPuntosDe);
  }

  @Override
  public Float calcularPuntosDe(Colaboracion colaboracion) {
    return mapCalculos.get(colaboracion.getTipoColaboracion()).apply(colaboracion);
  }

}
