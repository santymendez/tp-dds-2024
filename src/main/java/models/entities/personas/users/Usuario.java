package models.entities.personas.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.db.Persistente;

/**
 * Representa a un usuario en el sistema.
 * Un usuario tiene un nombre de usuario, contraseña y un rol.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario extends Persistente {
  @Getter
  @Column(name = "nombreUsuario")
  String nombreUsuario;

  @Column(name = "contrasenia")
  String contrasenia;

  @Getter
  @Enumerated(EnumType.STRING)
  @Column(name = "tipoRol")
  private TipoRol tipoRol;
}
