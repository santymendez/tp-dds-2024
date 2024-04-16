package modules.passwordValidator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Registro {
    private Usuario usuario;
    private Controlador controlador;

    public Boolean sePuedeRegistrar(){
        return this.controlador.esValida(this.usuario.getContrasenia());
    }
}
