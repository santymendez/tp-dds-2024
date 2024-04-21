package modules.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor

public class Usuario {
    private String nombre;
    @Setter
    private String contrasenia;

}
