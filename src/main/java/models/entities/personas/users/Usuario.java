package models.entities.personas.users;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.db.Persistente;

import javax.persistence.*;

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
