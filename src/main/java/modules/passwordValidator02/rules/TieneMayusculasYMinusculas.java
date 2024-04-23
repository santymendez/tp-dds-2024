package modules.passwordValidator02.rules;

import java.util.List;

public class TieneMayusculasYMinusculas extends Regla{

    @Override
    public Boolean cumpleLaRegla(String unaContrasenia) {
        boolean contieneMayusculas = !unaContrasenia.equals(unaContrasenia.toLowerCase());
        boolean contieneMinusculas = !unaContrasenia.equals(unaContrasenia.toUpperCase());

        return contieneMayusculas && contieneMinusculas;
    }

    public TieneMayusculasYMinusculas(){
        this.setMensajeError("No tiene letras mayusculas y minusculas");
    }
}
