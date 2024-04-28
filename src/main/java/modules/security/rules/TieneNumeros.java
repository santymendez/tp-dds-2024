package modules.security.rules;

public class TieneNumeros extends Regla{

    public TieneNumeros(){
        this.setMensajeError("No tiene numeros");
    }

    @Override
    public Boolean cumpleLaRegla(String unaContrasenia){
        String regexNumeros = "^(?=.*[0-9]).*$";
        return unaContrasenia.matches(regexNumeros);
    }

}
