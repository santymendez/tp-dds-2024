package models.entities.personas.tarjetas.colaborador;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;

/**
 * Clase que representa la tarjeta que posee el Colaborador.
 */

@Getter
@Setter
@Entity
@Table (name = "tarjetas_colaboradores")
public class TarjetaColaborador {
  @Id
  @Column(name = "id", nullable = false, unique = true)
  private final String codigo;

  @Column(name = "activo")
  private Boolean activo;

  @Column(name = "fechaAlta", columnDefinition = "DATE")
  private LocalDate fechaAlta;

  @OneToMany(mappedBy = "tarjetaColaborador",
          cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
          fetch = FetchType.EAGER)
  private final List<UsoTarjetaColaborador> usos;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  /**
   * Metodo constructor de la tarjeta del colaborador.
   */

  public TarjetaColaborador() {
    this.codigo = this.generarCodigoAlfanumerico();
    this.usos = new ArrayList<>();
    this.fechaAlta = LocalDate.now();
    this.activo = true;
  }

  private String generarCodigoAlfanumerico() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 11);
  }

  /**
   * Metodo que agrega un uso a la tarjeta del colaborador.
   *
   * @param uso Uso de la tarjeta.
   * @param heladera Heladera a la que se le solicita el uso.
   */

  public void agregarUso(UsoTarjetaColaborador uso, Heladera heladera) {
    this.usos.add(uso);
    uso.setTarjetaColaborador(this);
    uso.setHeladera(heladera);
  }

  /**
   * Obtiene el último uso de una tarjeta.
   *
   * @param heladera de la que quiere obtener el uso.
   * @return UsoTarjetaColaborador el último uso.
   */

  public UsoTarjetaColaborador ultimoUsoVigenteEn(
      Heladera heladera, LocalDateTime fechaHoraApertura
  ) {
    Optional<UsoTarjetaColaborador> ultimoUso = this.getUsos().stream()
        .filter(uso -> uso.getHeladera() == heladera)
        .max(Comparator.comparing(uso -> uso.getApertura().getFechaSolicitud()));

    if (ultimoUso.isPresent()
        && this.estaVigente(ultimoUso.get().getApertura().getFechaSolicitud(),
        heladera, fechaHoraApertura)) {
      UsoTarjetaColaborador uso = ultimoUso.get();
      uso.getApertura().setFechaApertura(fechaHoraApertura);
      return uso;
    } else {
      return null;
    }

  }

  /**
   * Verifica si una solicitud está vigente.
   *
   * @param ultimaSolicitud Solicitud que se intenta verificar si se encuentra vigente.
   */

  public Boolean estaVigente(LocalDateTime ultimaSolicitud,
                             Heladera heladera, LocalDateTime fechaHoraApertura) {
    Duration duration = Duration.between(ultimaSolicitud, fechaHoraApertura);
    return heladera.getLimitador().menorAlLimite(duration);
  }

  @PrePersist
  protected void onInsert() {
    if (this.fechaAlta == null) {
      this.fechaAlta = LocalDate.now();
    }
  }
}
