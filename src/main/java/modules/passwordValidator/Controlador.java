package modules.passwordValidator;

import lombok.Getter;
import lombok.Setter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


@Setter
public class Controlador {
    private Integer longitudMaxima = 64; //64 caracteres
    private Integer longitudMinima = 8; //8 caracteres
    private File top10000PeoresContrasenias = new File("src/main/resources/passwordValidator/top10000.txt");
    private Regex regex;

    public Controlador(Regex regex){
        this.regex = regex;
    }

    public Boolean esValida(String clave){
        return this.cumplePoliticasDeContrasenias(clave) &&
                this.noPerteneceAPeoresContrasenias(clave);
    }

    public Boolean noPerteneceAPeoresContrasenias(String clave){
        return this.verificarContraseniaConArchivo(top10000PeoresContrasenias, clave);
    }

    public Boolean verificarContraseniaConArchivo(File archivo, String clave) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (clave.equals(line)) {
                    return false; // Matchea la clave
                }
            }
            return true;
        } catch (Exception e) {
                System.err.println("Error al leer el archivo de contraseñas: " + e.getMessage());
                return false; // Devolver un valor en caso de excepción
        }
    }

    public Boolean cumplePoliticasDeContrasenias(String clave){
        return regex.cumpleRegex(clave, longitudMinima, longitudMaxima);
    }
}