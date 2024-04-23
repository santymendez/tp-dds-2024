package modules.passwordValidator.rules;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PerteneceAlArchivo extends Regla {
    private List<String> palabrasDelArchivo;

    @Override
    public Boolean cumpleLaRegla(String unaContrasenia) {
        return !this.getPalabrasDelArchivo().contains(unaContrasenia);
    }

    public PerteneceAlArchivo(String nombreArchivo, String path){
        this.setPalabrasDelArchivo(new ArrayList<>());
        this.setMensajeError("Pertenece al archivo " + nombreArchivo);
        File archivo =  new File(path);

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {

                // Dividir la línea en palabras utilizando espacio como separador
                String[] palabras = linea.split("\\s+");

                // Agregar cada palabra a la lista
                for (String palabra : palabras) {
                    this.getPalabrasDelArchivo().add(palabra);
                }
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
