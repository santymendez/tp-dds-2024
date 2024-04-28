package modules.security.rules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CantidadMinimaDeCaracteres extends Regla{
    Integer cantidadMinima;

    public CantidadMinimaDeCaracteres(Integer unaCantidadMinima){
        this.setCantidadMinima(unaCantidadMinima);
        this.setMensajeError("tamanio de contrasenia menor al permitido");
    }

    @Override
    public Boolean cumpleLaRegla(String unaContrasenia) {
        return unaContrasenia.length() >= this.getCantidadMinima();
    }
}
