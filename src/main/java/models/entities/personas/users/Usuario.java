package models.entities.personas.users;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
@Setter
public class Usuario extends Persistente {
  @Column(name = "nombreUsuario")
  String nombreUsuario;

  @Column(name = "contrasenia")
  String contrasenia;

  @ElementCollection()
  @Column(name = "tipoRol")
  List<TipoRol> tipoRol;
}
