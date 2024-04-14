package modulos.validadorContrasenias;

import lombok.Getter;

public class Registro {
    private Usuario usuario;
    @Getter
    private Controlador controlador;

    public Registro(Controlador controlador) {
        this.controlador = controlador;
    }

    public Boolean sePuedeRegistrar(Usuario usuario){
        return this.controlador.esValida(usuario.getContrasenia());
    }
}
