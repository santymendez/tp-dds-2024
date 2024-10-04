package models.entities.personas.users;

import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Generated;
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
  @Column(name = "nombreUsuario")
  String nombreUsuario;

  @Column(name = "contrasenia")
  String contrasenia;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoRol")
  private TipoRol tipoRol;
}
