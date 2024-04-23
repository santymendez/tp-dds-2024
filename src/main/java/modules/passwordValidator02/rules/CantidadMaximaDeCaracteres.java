package modules.passwordValidator02.rules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CantidadMaximaDeCaracteres extends Regla{
    Integer cantidadMaxima;

    public CantidadMaximaDeCaracteres(Integer unaCantidadMaxima){
        this.setCantidadMaxima(unaCantidadMaxima);
        this.setMensajeError("Sobrepaso la cantidad maxima de caracteres");
    }

    @Override
    public Boolean cumpleLaRegla(String unaContrasenia) {
        return unaContrasenia.length() <= this.getCantidadMaxima();
    }
}
