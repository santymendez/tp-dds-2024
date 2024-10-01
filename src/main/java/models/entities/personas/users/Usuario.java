package models.entities.personas.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.db.Persistente;

/**
 * Representa a un usuario en el sistema.
 * Un usuario tiene un nombre de usuario, contraseña y un rol.
 */

@AllArgsConstructor
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
public class Usuario extends Persistente {
  @Column(name = "nombreUsuario")
  String nombreUsuario;

  @Column(name = "contrasenia")
  String contrasenia;

  //TODO VER SI NO PUEDE TENER VARIOS ROLES
  @Enumerated(EnumType.STRING)
  @Column(name = "tipoRol")
  TipoRol tipoRol;
}
