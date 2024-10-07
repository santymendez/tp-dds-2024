package models.entities.personas.colaborador.reconocimiento;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import models.db.Persistente;
import models.entities.colaboracion.Colaboracion;
import models.entities.personas.colaborador.reconocimiento.formula.imp.Formula;

/**
 * Representa el reconocimiento que reciben los colaboradores.
 */

@Embeddable
public class Reconocimiento {
  @Getter
  @Column(name = "puntosAcumulados")
  private Float puntosPorColaborar;

  @Setter
  @Transient
  private Formula formulaCalculoDePuntos;

  public Reconocimiento() {
    this.puntosPorColaborar = 0f;
    this.formulaCalculoDePuntos = new Formula();
  }

  public void sumarPuntos(Colaboracion colaboracion) {
    puntosPorColaborar += formulaCalculoDePuntos.calcularPuntosDe(colaboracion);
  }

  public void restarPuntos(Float puntos) {
    puntosPorColaborar -= puntos;
  }
}