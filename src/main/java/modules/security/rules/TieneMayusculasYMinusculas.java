package modules.security.rules;

public class TieneMayusculasYMinusculas extends Regla{
    public TieneMayusculasYMinusculas(){
        this.setMensajeError("No tiene letras mayusculas y minusculas");
    }
@Override
    public Boolean cumpleLaRegla(String unaContrasenia) {
        String regexMayusculaYMinuscula = "^(?=.*[a-z])(?=.*[A-Z]).*$";
        return unaContrasenia.matches(regexMayusculaYMinuscula);
    }
}
