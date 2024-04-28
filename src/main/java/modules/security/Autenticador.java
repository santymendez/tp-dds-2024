package modules.security;

import lombok.Getter;
import lombok.Setter;
import modules.security.rules.Regla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Setter
@Getter
public class Autenticador {
    private HashSet<Regla> politicas;

    public Autenticador(){
        this.setPoliticas(new HashSet<>());
    }

    public void agregarPoliticas(Regla ... reglas){
        Collections.addAll(this.getPoliticas(), reglas);
    }

    public Boolean esValida(String unaContrasenia){
        
        List<String> mensajesDeError = new ArrayList<>();

        if(this.cumpleLasReglas(unaContrasenia, mensajesDeError)){
             System.out.println("Contraseña valida.");
        }
        else{
            System.out.println(this.mostrarMensajesConFormato(mensajesDeError));
        }
        return cumpleLasReglas(unaContrasenia, mensajesDeError);
    }

    public Boolean cumpleLasReglas(String unaContrasenia, List<String> mensajesDeError) {
        return politicas.stream().allMatch(m -> m.esValida(unaContrasenia, mensajesDeError));
    }

    public String mostrarMensajesConFormato(List<String> mensajes){
        StringBuilder mensajeCompleto = new StringBuilder();

        for (String mensaje : mensajes) {
            mensajeCompleto.append("Error: ").append(mensaje).append(" ");
        }

        return String.valueOf(mensajeCompleto);
    }
}
