package modules.passwordValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public Boolean cumpleRegex(String clave, Integer longitudMinima, Integer longitudMaxima) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{" + longitudMinima.toString() + "," + longitudMaxima.toString() + "}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(clave);
        return m.matches();
    }
}