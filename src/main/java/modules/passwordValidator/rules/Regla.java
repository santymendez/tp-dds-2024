package modules.passwordValidator.rules;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public abstract class Regla {
    private String mensajeError;

    public Boolean esValida(String unaContrasenia, List<String> mensajes){
        if(!this.cumpleLaRegla(unaContrasenia)){
            mensajes.add(this.getMensajeError());
        }
        return this.cumpleLaRegla(unaContrasenia);
    }

    public abstract Boolean cumpleLaRegla(String unaContrasenia);
}
