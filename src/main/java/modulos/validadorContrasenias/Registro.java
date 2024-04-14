package modulos.validadorContrasenias;

import java.util.HashSet;

public class Registro {
    private Usuario usuario;
    private Controlador controlador;

    public Controlador getControlador() {
        return controlador;
    }

    public Registro(Controlador controlador) {
        this.controlador = controlador;
    }

    public Boolean sePuedeRegistrar(Usuario unUsuario){
        //TODO
        // posiblemente habria que hace algo con el nombre de usuario
        // no creo que se pueda poner cualquiera
        return this.getControlador().esValida(unUsuario.getContrasenia()) ;
    }
}
