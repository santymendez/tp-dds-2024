package modulos.validadorContrasenias;

import lombok.Getter;

import java.util.HashSet;

public class Registro {
    private Usuario usuario;
    @Getter
    private Controlador controlador;

    public Registro(Controlador controlador) {
        this.controlador = controlador;
    }

    public Boolean sePuedeRegistrar(Usuario usuario){
        //TODO
        // posiblemente habria que hace algo con el nombre de usuario
        // no creo que se pueda poner cualquiera
        return this.controlador.esValida(usuario.getContrasenia());
    }
}
