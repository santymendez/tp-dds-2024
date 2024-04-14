package modulos.validadorContrasenias;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Usuario {
    private String nombre;
    @Setter
    private String contrasenia;

    public Usuario(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

}
