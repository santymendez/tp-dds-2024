package modules.security.rules;

import lombok.Setter;
import java.util.List;

@Setter
public abstract class Regla {
    private String mensajeError;

    public Boolean esValida(String unaContrasenia, List<String> mensajes){
        if(!this.cumpleLaRegla(unaContrasenia)){
            mensajes.add(this.mensajeError);
        }
        return this.cumpleLaRegla(unaContrasenia);
    }

    public abstract Boolean cumpleLaRegla(String unaContrasenia);
}
