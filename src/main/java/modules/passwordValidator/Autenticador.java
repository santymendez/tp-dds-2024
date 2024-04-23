package modules.passwordValidator;

import lombok.Getter;
import lombok.Setter;
import modules.passwordValidator.rules.Regla;

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

    public String esValida(String unaContrasenia){
        
        List<String> mensajes = new ArrayList<>();

        if(this.cumpleLasReglas(unaContrasenia, mensajes)){
            return "Contrasenia Valida";
        }
        else{
            return this.mostrarMensajesConFormato(mensajes);
        }
    }

    public Boolean cumpleLasReglas(String unaContrasenia, List<String> mensajes) {
        return politicas.stream().allMatch(m -> m.esValida(unaContrasenia, mensajes));
    }

    public String mostrarMensajesConFormato(List<String> mensajes){
        String mensajeCompleto = "";

        for (String mensaje : mensajes) {
            mensajeCompleto.append("Error: ").append(mensaje).append("  ");
        }

        return mensajeCompleto;
    }
}
