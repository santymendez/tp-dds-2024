package modules.security.rules;

public class TieneCaracteresEspeciales extends Regla{

    public TieneCaracteresEspeciales(){
        this.setMensajeError("No tiene caracteres especiales");
    }

    @Override
    public Boolean cumpleLaRegla(String unaContrasenia){
        String regexCaracteresEspeciales = "^(?=.*[!@#$%^&*_+\\-.<>]).*$"; //Cualquier otro caracter especial no es valido
        return unaContrasenia.matches(regexCaracteresEspeciales);
    }

}
