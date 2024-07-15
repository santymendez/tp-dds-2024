package models.entities.heladera.modulos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;

/**
 * Representa al modulo de la heladera encargado de la gestion
 * de las tarjetas de los colaboradores.
 */

@Setter
@Getter
public class ModuloDeAperturas {
  private List<TarjetaColaborador> tarjetasHabilitadas;
  private Float limiteDeTiempo;
  private Heladera heladera;
  //TODO en los otros modulos busque evitar lo de tener
  // la heladera, pero en este no se si conviene

  /**
   * Constructor del módulo de aperturas.
   *
   * @param heladera Heladera la cual va a tener este módulo.
   */

  public ModuloDeAperturas(Heladera heladera) {
    this.tarjetasHabilitadas = new ArrayList<>();
    this.limiteDeTiempo = 3.0f; //Su valor original es 3
    this.heladera = heladera;
  }

  /**
   * Permite intentar abrir la heladera con una tarjeta a partir de su codigo.
   *
   * @param tarjeta de la tarjeta con la que se intenta abrir.
   */

  public Boolean intentarAbrirCon(TarjetaColaborador tarjeta) {
    if (!tarjetasHabilitadas.contains(tarjeta)) {
      throw new RuntimeException("No posees los permisos necesarios para abrir la heladera.");
    }

    UsoTarjetaColaborador ultimoUso = this.ultimoUsoDe(tarjeta);

    if (!this.estaVigente(ultimoUso.getApertura().getFechaSolicitud())) {
      throw new RuntimeException("Tu solicitud ha expirado.");
    }

    ultimoUso.getApertura().setFechaApertura(LocalDateTime.now());
    return true;
  }

  /**
   * Obtiene el último uso de una tarjeta.
   *
   * @param tarjeta de la que quiere obtener el uso.
   * @return UsoTarjetaColaborador el último uso.
   */

  public UsoTarjetaColaborador ultimoUsoDe(TarjetaColaborador tarjeta) {
    List<UsoTarjetaColaborador> usosFiltradosPorHeladera =
        tarjeta.getUsos().stream().filter(uso -> uso.getHeladera() == this.heladera).toList();

    if (usosFiltradosPorHeladera.isEmpty()) {
      throw new RuntimeException("No hay tarjetas en la lista, o no está inicializada");
    }
    return usosFiltradosPorHeladera.get(usosFiltradosPorHeladera.size() - 1);
  }

  /**
   * Verifica si una solicitud está vigente.
   *
   * @param ultimaSolicitud Solicitud que se intenta verificar si se encuentra vigente.
   */

  public Boolean estaVigente(LocalDateTime ultimaSolicitud) {
    Duration duration = Duration.between(ultimaSolicitud, LocalDateTime.now());
    float horas = duration.toHours();
    return horas < limiteDeTiempo;
  }
}
